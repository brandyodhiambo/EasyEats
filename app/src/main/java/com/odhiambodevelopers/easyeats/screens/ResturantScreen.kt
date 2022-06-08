package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.model.Restaurant
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.odhiambodevelopers.easyeats.ui.theme.white
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ResturantScreen() {
    Column {
        ToolBarResturant()
        LazyColumn{
            item {
                ResturantData()
            }
        }
    }
}
@Composable
fun ToolBarResturant() {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .height(70.dp)
                .background(white)
                .fillMaxWidth()
                .fillMaxHeight(),
            value = text,
            onValueChange = { text = it },
            label = {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("What are you looking for?")
                }
            },
            shape = RoundedCornerShape(12.dp)
        )

    }
}

@Composable
fun ResturantData(restaurant: List<Restaurant> = emptyList()) {

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
            CardItem(restaurant)
        }
    }
}


@Composable
fun CardItem(restaurant: Restaurant) {
    Box( modifier= Modifier
        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        .clip(RoundedCornerShape(8.dp))
        .height(230.dp)) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = restaurant.image),
            contentDescription = null,
        )
        RatingCard(restaurant.rating)
        CardItemDetails(
            name = restaurant.name,
            location = restaurant.location,
            time = restaurant.time,
            price =  restaurant.price
        )
    }
}

@Composable
fun RatingCard(rating: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
            backgroundColor = green,
            shape = RoundedCornerShape(15.dp)
        ){
            Row(
                modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 5.dp),
            ) {
                Icon(imageVector = Icons.Default.Star, tint = Color.White, contentDescription =null )
                Text(text = rating.toString(), color = Color.White)
            }
        }
        Icon(
            modifier = Modifier
                .padding(top = 24.dp, end = 24.dp, bottom = 5.dp)
                .size(30.dp),
            imageVector = Icons.Default.Favorite,
            tint = Color.White,
            contentDescription = null
        )
    }

}

@Composable
fun CardItemDetails(
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