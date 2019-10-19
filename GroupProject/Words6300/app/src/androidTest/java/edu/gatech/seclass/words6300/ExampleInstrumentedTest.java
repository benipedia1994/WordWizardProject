package edu.gatech.seclass.words6300;

import android.content.Context;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;




/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    //Check if Main menu buttons are clickable
    @Test
    public void play_game_clickable(){
        onView(withId(R.id.play_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void  settings_clickable(){
        onView(withId(R.id.settings_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void  view_statistics_clickable(){
        onView(withId(R.id.view_statistics_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void  how_to_play_clickable(){
        onView(withId(R.id.how_to_play_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void  exit_app_button_clickable(){
        onView(withId(R.id.exit_app_button)).check(ViewAssertions.matches(isClickable()));
    }

    //Check if clicks go to and show proper screen or perform proper function
    @Test
    public void activity_main_to_activity_game_test(){
        onView(withId(R.id.play_button))
                .perform(click());
        onView(withId(R.id.make_word_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void activity_main_to_activity_settings(){
        onView(withId(R.id.settings_button))
                .perform(click());
        onView(withId(R.id.reset_to_default_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void activity_main_to_activity_statistics(){
        onView(withId(R.id.view_statistics_button))
                .perform(click());
        onView(withId(R.id.game_score_stat_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void activity_main_to_instructions(){
        onView(withId(R.id.how_to_play_button))
                .perform(click());
        onView(withId(R.id.return_to_main_menu_button))
                .check((matches(isDisplayed())));
    }

    @Test
    public void activity_main_to_close_app(){
        try{onView(withId(R.id.exit_app_button))
                .perform(click());
        assertTrue(mainActivityActivityTestRule.getActivity().isDestroyed());
        fail();
        } catch(not)
    }


    //Check if Play
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("edu.gatech.seclass.words6300", appContext.getPackageName());
    }

    // Main Screen Launch test
}


