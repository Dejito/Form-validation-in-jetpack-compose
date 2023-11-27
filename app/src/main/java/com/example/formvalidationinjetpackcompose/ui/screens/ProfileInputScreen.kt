package com.example.formvalidationinjetpackcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.formvalidationinjetpackcompose.ui.composables.SelectGender

@Composable
fun ProfileValidationScreen() {

    var selectedGender by remember { mutableStateOf("") }



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
            SelectGender(
                selectedGender = ,
                onSelectGender = { /*TODO*/ },
                onGenderSelected = {},
                content = { /*TODO*/ },
                isError =
            )
        }
    }
}