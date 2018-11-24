package com.demo.testingdemo;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.demo.cityIno.R;
import com.demo.cityIno.mvp.CityListActivity;
import com.demo.cityIno.util.EspressoTestingIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CityListTest {


    @Rule
    public ActivityTestRule<CityListActivity> activityTestRule =
            new ActivityTestRule<>(CityListActivity.class);

    /**
     * Register IdlingResource resource to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize test actions.
     */

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource());
    }

    @Test
    public void checkStaticView() {
        // verify default empty text message movies_recycler_list
        onView(withId(R.id.swipe_msg_tv)).check(matches(not(isDisplayed())));
        onView(withId(R.id.cityinfo_recycler_list)).check(matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerViewVisibility() {
        // perform swipe
        onView(withId(R.id.swipe_msg_tv)).check(matches(not(isDisplayed())));
        onView(withId(R.id.swipe_container)).perform(swipeDown());
        // verify swipe is being displayed
      //  onView(withId(R.id.swipe_msg_tv)).check(matches(not(isDisplayed())));

        // verify recycler view is being displayed
        onView(withId(R.id.cityinfo_recycler_list)).check(matches(isDisplayed()));
        onView(withId(R.id.cityinfo_recycler_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Verify the toast text content
        CityListActivity activity = activityTestRule.getActivity();
        /*onView(withText("Title : 'Beavers'")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches((isDisplayed())));*/
    }


}
