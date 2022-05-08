package io.hungnguyen.nab.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.hungnguyen.nab.assignment.ui.detail.DetailScreen
import io.hungnguyen.nab.assignment.ui.home.HomeScreenContent
import io.hungnguyen.nab.assignment.ui.home.HomeViewState
import io.hungnguyen.nab.assignment.ui.theme.AppTheme
import io.hungnguyen.nab.assignment.util.NavDestinations

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.HOME_SCREEN,
                    ) {
                        composable(NavDestinations.HOME_SCREEN) {
                            HomeScreenContent(navController)
                        }
                        composable("${NavDestinations.DETAIL_SCREEN}/{id}") {
                            it.arguments?.getString("id")?.toLong()?.let { id ->
                                DetailScreen(id, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        HomeScreenContent(
            state = HomeViewState(),
            action = {}
        )
    }
}