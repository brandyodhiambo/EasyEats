package com.odhiambodevelopers.easyeats.screens.user

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UserScreen(
    navigator: DestinationsNavigator,
    viewModel: UserViewModel = hiltViewModel()
) {
    val imageUri = viewModel.imageUri.value
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) {
        it?.let { it1 ->
            viewModel.setImageUri(it1)
            viewModel.uploadImage(it1)
        }
    }
    Column {
        ProfileAppBar()

        Box {
            val imageOffset = 40.dp
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = imageOffset),
                backgroundColor = green,
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
            ) {

            }
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(
                    data = imageUri,
                    builder = {
                        placeholder(R.drawable.woman)
                    }

                ),
                contentDescription = null,
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        galleryLauncher.launch("image/*")
                    }
                    .align(Alignment.BottomCenter),
                imageVector = Icons.Default.Edit,
                tint = green,
                contentDescription = null
            )
        }

        UserDetails()
    }
}

@Composable
fun ProfileAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "My Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = null
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Settings,
                tint = Color.White,
                contentDescription = null
            )
        },
        backgroundColor = green
    )
}

@Composable
fun ImageCard(
    onClick: () -> Unit = {},
    imageUri: Uri?
) {

}


@Composable
fun UserDetails() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Brandy Ariana",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp,

            ) {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "My Address", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "FAQ", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "About", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Help", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Exit", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun ImageCard(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    Box {
        val imagePainter = painterResource(id = R.drawable.woman)
        val imageOffset = 40.dp
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = imageOffset),
            backgroundColor = green,
            shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
        ) {

        }
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.BottomCenter)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Icon(
            modifier = Modifier
                .clickable {
                    galleryLauncher.launch("image/*")
                }
                .align(Alignment.BottomCenter),
            imageVector = Icons.Default.Edit,
            tint = green,
            contentDescription = null
        )
    }
}


/*@Preview(showBackground = true)
@Composable
fun userProfile() {
    EasyEatsTheme{
        Column {
            ProfileAppBar()
            ImageCard()
            UserDetails()
        }
    }
}*/


