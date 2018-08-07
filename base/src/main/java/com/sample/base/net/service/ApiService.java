package com.sample.base.net.service;

import com.sample.base.net.bean.RequestEntry;
import com.sample.base.net.bean.ResponseEntry;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/7
 * @Description
 */
public interface ApiService {

    /**
     * 示例
     * @param requestEntry
     * @return
     */
    @POST
    Observable<ResponseEntry> getData(@Body RequestEntry requestEntry);

}
