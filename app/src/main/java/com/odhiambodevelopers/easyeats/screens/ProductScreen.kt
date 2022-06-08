package com.odhiambodevelopers.easyeats.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import com.odhiambodevelopers.easyeats.model.Menu
import com.odhiambodevelopers.easyeats.model.Product
import com.odhiambodevelopers.easyeats.ui.theme.Colorless
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.ui.theme.green

@Composable
fun AppbarProduct(name:String) {
    TopAppBar(
        title = {
            Text(
                text = name.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack, tint = Color.White, contentDescription = null)
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, tint = Color.White, contentDescription = null )
        },
        backgroundColor = green
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        backgroundColor = green,
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
    ) {

    }
}


@Composable
fun ProductsList(model: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = model.image),
                contentDescription = null
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.background(Colorless),
                    text = model.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = green
                )
                Text(
                    modifier = Modifier.background(Colorless),
                    text = model.description,
                    fontSize = 14.sp,
                    color = Color.Black
                )
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.End
               ){
                   Button(
                       onClick = { /*TODO*/ },
                       modifier = Modifier
                           .fillMaxHeight()
                           .fillMaxWidth(0.4f),
                       shape = RoundedCornerShape(15.dp),
                       colors = ButtonDefaults.buttonColors(green),
                   ) {
                       Text(text = "Buy", color = Color.White)
                   }
               }
            }
        }

    }
}

@Composable
fun ProductData(menu:List<Menu> = emptyList()) {
    val menuList = mutableListOf<Product>()
    menuList.add(Product(R.drawable.drinks,"Drinks","For drinking"))
    menuList.add(Product(R.drawable.foodeat,"Vegetables","Vegetables to consume"))
    menuList.add(Product(R.drawable.foody,"Protein","Good for your health"))

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Menu Name",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(15.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            items(menuList) { model ->
                ProductsList(model = model)
            }
        }
    }

    Text(
        text = "Menu Name",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.width(15.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        items(menuList) { model ->
            ProductsList(model = model)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductScreen() {
    EasyEatsTheme {
        Column {
            AppbarProduct("vero vero")
            ProductData()
        }
    }
}