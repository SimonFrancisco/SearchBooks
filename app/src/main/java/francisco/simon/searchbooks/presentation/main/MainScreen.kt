package francisco.simon.searchbooks.presentation.main

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import francisco.simon.searchbooks.core.navigation.AppNavGraph
import francisco.simon.searchbooks.core.navigation.NavigationState
import francisco.simon.searchbooks.core.navigation.rememberNavigationState
import francisco.simon.searchbooks.core.presentation.utils.toBookDetails
import francisco.simon.searchbooks.presentation.favouriteBooks.FavouriteBookScreen
import francisco.simon.searchbooks.presentation.searchBooks.components.SearchBookScreen
import francisco.simon.searchbooks.ui.theme.BrightSkyBlue
import francisco.simon.searchbooks.ui.theme.LightGray
import francisco.simon.searchbooks.ui.theme.White

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = {
            BottomBar(navigationState)
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            searchBookScreenContent = {
                SearchBookScreen(
                    paddingValues = paddingValues,
                    onBookClicked = {
                        navigationState.navigateToBookDetails(book = it.toBookDetails())
                    }
                )
            }, favouriteBooksContentScreen = {
                FavouriteBookScreen(onBookClicked = {
                    navigationState.navigateToBookDetails(book = it.toBookDetails())
                }, onBackPressed = {
                    navigationState.navHostController.popBackStack()
                })
            },
            bookDetailsScreenContent = {

            }
        )
    }


}


@Composable
private fun BottomBar(navigationState: NavigationState) {
    NavigationBar(
        modifier = Modifier.height(55.dp),
        containerColor = White
    ) {
        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
        val items = listOf(NavigationItem.Home, NavigationItem.Favourite)
        items.forEach { item ->
            val selected = navBackStackEntry?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedTextColor = BrightSkyBlue,
                    unselectedIconColor = LightGray,
                    unselectedTextColor = LightGray,
                    selectedIconColor = BrightSkyBlue,
                    selectedIndicatorColor = White,
                    disabledIconColor = LightGray,
                    disabledTextColor = LightGray
                ),
                selected = selected,
                onClick = {
                    if (!selected) {
                        navigationState.navigateTo(item.screen.route)
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = item.titleResId))
                }
            )
        }
    }
}
