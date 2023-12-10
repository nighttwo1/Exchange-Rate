package com.nighttwo1.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.theme.CurrencyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    modifier: Modifier = Modifier,
    dropdownMenuStatus: DropdownMenuStatus,
) {
    val options = dropdownMenuStatus.options
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[dropdownMenuStatus.selectedItemIndex]) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        BasicTextField(
            modifier = Modifier.menuAnchor()
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(size = 7.dp)
                ),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            decorationBox = { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = "0",
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
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },

                    )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        dropdownMenuStatus.selectedItemIndex = index
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun rememberDropdownMenuStatus(
    options: List<String>,
    selectedItemIndex: Int = 0
) = remember {
    DropdownMenuStatus(options).also {
        it.selectedItemIndex = selectedItemIndex
    }
}

@Stable
class DropdownMenuStatus(
    val options: List<String>,
) {
    var selectedItemIndex by mutableIntStateOf(0)
}

@Preview
@Composable
fun DropDownMenuPreview() {
    CurrencyTheme {
        DropdownMenu(
            dropdownMenuStatus = rememberDropdownMenuStatus(
                selectedItemIndex = 2,
                options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
            )
        )
    }
}