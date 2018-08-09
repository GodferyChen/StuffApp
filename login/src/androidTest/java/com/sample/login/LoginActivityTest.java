package com.sample.login;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void showText() {
        //检验：一开始，textView不显示
        onView(withId(R.id.text)).check(matches(not(isDisplayed())));

        //检验：button的文字内容
        onView(withId(R.id.btn_show)).check(matches(withText("显示文字"))).perform(click());

        //检验：textView内容是否修改，并且变为可见
        onView(withId(R.id.text)).check(matches(withText("hello espresso!"))).check(matches(isDisplayed()));
    }
}