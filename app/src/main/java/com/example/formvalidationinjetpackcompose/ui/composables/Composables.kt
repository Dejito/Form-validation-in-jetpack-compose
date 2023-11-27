package com.example.formvalidationinjetpackcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight



@Composable
fun Name(
    value: String, onTextChanged: (String) -> Unit, bottomPadding: Int = 15,
    content: @Composable () -> Unit, isError: Boolean = false,

    ) {
    Column(modifier = Modifier.padding(bottom = bottomPadding.dp)) {
        TitleText(text = "Input name")

        OutlinedTextField(
            value = value,
            onValueChange = onTextChanged,
            placeholder = { Text(text = "Include first name and last name") },
            shape = RoundedCornerShape(10),
            isError = isError
        )
        content()
    }
}

@Composable
fun Email(
    value: String, onTextChanged: (String) -> Unit, bottomPadding: Int = 15,
    content: @Composable () -> Unit, isError: Boolean = false,

    ) {
    Column(modifier = Modifier.padding(bottom = bottomPadding.dp)) {
        TitleText(text = "Input email")

        OutlinedTextField(
            value = value,
            onValueChange = onTextChanged,
            placeholder = { Text(text = "Please provide a valid email address") },
            shape = RoundedCornerShape(10),
            isError = isError
        )
        content()
    }
}

@Composable
fun SelectGender(
    selectedGender: String?,
    onSelectGender: () -> Unit,
    onGenderSelected: (String) -> Unit,
    content: @Composable () -> Unit,
    isError: Boolean,

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
//            modifier = Modifier.clickable {
//                onSelectGender()
//            }
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
fun Address(
    value: String, onTextChanged: (String) -> Unit, bottomPadding: Int = 15,
    content: @Composable () -> Unit, isError: Boolean = false,

    ) {
    Column(modifier = Modifier.padding(bottom = bottomPadding.dp)) {
        TitleText(text = "Input address")
        OutlinedTextField(
            value = value,
            onValueChange = onTextChanged,
            placeholder = { Text(text = "Include house number and street") },
            shape = RoundedCornerShape(10),
            isError = isError
        )
        content()
    }
}


@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.paddingFromBaseline(bottom = 5.dp)
    )
}

@Composable
fun OutlinedTextFieldWithTrailingIconButton(
    value: String,
    placeholderText: String,
    bottomPadding: Int = 15,
    onClick: () -> Unit = {}, isError: Boolean = false,
    onTextChanged: (text: String) -> Unit = {},
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


//@Composable
//fun SelectState(
//    value: String,
//    expanded: MutableState<Boolean>,
////    listItems: ArrayList<String>,
//    onChangeText: (String) -> Unit,
//    onSelectState: () -> Unit,
//    content: @Composable () -> Unit, isError: Boolean,
//
//    ) {
//    Column(
//        modifier = Modifier.padding(bottom = 17.dp)
//    ) {
//        Text(
//            text = "State", fontWeight = FontWeight.W500
//        )
//        OutlinedTextFieldWithTrailingIconButton(
//            value = value,
//            placeholderText = "Select a state",
//            bottomPadding = 3,
//            onClick = onSelectState,
//            onTextChanged = onChangeText, isError = isError,
//
//            )
//
//        DropdownMenu(
//            modifier = Modifier
//                .height(550.dp)
//                .width(160.dp),
////                .onGloballyPositioned { coordinates ->
////                    // This value is used to assign to
////                    // the DropDown the same width
//////                    mTextFieldSize = coordinates.size.toSize()
////                },
//            expanded = expanded.value,
//            onDismissRequest = {
//                expanded.value = false
//            }) {
//            statesInNigeria.forEachIndexed { _, itemValue ->
//                DropdownMenuItem(
//                    onClick = {
//                        onChangeText(itemValue)
//                        expanded.value = false
//                    },
//                    content = {
//                        Text(
//                            text = itemValue,
//                            modifier = Modifier.clickable {
//                                onChangeText(itemValue)
//                                expanded.value = false
//                            }
//                                .padding(bottom = 15.dp)
//                        )
//                    }
//                )
//            }
//        }
//        content()
//    }
//}

//val statesInNigeria = arrayListOf(
//    "Abia",
//    "Adamawa",
//    "Akwa Ibom",
//    "Anambra",
//    "Bauchi",
//    "Bayelsa",
//    "Benue",
//    "Borno",
//    "Cross River",
//    "Delta",
//    "Ebonyi",
//    "Edo",
//    "Ekiti",
//    "Enugu",
//    "Gombe",
//    "Imo",
//    "Jigawa",
//    "Kaduna",
//    "Kano",
//    "Katsina",
//    "Kebbi",
//    "Kogi",
//    "Kwara",
//    "Lagos",
//    "Nasarawa",
//    "Niger",
//    "Ogun",
//    "Ondo",
//    "Osun",
//    "Oyo",
//    "Plateau",
//    "Rivers",
//    "Sokoto",
//    "Taraba",
//    "Yobe",
//    "Zamfara"
//)