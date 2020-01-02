package com.elearing;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> activityRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.username)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.sign_up)).perform(click());
    }
}