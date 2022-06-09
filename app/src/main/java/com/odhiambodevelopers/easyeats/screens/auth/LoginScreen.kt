package com.odhiambodevelopers.easyeats.screens.auth

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.screens.destinations.MainScreenDestination
import com.odhiambodevelopers.easyeats.screens.destinations.SignUpDestination
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState = viewModel.login.value

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val authView = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.ee),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        )
        Text(
            "Login",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Email",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.emailState.value,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Enter Email") },
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Password",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.passwordState.value,
            onValueChange = { viewModel.setPassword(it) },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Forgot Password?",
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .clickable {
                    openDialog.value = true
                }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            colors = ButtonDefaults.buttonColors(green),
            onClick = {
                viewModel.loginUser()
                navigator.popBackStack()
                navigator.navigate(MainScreenDestination)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Sign in", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Don't Have an account Sign up?",
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .clickable {
                    navigator.popBackStack()
                    navigator.navigate(SignUpDestination)
                },
            color = green,

            )
        Spacer(modifier = Modifier.height(4.dp))

        if (authState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

       LaunchedEffect(authState){
           if (authState.error != null) {
               Toast.makeText(context, authState.error.toString(), Toast.LENGTH_SHORT).show()
           }

           if (authState.isSuccessful) {
               Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show()
           }
       }

        if (openDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .height(300.dp)
                    .width(420.dp),
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Forgot Password", color = Color.Black)
                },
                text = {
                    Column {
                        Text(text = "Enter your email to reset password", color = green,)
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                            value = viewModel.emailState.value,
                            onValueChange = { viewModel.setEmail(it) },
                            label = { Text("Enter Email") },
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        },
                    ) {
                        Text("Cancel")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            viewModel.forgotPassword()
                        },
                    ) {
                        Text("Ok")
                    }
                },
            )
        }

    }
}










