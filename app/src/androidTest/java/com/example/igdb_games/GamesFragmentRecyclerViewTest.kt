package com.example.igdb_games

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.igdb_games.ui.adapter.GamesRVAdapter
import com.example.igdb_games.ui.fragment.GamesFragment
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GamesFragmentRecyclerViewTest {

    private lateinit var scenario: FragmentScenario<GamesFragment>

    @Before
    fun setup() {
        scenario = FragmentScenario.launch(GamesFragment::class.java)
    }

    @Test
    fun fragmentGames_ScrollTo() {
        onView(withId(R.id.rv_games))
            .perform(
                RecyclerViewActions.scrollTo<GamesRVAdapter.ViewHolder>(
                    hasDescendant(withText("Name: 42"))
                )
            )
    }

    @Test
    fun fragmentGames_PerformClickAtPosition() {
            onView(withId(R.id.rv_games))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<GamesRVAdapter.ViewHolder>(
                        0,
                        click()
                    )
                )
    }

    @Test
    fun fragmentGames_PerformClickOnItem() {
            onView(withId(R.id.rv_games))
                .perform(
                    RecyclerViewActions.scrollTo<GamesRVAdapter.ViewHolder>(
                        hasDescendant(withText("Name: 50"))
                    )
                )

            onView(withId(R.id.rv_games))
                .perform(
                    RecyclerViewActions.actionOnItem<GamesRVAdapter.ViewHolder>(
                        hasDescendant(withText("Name: 42")),
                        click()
                    )
                )
    }

    @Test
    fun fragmentGames_PerformCustomClick() {
            onView(withId(R.id.rv_games))
                .perform(
                    RecyclerViewActions
                        .actionOnItemAtPosition<GamesRVAdapter.ViewHolder>(
                            0,
                            tapOnItemWithId(R.id.iv_cover)
                        )
                )
    }

    private fun tapOnItemWithId(id: Int) = object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Нажимаем на view с указанным id"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById(id) as View
            v.performClick()
        }
    }
}
