package com.example.formvalidationinjetpackcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun Address (
    value: MutableState<String>, onTextChanged: (String)-> Unit) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onTextChanged,
            placeholder = { Text(text = "Include house number and street")}
        )
    }

}

@Composable
fun SelectGender(
    selectedGender: String?,
    onSelectGender: () -> Unit,
    onGenderSelected: (String) -> Unit,
    content: @Composable () -> Unit,
    isError: Boolean

) {
    val genderOptions = listOf("Male", "Female")
    var expanded by remember { mutableStateOf(false) }

    // This state variable is used to keep track of the selected gender.
    var selectedGenderIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier.padding(bottom = 17.dp)
    ) {
        Text(
            text = "Gender",
            fontWeight = FontWeight.W500,
            modifier = Modifier.clickable {
                onSelectGender()
            }
        )

        Box(
            modifier = Modifier.clickable { expanded = true },
            content = {
                OutlinedTextFieldWithTrailingIconButton(
                    value = selectedGender ?: "",
                    placeholderText = "Select",
                    bottomPadding = 3,
                    onClick = { expanded = true },
                    isError = isError
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            genderOptions.forEachIndexed { index, gender ->
                DropdownMenuItem(
                    onClick = {
                        selectedGenderIndex = index
                        onGenderSelected(gender)
                        expanded = false
                    }
                ) {
                    Text(text = gender, fontSize = 14.sp)
                }
            }
        }
        content()
    }
}


@Composable
fun OutlinedTextFieldWithTrailingIconButton(
    value: String? = "",
    placeholderText: String,
    bottomPadding: Int = 15,
    onClick: () -> Unit = {}, isError: Boolean = false,
    onTextChanged: (text: String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(bottom = bottomPadding.dp)
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = value ?: "",
            readOnly = true,
            enabled = false, isError = isError,
            onValueChange = { newValue ->
                onTextChanged(newValue)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = Color.White,
            ),
            placeholder = {
                Text(
                    text = placeholderText,
                    fontSize = 14.sp,
//                    color = Color.Black
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(10),
            trailingIcon = {
                IconButton(onClick = {
                    onClick()
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "arrow dropdown"
                    )

                }
            },
//                color = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
                .clickable { onClick() }
        )
    }
}