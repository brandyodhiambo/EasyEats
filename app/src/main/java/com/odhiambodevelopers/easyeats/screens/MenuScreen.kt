package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import com.odhiambodevelopers.easyeats.model.Menu
import com.odhiambodevelopers.easyeats.model.Restaurant
import com.odhiambodevelopers.easyeats.ui.theme.Colorless
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.ui.theme.green

@Composable
fun AppBar(name:String) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name.uppercase(),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                tint = Color.White,
                contentDescription = null
            )
        },
        backgroundColor = green,
        actions = {
            Icon(imageVector = Icons.Default.Search, tint = Color.White, contentDescription = null)
        }

    )

}

@Composable
fun AppBarCard(restaurant: Restaurant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = green,
        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(modifier = Modifier.size(8.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = restaurant.location,
                    fontSize = 11.sp,
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(modifier = Modifier.size(8.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = restaurant.time,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(modifier = Modifier.size(8.dp), onDraw = {
                    drawCircle(color = Color.White)
                })
                Text(
                    text = restaurant.price,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun MenuList(menu: Menu) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .height(200.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = menu.image),
            contentDescription = null
        )
        Text(
            text = menu.name.uppercase(),
            fontSize = 24.sp,
            color = green,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MenuData(menu: List<Menu> = emptyList()) {
    val menuList = listOf(
        Menu(R.drawable.drinks, "Drinks"),
        Menu(R.drawable.foodeat, "Vegetables"),
        Menu(R.drawable.foody, "Protein")
    )


    Spacer(modifier = Modifier.width(15.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(menuList) { model ->
            MenuList(menu = model)
        }
    }
}

@Composable
fun AppbarData(restaurant: List<Restaurant> = emptyList()) {
    val resturantList = listOf(
        Restaurant(R.drawable.rest1, "VERO VERO", 5.0f, "Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest2, "VERO VERO", 5.0f, "Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest3, "VERO VERO", 5.0f, "Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest4, "VERO VERO", 5.0f, "Nairobi,Kenye", "12:45pm", "35600"),
        Restaurant(R.drawable.rest5, "VERO VERO", 5.0f, "Nairobi,Kenye", "12:45pm", "35600"),
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        backgroundColor = green,
        shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
    ){
        LazyRow(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(green)
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
        ) {
            items(resturantList.take(1)) { restaurant ->
                AppBarCard(restaurant)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MenuScreen() {
    EasyEatsTheme {
        Column {
            AppBar(name = "vero vero")
            AppbarData()
            MenuData()
        }
    }
}