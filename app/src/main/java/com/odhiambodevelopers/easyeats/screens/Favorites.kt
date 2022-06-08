package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.model.Restaurant
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Favorites(navigator: DestinationsNavigator) {
    Column() {
        AppBarFavorites()
        ResturantFavorite()
        /*LazyColumn{
            items(){

            }
        }*/

    }
}

@Composable
fun AppBarFavorites() {
    TopAppBar(
        title = {
            Text(
                text = "Favorites",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ChevronLeft, tint = Color.White, contentDescription = null )
        },
        actions = {
            Icon(imageVector = Icons.Default.Delete, tint = Color.White, contentDescription = null)
        },
        backgroundColor = green
    )
}

@Composable
fun CardFavorite(restaurant: Restaurant) {
    Box( modifier= Modifier
        .padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp)
        .clip(RoundedCornerShape(8.dp))
        .height(200.dp)) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = restaurant.image),
            contentDescription = null,
        )
        CardItemFavorites(
            name = restaurant.name,
            location = restaurant.location,
            time = restaurant.time,
            price =  restaurant.price
        )
    }
}

@Composable
fun CardItemFavorites(
    modifier: Modifier = Modifier,
    name:String,
    location:String,
    price:String,
    time:String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name.uppercase(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            Row(
                modifier =Modifier.padding(10.dp)
            ) {
                Canvas(modifier = Modifier.size(15.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = location,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }

            Row(
                modifier =Modifier.padding(10.dp)
            ) {
                Canvas(modifier = Modifier.size(15.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }

            Row(
                modifier =Modifier.padding(10.dp)
            ) {
                Canvas(modifier = Modifier.size(15.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = price,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun ResturantFavorite(restaurant: List<Restaurant> = emptyList()) {

    val resturantList = listOf(
        Restaurant(R.drawable.rest1, "VERO VERO",5.0f ,"Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest2, "VERO VERO",5.0f ,"Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest3, "VERO VERO",5.0f ,"Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest4, "VERO VERO",5.0f ,"Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest5, "VERO VERO",5.0f ,"Nairobi,Kenye", "12:45pm", "35600"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(resturantList) { restaurant ->
            CardFavorite(restaurant)
        }
    }
}







/*
@Preview(showBackground = true)
@Composable
fun FavoriteScreen() {
    EasyEatsTheme {
        Column {
            AppBarFavorites()
            ResturantFavorite()
        }
    }
}*/
