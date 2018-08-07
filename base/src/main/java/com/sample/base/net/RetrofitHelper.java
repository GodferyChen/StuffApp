package com.sample.base.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sample.base.BaseApp;
import com.sample.base.helper.CommonHelper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/7
 * @Description
 */
public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;
    private static String baseUrl = "https://www.google.com/";
    private static String defaultUrl = "https://www.google.com/";

    static {
        initOkHttpClient();
        // 获取默认地址
    }

    private static void setBaseUrl(String baseUrl) {
        RetrofitHelper.baseUrl = baseUrl;
    }


    /**
     * 网络请求入口
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T service(Class<T> service) {
        return createApi(service, baseUrl);
    }

    /**
     * 根据传入的baseUrl，和api创建retrofit
     *
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        if (baseUrl == null || "".equals(baseUrl)) {
            baseUrl = defaultUrl;
        }
        Retrofit retrofit;
        try {
            // 捕获因为服务器地址设置非法导致的异常

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(mOkHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (Exception e) {
            // 还原设置
            RetrofitHelper.setBaseUrl(defaultUrl);

            retrofit = new Retrofit.Builder()
                    .baseUrl(defaultUrl)
                    .client(mOkHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(clazz);
    }

    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private static void initOkHttpClient() {
        SSLSocketFactory socketFactory = SSLHelper.getSocketFactory(BaseApp.getInstance());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Cache cache = new Cache(new File(BaseApp.getInstance()
                            .getCacheDir(), "HttpCache"), 1024 * 1024 * 10);

                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    mOkHttpClient = builder
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .sslSocketFactory(socketFactory)
                            .addNetworkInterceptor(new CacheInterceptor())
//                            .addNetworkInterceptor(new StethoInterceptor())
//                              .addInterceptor(new UserAgentInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .hostnameVerifier(new HostnameVerifier() {
                                @Override
                                public boolean verify(String s, SSLSession sslSession) {
                                    return true;
                                }
                            })
                            .build();
                }
            }
        }
    }

    /**
     * 为okhttp添加缓存，这里是考虑到服务器不支持缓存时，从而让okhttp支持缓存
     */
    private static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            // 有网络时 设置缓存超时时间1个小时
            int maxAge = 60 * 60;
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();

            if (CommonHelper.isNetworkAvailable(BaseApp.getInstance())) {
                //有网络时只从网络获取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (CommonHelper.isNetworkAvailable(BaseApp.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {

                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }
}
