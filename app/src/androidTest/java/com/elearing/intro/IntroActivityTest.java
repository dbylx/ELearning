package com.elearing.intro;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.elearing.R;
import com.elearing.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntroActivityTest {
    @Rule
    public ActivityTestRule<IntroActivity> activityRule =
            new ActivityTestRule<>(IntroActivity.class);

    @Test
    public void startIntro(){
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.btn_get_started)).perform(click());
    }

}