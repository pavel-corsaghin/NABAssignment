package io.hungnguyen.nab.assignment.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.hungnguyen.nab.assignment.R
import io.hungnguyen.nab.assignment.ui.base.AppComposable
import io.hungnguyen.nab.assignment.ui.composable.SearchBar
import io.hungnguyen.nab.assignment.ui.theme.AppTheme
import io.hungnguyen.nab.assignment.util.NavDestinations
import io.hungnguyen.nab.assignment.util.timeStampToFormattedDate

@Composable
fun HomeScreenContent(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    AppComposable(
        isLoading = state.isLoading,
        content = {
            HomeScreenContent(
                state = state,
                action = {
                    when (it) {
                        is HomeAction.OpenDetail -> {
                            navController.navigate("${NavDestinations.DETAIL_SCREEN}/${it.id}")
                        }
                        else -> viewModel.submitAction(it)
                    }
                })
        }
    )
}

@Composable
fun HomeScreenContent(
    state: HomeViewState,
    action: (HomeAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        }
    )
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                value = state.query,
                placeholder = stringResource(R.string.search_placeholder),
                onDoneActionClick = { action(HomeAction.Submit) },
                onValueChanged = { action(HomeAction.Searching(it)) },
                isLightMode = false
            )
            LazyColumn {
                items(state.weathers) { weather ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .clickable { action(HomeAction.OpenDetail(weather.id)) },
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            Text(stringResource(id = R.string.date, timeStampToFormattedDate(weather.date)))
                            Text(stringResource(id = R.string.avg_temp, weather.temp ?: 0))
                            Text(stringResource(id = R.string.pressure, weather.pressure ?: 0))
                            Text(stringResource(id = R.string.humidity, weather.humidity ?: 0))
                            Text(stringResource(id = R.string.description, weather.description ?: ""))
                        }
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AppTheme {
        HomeScreenContent(
            state = HomeViewState(),
            action = {}
        )
    }
}