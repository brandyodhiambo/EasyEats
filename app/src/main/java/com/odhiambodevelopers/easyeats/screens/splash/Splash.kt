package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.screens.destinations.LoginDestination
import com.odhiambodevelopers.easyeats.screens.destinations.MainScreenDestination
import com.odhiambodevelopers.easyeats.screens.destinations.OnboardOneDestination
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.utils.checkIfOnBoardingFinished
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Destination(start = true)
@Composable
fun Splash(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        val context = LocalContext.current
        LaunchedEffect(key1 = true){
            withContext(Dispatchers.Main){
                delay(3000)
                if (context.checkIfOnBoardingFinished()){
                    val user = FirebaseAuth.getInstance().currentUser
                    if ( user!= null && user.isEmailVerified){
                        navigator.popBackStack()
                        navigator.navigate(MainScreenDestination)
                    }else{
                        navigator.popBackStack()
                        navigator.navigate(LoginDestination)
                    }
                } else{
                    navigator.popBackStack()
                    navigator.navigate(OnboardOneDestination)
                }

            }
        }

        Image(painter = painterResource(id = R.drawable.ee), contentDescription = null )
    }
}

