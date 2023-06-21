package com.example.cleanemailvalidation.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cleanemailvalidation.ui.theme.CleanEmailValidationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanEmailValidationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<MainViewModel>()
                    val state = viewModel.state
                    val context = LocalContext.current
                    LaunchedEffect(key1 = context) {
                        viewModel.validationEvents.collect { event ->
                            when (event) {
                                is MainViewModel.ValidationEvent.Success -> {
                                    Toast.makeText(
                                        context,
                                        "Registration successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = state.email,
                            onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
                            },
                            isError = state.emailError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Email")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )
                        )
                        if (state.emailError != null) {
                            Text(
                                text = state.emailError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = state.password,
                            onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
                            },
                            isError = state.passwordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if (state.passwordError != null) {
                            Text(
                                text = state.passwordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = state.repeatedPassword,
                            onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                            },
                            isError = state.repeatedPasswordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Repeat password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if (state.repeatedPasswordError != null) {
                            Text(
                                text = state.repeatedPasswordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = state.acceptedTerms,
                                onCheckedChange = {
                                    viewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Accept terms")
                        }
                        if (state.termsError != null) {
                            Text(
                                text = state.termsError,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }

                        Button(
                            onClick = {
                                viewModel.onEvent(RegistrationFormEvent.Submit)
                            },
                            modifier = Modifier.align(Alignment.Start),
                        ) {
                            Text(text = "Submit")
                        }
                    }
                }


            }
        }
    }
}

