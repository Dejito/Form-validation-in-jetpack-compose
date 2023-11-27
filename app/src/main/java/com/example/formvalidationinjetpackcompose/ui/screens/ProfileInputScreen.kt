package com.example.formvalidationinjetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var displayGenderDropDown by remember { mutableStateOf(false) }
    val genderErrorMessage = remember { mutableStateOf("") }
    val addressErrorMessage = remember { mutableStateOf("") }
    val emailErrorMessage = remember { mutableStateOf("") }
    val nameErrorMessage = remember { mutableStateOf("") }
    val context = LocalContext.current

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
                .padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Name(
                    value = name,
                    onTextChanged = { name = it },
                    content = {
                        if (nameErrorMessage.value.isNotEmpty()) {
                            Text(
                                text = nameErrorMessage.value,
                                color = MaterialTheme.colors.error,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    }
                )

                Email(
                    value = email,
                    onTextChanged = { email = it },
                    content = {
                        if (emailErrorMessage.value.isNotEmpty()) {
                            Text(
                                text = emailErrorMessage.value,
                                color = MaterialTheme.colors.error,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)

                            )
                        }

                    })

                SelectGender(
                    selectedGender = selectedGender,
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
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    }
                )

                Address(
                    value = address,
                    onTextChanged = { address = it },
                    content = {
                        if (addressErrorMessage.value.isNotEmpty()) {
                            Text(
                                text = addressErrorMessage.value,
                                color = MaterialTheme.colors.error,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    }
                )
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

            Button(
                modifier = Modifier.fillMaxWidth().height(53.dp),
                onClick = {
                    addressErrorMessage.value = ""
                    emailErrorMessage.value = ""
                    nameErrorMessage.value = ""
                    genderErrorMessage.value = ""
                    val isValid = validateInput(
                        address = address,
                        email = email,
                        gender = selectedGender,
                        name = name,
                        addressErrorMessage = addressErrorMessage,
                        emailErrorMessage = emailErrorMessage,
                        nameErrorMessage = nameErrorMessage,
                        genderErrorMessage = genderErrorMessage
                    )
                    if (isValid) {
                        Toast.makeText(context, "Validation passed!", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Text(text = "Validate")
            }

        }
    }
}

private fun validateInput(
    name: String,
    address: String,
    email: String,
    gender: String,
    nameErrorMessage: MutableState<String>,
    addressErrorMessage: MutableState<String>,
    emailErrorMessage: MutableState<String>,
    genderErrorMessage: MutableState<String>,
    ): Boolean {
    var isValid = true

    if (name.isEmpty()) {
        nameErrorMessage.value = "Please provide your names"
        isValid = false
    } else if (name.length < 7) {
        nameErrorMessage.value = "Provided name is too short"
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

    if (gender.isEmpty()) {
        genderErrorMessage.value = "Please select a gender"
        isValid = false
    }

    return isValid
}