package com.odhiambodevelopers.easyeats.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.odhiambodevelopers.easyeats.screens.destinations.LoginDestination
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = false)
@Composable
fun SignUp(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val authState = viewModel.register.value

    val context = LocalContext.current

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(R.drawable.ee),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        )
        Text(
            "Sign Up",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Name",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.nameState.value,
            onValueChange = { viewModel.setName(it) },
            label = { Text("Enter Full Name") },
            shape = RoundedCornerShape(10.dp)
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

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Phone Number",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.phoneState.value,
            onValueChange = { viewModel.setPhone(it) },
            label = { Text("Enter Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Password",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        TextField(
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
            "Confirm Password",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.confirmPasswordState.value,
            onValueChange = { viewModel.setConfirmPassword(it) },
            label = { Text("Confirm password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Button(
            colors = ButtonDefaults.buttonColors(green),
            onClick = {
                viewModel.registerUsers()
               navigator.popBackStack()
                navigator.navigate(LoginDestination)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Sign Up", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Already Have an account signIn?",
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    navigator.popBackStack()
                    navigator.navigate(LoginDestination)
                },
            color = green,
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (authState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(CenterHorizontally)
            )
        }

        LaunchedEffect(authState){
            if (authState.error != null) {
                Toast.makeText(context, authState.error.toString(), Toast.LENGTH_SHORT).show()
            }

            if (authState.isSuccessful) {
                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            }
        }



    }
}

/*@Preview(showBackground = true)
@Composable
fun signUp() {
    EasyEatsTheme {
        SignUp()
    }
}*/
