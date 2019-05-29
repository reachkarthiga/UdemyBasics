package com.android.example.udemybasics;

import android.Manifest;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SquareTriangularEspressoTest {

    @Rule
    public ActivityTestRule<SquareTriangular> activityTestRule =
            new ActivityTestRule<>(SquareTriangular.class);

    @Rule public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Rule public TestName name = new TestName();

    private CustomFailureHandler customFailureHandler;

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(),
                activityTestRule.getActivity().getLocalClassName(), activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    @Test
    public void testDescription() {
        customFailureHandler.setMethodName(name.getMethodName());
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(withText("Enter a Number below to find out if its a Square number, Triangular number , or both")));
    }

    @Test
    public void testSquareTriangularToast(){
        SquareTriangular activity = activityTestRule.getActivity();
        onView(withId(R.id.num)).perform(typeText("36"));
        onView(withText("Check")).perform(click());

        onView(withText(R.string.square_triangular_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }


    @Test
    public void testSquareNotTriangularToast(){
        SquareTriangular activity = activityTestRule.getActivity();
        onView(withId(R.id.num)).perform(typeText("9"));
        onView(withText("Check")).perform(click());

        onView(withText(R.string.square_not_triangular_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testNeitherSquareTriangularToast(){
        SquareTriangular activity = activityTestRule.getActivity();
        onView(withId(R.id.num)).perform(typeText("2"));
        onView(withText("Check")).perform(click());

        onView(withText(R.string.neither_square_triangular_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }


/*
    @Test
    public void testTeaFlow() {
        customFailureHandler.setMethodName(name.getMethodName());
        onView(allOf(withId(R.id.preference), withText("Which one do you prefer....??")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.coffeeImage))
                .check(matches(isDisplayed()));

        onView(withId(R.id.teaImage))
                .check(matches(isDisplayed()))
                .perform(click());

        //Make assertions

        onView(allOf(withId(R.id.preference), withText("Oh!! You Prefr Green Tea")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.coffeeImage))
                .check(matches(not(isDisplayed())));

    }
*/

}
