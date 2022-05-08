package io.hungnguyen.nab.assignment.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.hungnguyen.nab.assignment.R
import io.hungnguyen.nab.assignment.ui.base.AppComposable
import io.hungnguyen.nab.assignment.ui.theme.AppTheme
import io.hungnguyen.nab.assignment.util.timeStampToFormattedDate

@Composable
fun DetailScreen(
    id: Long,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.submitAction(DetailAction.GetDetail(id))
    val state by viewModel.state.collectAsState()
    AppComposable(
        isLoading = state.isLoading,
        content = {
            DetailScreen(navController, state)
        }
    )
}

@Composable
fun DetailScreen(
    navController: NavController,
    state: DetailState,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.detail)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(stringResource(id = R.string.date, timeStampToFormattedDate(state.weather?.date ?: 0)))
            Text(stringResource(id = R.string.avg_temp, state.weather?.temp ?: 0))
            Text(stringResource(id = R.string.pressure, state.weather?.pressure ?: 0))
            Text(stringResource(id = R.string.humidity, state.weather?.humidity ?: 0))
            Text(stringResource(id = R.string.description, state.weather?.description ?: ""))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    AppTheme {
        DetailScreen(
            navController = rememberNavController(),
            state = DetailState()
        )
    }
}
