package com.kvn.checkincmp.ui.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.kvn.presentation.feature.prelogin.AuthNavigation
import com.kvn.presentation.feature.prelogin.RegisterViewModel
import com.kvn.presentation.feature.prelogin.SignInRegUiState
import com.kvn.checkincmp.navigation.NavRoutes
import com.kvn.checkincmp.widgets.TravenorCircleImageButton
import com.kvn.checkincmp.widgets.TravenorSpacer
import com.kvn.checkincmp.widgets.TravenorTextField
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(backStack: NavBackStack<NavKey>, viewModel: RegisterViewModel = koinViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val name by viewModel.firstName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()

    LaunchedEffect(true) {
        viewModel.navigationState.collectLatest {
            when (it) {
                is AuthNavigation.ToListing -> {
                    backStack.add(NavRoutes.Listing)
                }

                is AuthNavigation.ToLogin -> {
                    backStack.remove(NavRoutes.SignUp)
                }

                else -> {}
            }
        }
    }

    SignUpContent(
        uiState = uiState,
        name = name,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        onNameChange = { viewModel.onNameChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onConfirmPasswordChange = { viewModel.onConfirmPasswordChange(it) },
        onSignUp = { viewModel.register() },
        onSignInClick = { viewModel.navigateToLogin() },
        onBackClick = {}
    )
}

@Composable
fun SignUpContent(
    uiState: SignInRegUiState,
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    onSignInClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold {
        var passwordVisibility by remember { mutableStateOf(false) }
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            TravenorCircleImageButton(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow",
                modifier = Modifier,
                onClick = onBackClick
            )

            TravenorSpacer(20.dp)
            Text(
                "Sign up now", modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            Text(
                "Please sign up to continue our app",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            TravenorSpacer(26.dp)

            TravenorTextField(
                name, onValueChange = onNameChange,
                modifier = Modifier,
                placeholder = {
                    Text(
                        "Full Name",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                },
            )

            TravenorSpacer(16.dp)


            TravenorTextField(
                email, onValueChange = onEmailChange,
                modifier = Modifier,
                placeholder = {
                    Text(
                        "Email Address",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                },
            )

            TravenorSpacer(16.dp)

            TravenorTextField(
                password, onValueChange = onPasswordChange,
                modifier = Modifier,
                placeholder = {
                    Text(
                        "Password",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        imageVector = if (!passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(48.dp).padding(12.dp).clickable {
                            passwordVisibility = !passwordVisibility
                        }
                    )
                }
            )
            TravenorSpacer(16.dp)

            TravenorTextField(
                confirmPassword, onValueChange = onConfirmPasswordChange,
                modifier = Modifier,
                placeholder = {
                    Text(
                        "Confirm Password",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        imageVector = if (!passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(48.dp).padding(12.dp).clickable {
                            passwordVisibility = !passwordVisibility
                        }
                    )
                }
            )
            TravenorSpacer(16.dp)

            AnimatedVisibility(uiState.isLoading) {
                CircularProgressIndicator()
            }
            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sign Up", modifier = Modifier.padding(vertical = 8.dp))
            }

            uiState.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account?",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                TextButton(onClick = onSignInClick) {
                    Text("Sign In", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpContent(
            uiState = SignInRegUiState(),
            name = "John Doe",
            email = "john@example.com",
            password = "password",
            confirmPassword = "password",
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onSignUp = {},
            onSignInClick = {},
            onBackClick = {}
        )
    }
}
