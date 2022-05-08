package io.hungnguyen.nab.assignment.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.hungnguyen.nab.assignment.R
import io.hungnguyen.nab.assignment.ui.theme.AppTheme
import io.hungnguyen.nab.assignment.ui.theme.GreyTextFieldBorder
import io.hungnguyen.nab.assignment.ui.theme.LightGreen
import io.hungnguyen.nab.assignment.ui.theme.Tint

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onDoneActionClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit,
    isLightMode: Boolean = true,
    enabled: Boolean = true,
) {
    Surface(
        modifier = modifier
            .height(54.dp),
        shape = RoundedCornerShape(8.dp),
        color = if(isLightMode) Color.White else Tint,
        border = BorderStroke(1.dp, if (value.isEmpty())  GreyTextFieldBorder else LightGreen)
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            modifier = Modifier
                .onFocusChanged { onFocusChanged(it) },
            value = value,
            onValueChange = { query -> onValueChanged(query) },
            placeholder = { Text(text = placeholder) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            enabled = enabled,
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        tint = Color.Unspecified
                    )
                }
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChanged("") }) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }
            },
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                onDoneActionClick()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                placeholderColor = Color.Gray,
                trailingIconColor = Color.Gray,
            )
        )
    }
}

@Preview
@Composable
private fun SearchBarWhitePreview() {
    AppTheme {
        SearchBar(
            value = "",
            placeholder = "Search for a shooting range",
            onValueChanged = {}
        )
    }
}

@Preview
@Composable
private fun SearchBarFilledWhitePreview() {
    AppTheme {
        SearchBar(
            value = "abc",
            placeholder = "Search for a shooting range",
            onValueChanged = {}
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    AppTheme {
        SearchBar(
            value = "",
            placeholder = "Search for a shooting range",
            onValueChanged = {},
            isLightMode = false
        )
    }
}

@Preview
@Composable
private fun SearchBarFilledPreview() {
    AppTheme {
        SearchBar(
            value = "abc",
            placeholder = "Search for a shooting range",
            onValueChanged = {},
            isLightMode = false
        )
    }
}