package com.example.formvalidationinjetpackcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.formvalidationinjetpackcompose.ui.composables.Address
import com.example.formvalidationinjetpackcompose.ui.composables.Email
import com.example.formvalidationinjetpackcompose.ui.composables.Name
import com.example.formvalidationinjetpackcompose.ui.composables.SelectGender
import java.util.regex.Pattern

@Composable
fun ProfileValidationScreen() {

    var selectedGender by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedState by remember { mutableStateOf("") }
    var displayGenderDropDown by remember { mutableStateOf(false) }
    var displayState by remember { mutableStateOf(false) }
    val genderErrorMessage = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = "Profile Validation",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
        ) {

            Name(
                value = name,
                onTextChanged = { name = it },
                content = {

                }
            )

            Email(
                value = email,
                onTextChanged = { email = it },
                content = {})

            SelectGender(
                selectedGender = selectedGender,
                onSelectGender = { displayGenderDropDown = true },
                isError = genderErrorMessage.value.isNotEmpty(),
                onGenderSelected = { gender ->
                    selectedGender = gender
                    displayGenderDropDown = false // Close the dropdown after selecting a gender
                },
                content = {
                    if (genderErrorMessage.value.isNotEmpty()) {
                        Text(
                            text = genderErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                        )
                    }
                }
            )

            Address(
                value = address,
                onTextChanged = { address = it },
                content = {

                }
            )

            Button(
                onClick = {
                    var isValid =
                }
            ) {
                Text(text = "Validate")
            }

            //todo: you can uncomment this and use as further test if you may
//            SelectState(
//                value = selectedState,
//                expanded = ,
//                onChangeText = ,
//                onSelectState = {  },
//                content = { },
//                isError =
//            )

        }
    }
}

private fun validateInput(
    name: String,
    address: String,
    email: String,
    state: String,
    nameErrorMessage: MutableState<String>,
    addressErrorMessage: MutableState<String>,
    emailErrorMessage: MutableState<String>,
    stateErrorMessage: MutableState<String>,
    ): Boolean {
    var isValid = true
    if (name.length < 7) {
        nameErrorMessage.value = "Please provide a valid name"
        isValid = false
    }

    if (address.length < 10) {
        addressErrorMessage.value = "Please provide a valid address"
        isValid = false
    }

    if (email.length < 6 || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
        emailErrorMessage.value = "Please provide a valid email address"
        isValid = false
    }

    if (state.isEmpty()) {
        stateErrorMessage.value = "Please select a State"
        isValid = false
    }

    return isValid
}