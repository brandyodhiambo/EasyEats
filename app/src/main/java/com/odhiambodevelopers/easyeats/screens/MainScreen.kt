package com.odhiambodevelopers.easyeats.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odhiambodevelopers.easyeats.model.Food
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MainScreen(navigator: DestinationsNavigator) {
    Column() {
        ToolBar()
        CategoryList(navigator)
        LazyColumn {
            item {
                ContentOne()
            }
            item {
                ContentTwo()
            }
        }
    }
}



@Composable
fun ToolBar() {
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
fun CategoryList(navigator: DestinationsNavigator) {
    val hotels = listOf(
        "Restaurants",
        "Coffee Shops",
        "Resorts",
        "Motel",
        "City Center"
    )
    var selectedItem by remember{mutableStateOf( "")}
    val context = LocalContext.current
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(8.dp),
    ) {
        this.items(items = hotels.take(5)) { hotels ->
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = lightGreen,
                contentColor = green,
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(8.dp)

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = selectedItem.equals(hotels[3]) ,
                        onClick = { selectedItem = hotels[3].toString() }
                    )

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = hotels,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                    if (selectedItem.equals(hotels[0])) {
                        navigator.popBackStack()
                        Toast.makeText(context, "Resturant", Toast.LENGTH_LONG).show()

                        // navigator.navigate(ResturantScreen())
                    } else if (selectedItem.equals(hotels[1])){
                        Toast.makeText(context, "Coffee Shop", Toast.LENGTH_LONG).show()

                    }
                    else if (selectedItem.equals(hotels[2])){
                        Toast.makeText(context, "Resorts", Toast.LENGTH_LONG).show()
                    }
                    else if (selectedItem.equals(hotels[3])){
                        Toast.makeText(context, "Motel", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(context, "City Center", Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }
}

@Composable
fun ContentOne() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Popular Food",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = "View All",
                fontSize = 16.sp,
                textAlign = TextAlign.Right,
                color = green,

                )

        }
    }
    val foodList = mutableListOf<Food>()
    foodList.add(Food(R.drawable.drinks, "Sodas", "For Cool weather"))
    foodList.add(Food(R.drawable.foody, "Vegetables", "For Cool weather"))
    foodList.add(Food(R.drawable.foodeat, "Protein", "For Cool weather"))
    Spacer(modifier = Modifier.width(15.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(foodList) { model ->
            ListRow(model = model)
        }
    }

}

@Composable
fun ContentTwo() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Special Offers",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = "View All",
                fontSize = 16.sp,
                textAlign = TextAlign.Right,
                color = green,

                )

        }
    }
    val foodList = mutableListOf<Food>()
    foodList.add(Food(R.drawable.drinks, "Sodas", "For Cool weather"))
    foodList.add(Food(R.drawable.foody, "Vegetables", "For Cool weather"))
    foodList.add(Food(R.drawable.foodeat, "Protein", "For Cool weather"))
    Spacer(modifier = Modifier.width(15.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(foodList) { model ->
            ListRow(model = model)
        }
    }

}

//row
@Composable
fun ListRow(model: Food) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight()
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = model.image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)

            )
            Text(
                modifier = Modifier.background(Colorless),
                text = model.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                modifier = Modifier.background(Colorless),
                text = model.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun mainScreen() {
    EasyEatsTheme {
        Column() {
            ToolBar()
            CategoryList()
            LazyColumn {
                item {
                    ContentOne()
                }
                item {
                    ContentTwo()
                }
            }
        }
    }
}*/
