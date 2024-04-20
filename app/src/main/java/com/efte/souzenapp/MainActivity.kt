package com.efte.souzenapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.efte.souzenapp.core.UIState
import com.efte.souzenapp.presentation.about.AboutScreen
import com.efte.souzenapp.presentation.detail.DetailScreen
import com.efte.souzenapp.presentation.favorite.FavoriteScreen
import com.efte.souzenapp.presentation.home.HomeScreen
import com.efte.souzenapp.presentation.home.HomeVM
import com.efte.souzenapp.presentation.watch.WatchScreen
import com.efte.souzenapp.route.ScreenRoute
import com.efte.souzenapp.ui.theme.SouzenAppTheme
import com.efte.souzenapp.utils.BottomBarTab
import com.efte.souzenapp.utils.BottomNavigation
import com.efte.souzenapp.utils.tabs
import dev.chrisbanes.haze.HazeState

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeVM by viewModels()
            val navController = rememberNavController()
            val hazeState = remember { HazeState() }
            var routeState by remember { mutableStateOf<BottomBarTab>(BottomBarTab.Home) }
            var isShowBottomBar by remember { mutableStateOf(true) }
            SouzenAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        floatingActionButton = {
                            AnimatedVisibility(visible = isShowBottomBar, enter =  fadeIn(),
                                exit = fadeOut()
                            ) {
                                BottomNavigation(hazeState) {
                                    routeState = it
                                    navController.navigate(it.title)
                                }
                            }

                        }
                    ) {

                        navController.addOnDestinationChangedListener { controller, destination, arguments ->
                            println("addOnDes ${destination.route}")
                            val list = tabs.map {  title }
                            isShowBottomBar = destination.route in list
                        }

                        Box(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = BottomBarTab.Home.title
                            ) {

                                composable(BottomBarTab.Home.title) {
                                    LaunchedEffect(key1 = true) {
                                       /* if (homeViewModel.homeState.value !is UIState.OnSuccess<*>) {
                                            homeViewModel.getHome()
                                        }*/

                                    }
                                    HomeScreen(navController, homeViewModel){
                                        println("isScroll $it")
                                        isShowBottomBar = it
                                    }
                                }


                                composable(BottomBarTab.About.title) {
                                    AboutScreen()
                                }

                                composable(BottomBarTab.Favorite.title) {
                                    FavoriteScreen()
                                }

                                composable(ScreenRoute.DetailAnime.route){
                                    isShowBottomBar =false
                                    DetailScreen(navController)
                                }
                                composable(ScreenRoute.WatchAnime.route) {
                                    WatchScreen()
                                }

                            }

                            // NavigationMenuRoute(routeState, navController, homeViewModel)
                        }
                    }
                }
            }
        }
    }


}

@Composable
private fun NavigationMenuRoute(
    routeState: BottomBarTab,
    navController: NavHostController,
    homeViewModel: HomeVM
) {
    when (routeState) {
        is BottomBarTab.Favorite -> {
            FavoriteScreen()
        }

        is BottomBarTab.About -> {
            AboutScreen()
        }

        else -> {
            LaunchedEffect(key1 = true) {
                Log.d("TAG", "onCreate: HOME")
                if (homeViewModel.homeState.value !is UIState.OnSuccess<*>) {
                    homeViewModel.getHome()
                }

            }
            HomeScreen(navController, homeViewModel){
                println("isScroll $it")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SouzenAppTheme {
        Greeting("Android")
    }
}