package com.example.igdb_games

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.ui.fragment.GameFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<GameFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testBundle() {
        val game = Game(0, "name of game", "summary of game", null, null, null)
        val fragmentArgs = bundleOf(GameFragment.USER_ARG to game)
        //Запускаем Fragment с аргументами
        val scenario = launchFragmentInContainer<GameFragment>(fragmentArgs)


        val assertion = matches(withText("name of game"))
        onView(withId(R.id.tv_game_name)).check(assertion)
    }

    @Test
    fun fragment_testFillFieldsMethod() {
        val game = Game(0, "another name", null, null, null, null)

        scenario.onFragment { fragment ->
            fragment.fillFields(game, emptyList(), emptyList())
        }
        val assertion = matches(withText("another name"))
        onView(withId(R.id.tv_game_name)).check(assertion)
    }

}
