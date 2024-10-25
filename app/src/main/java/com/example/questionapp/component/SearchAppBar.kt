package com.example.questionapp.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.primarySurface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.text.input.*

@Composable
fun  SearchAppBar(
    modifier: Modifier=Modifier,
    value:String,
    onInputeValueChange: (String) -> Unit,
    onClosedIconClicked: () -> Unit,
    onSearchIconClicked : () -> Unit

){
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onInputeValueChange,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        placeholder = {  Text(text = "Search...", color = Color.Black.copy(alpha = 0.7f)) },

        leadingIcon =  { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon",
               tint = Color.Black.copy(alpha = 0.7f)) },
        trailingIcon =  { IconButton(onClick = { if (value.isNotEmpty())  onInputeValueChange("")
            else onClosedIconClicked}) { Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Icon",
                    tint = Color.Black.copy(alpha = 0.7f)) } },
        keyboardOptions= KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions= KeyboardActions(
            onSearch = {onSearchIconClicked()}
        ),
        /*colors= TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colors.primarySurface,
                   unfocusedContainerColor= MaterialTheme.colors.primarySurface,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White
        )*/
    )

}