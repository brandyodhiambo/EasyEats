package com.odhiambodevelopers.easyeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.odhiambodevelopers.easyeats.screens.NavGraphs
import com.odhiambodevelopers.easyeats.ui.theme.EasyEatsTheme
import com.odhiambodevelopers.easyeats.ui.theme.green
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navHostEngine = rememberNavHostEngine()

                    val bottomNavigationItems: List<BottomNavItem> = listOf(
                        BottomNavItem.MainScreen,
                        BottomNavItem.FavoriteScreen,
                        BottomNavItem.UserScreen,
                        BottomNavItem.ResturantScreen
                    )

                    Scaffold(
                        bottomBar = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            val route = navBackStackEntry?.destination?.route

                            bottomNavigationItems.forEach { item ->
                                if (item.destination.route == route) {
                                    BottomNavigation(
                                        backgroundColor = Color.White,
                                        contentColor = Color.Black
                                    ) {
                                        bottomNavigationItems.forEach { item ->
                                            BottomNavigationItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = item.icon,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = {
                                                    Text(text = item.title)
                                                },
                                                alwaysShowLabel = false,
                                                selectedContentColor = green,
                                                unselectedContentColor = Color.LightGray,
                                                selected = currentDestination?.route?.contains(item.destination.route) == true,
                                                onClick = {
                                                    navController.navigate(item.destination.route) {
                                                        navController.graph.startDestinationRoute?.let { screenRoute ->
                                                            popUpTo(screenRoute) {
                                                                saveState = true
                                                            }
                                                        }
                                                        launchSingleTop = true
                                                        restoreState = true
                                                    }
                                                }
                                            )

                                        }
                                    }
                                }
                            }
                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = navController,
                                engine = navHostEngine
                            )
                        }

                    }


                }
            }
        }
    }
}

