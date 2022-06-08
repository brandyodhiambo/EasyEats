package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.screens.destinations.MainScreenDestination
import com.odhiambodevelopers.easyeats.screens.destinations.SignUpDestination
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.odhiambodevelopers.easyeats.ui.theme.lightGreen
import com.odhiambodevelopers.easyeats.ui.theme.white
import com.odhiambodevelopers.easyeats.utils.onBoardingFinished
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun OnboardOne3(navigator: DestinationsNavigator) {
    Column {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp),
            shape = RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp),
            backgroundColor = lightGreen
        ) {
            Image(
                painter = painterResource(R.drawable.foodeat),
                contentDescription = "image one",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = " We also deliver food and drinks to the nearest supermarket,Home and even Schools get your order and let be delivered for free",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            colors = ButtonDefaults.buttonColors(green),
            onClick = {
                navigator.popBackStack()
                navigator.navigate(SignUpDestination)
                context.onBoardingFinished()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Next", color = white)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Skip",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    navigator.popBackStack()
                    navigator.navigate(SignUpDestination)
                    context.onBoardingFinished()
                },
            color = green,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp

        )
        Spacer(modifier = Modifier.padding(8.dp))

    }
}

