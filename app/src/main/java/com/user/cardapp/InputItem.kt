package com.user.cardapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha


@Composable
fun InputItem(
    textFieldValue: TextFieldValue,
    label: String,
    onTextChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String?,
    leadingIcon: Int? = null,
    trailingIcon: Int? = null
) {


    OutlinedTextField(value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = ImeAction.Next
        ),
        textStyle = textStyle,
        maxLines = 1,
        singleLine = true,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = label, style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        isError = !errorMessage.isNullOrEmpty(),

        leadingIcon = if (leadingIcon != null) {
            {
                Image(
                    painter = painterResource(leadingIcon),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.width(56.dp).height(36.dp).padding(start = 4.dp, end = 8.dp)
                )
            }
        } else {
            null
        },
        trailingIcon = if (trailingIcon != null) {
            {
                Image(
                    painter = painterResource(trailingIcon),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(36.dp).padding(end = 8.dp)
                )
            }
        } else {
            null
        },
        supportingText = {
            if (!errorMessage.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = modifier,
        visualTransformation = visualTransformation
    )

}