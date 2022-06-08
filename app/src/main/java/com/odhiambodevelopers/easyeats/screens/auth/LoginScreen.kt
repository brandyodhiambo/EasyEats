package com.odhiambodevelopers.easyeats.screens.auth

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.screens.destinations.MainScreenDestination
import com.odhiambodevelopers.easyeats.screens.destinations.SignUpDestination
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.odhiambodevelopers.easyeats.ui.theme.lightGreen
import com.odhiambodevelopers.easyeats.utils.AuthResultContract
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


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
        GoogleSignInButtonUi(
            text = "Sign Up With Google",
            loadingText = "Signing In....",
            onClicked = {
                authView.value = true
                Toast.makeText(context, "FEATURE IS BEEN WORKED ON", Toast.LENGTH_LONG).show()
                navigator.popBackStack()
                navigator.navigate(SignUpDestination)

            }
        )

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
                       Text(text = "Enter your email to reset password", color = green, )
                       OutlinedTextField(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(start = 8.dp, end = 8.dp),
                           value = viewModel.emailState.value,
                           onValueChange = { viewModel.setEmail(it) },
                           label = { Text("Enter Email") },
                           shape = RoundedCornerShape(10.dp))
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

        if (authView.value){
            val coroutineScope = rememberCoroutineScope()
            var text by remember { mutableStateOf<String?>(null)}
            val signInRequestCode = 1
            val startForResult =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val intent = result.data
                        if (result.data != null) {
                            val task: Task<GoogleSignInAccount> =
                                GoogleSignIn.getSignedInAccountFromIntent(intent)
                            //handleSignInResult(task)
                        }
                    }
                }

            val authResultLauncher =
                rememberLauncherForActivityResult(contract = AuthResultContract()){
                        task ->
                    try {
                        val account = task?.getResult(ApiException::class.java)
                        if (account==null){
                            text = "Google sign in failed"
                        }else{
                            coroutineScope.launch {
                                account.email?.let { account.displayName?.let { it1 -> viewModel.signIn(email = it,displayName = it1) } }
                               // viewModel.signIn(account.email,account.displayName)
                            }
                        }
                    }catch (e: ApiException){
                        text="Google sign in failed"
                    }
                }
            text?.let {
                AuthView(errorText = it,onClick = {text=null
                    authResultLauncher.launch(signInRequestCode)
                    //startForResult.launch(googleSignInClient?.signInIntent)
                })
            }
        }

    }
}

@Composable
fun ForgotPasswordDialog(viewModel: AuthViewModel) {
    val context = LocalContext.current

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GoogleSignInButtonUi(
    text:String = "",
    loadingText:String = "",
    onClicked:() -> Unit
) {
    var clicked by remember{ mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        onClick = {clicked = !clicked},
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = lightGreen
    ) {
        Row(modifier = Modifier
            .padding(
                start = 12.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 12.dp
            )
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id =R.drawable.google),
                contentDescription = "Google sign button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (clicked) loadingText else text,
                fontSize = 16.sp,
                color = green
            )

            if (clicked){
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
                onClicked()
            }
        }
    }
}

@Composable
fun AuthView(
    errorText: String,
    onClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleSignInButtonUi(
                text = "Sign up with Google",
                loadingText = "Signing in....",
                onClicked = { onClick() }
            )
            errorText.let {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }

    }
}



/*@Preview(showBackground = true)
@Composable
fun login() {
    EasyEatsTheme {
        Login()
    }
}*/
