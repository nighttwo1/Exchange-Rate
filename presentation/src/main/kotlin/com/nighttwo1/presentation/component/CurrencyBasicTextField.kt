package com.nighttwo1.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * https://stackoverflow.com/questions/67681416/jetpack-compose-decrease-height-of-textfield
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBasicTextField(
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(
        textAlign = TextAlign.Start
    ),
    value: String,
    onValueChange: (value: String) -> Unit = {},
) {
    BasicTextField(
        modifier = modifier,
        readOnly = readOnly,
        textStyle = textStyle,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = {
                    innerTextField()
                },
                enabled = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
                placeholder = null,
                leadingIcon = null,
                trailingIcon = null
            )
        }
    )
}