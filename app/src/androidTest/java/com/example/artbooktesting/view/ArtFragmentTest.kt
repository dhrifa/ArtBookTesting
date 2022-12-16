package com.example.artbooktesting.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.artbooktesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import  com.example.artbooktesting.R

@MediumTest
@HiltAndroidTest
class ArtFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetails(){

        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ArtFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        //mimick click button
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        //check if nav has been chamged after tthe click
        Mockito.verify(navController).navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
    }
}