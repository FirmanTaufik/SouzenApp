package com.efte.souzenapp.presentation.home

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.efte.souzenapp.Greeting
import com.efte.souzenapp.R
import com.efte.souzenapp.core.UIState
import com.efte.souzenapp.ui.theme.BlackColor
import com.efte.souzenapp.ui.theme.PrimaryColor
import com.efte.souzenapp.ui.theme.ShimmerColor
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeScreen(navHostController: NavHostController, homeViewModel: HomeVM, isScrolling : (Boolean)->Unit) {
    val listState = rememberLazyListState()
    isScrolling(  listState.isScrollingUp())
    val state by homeViewModel.homeState.collectAsState()
    when (state) {
        is UIState.OnLoading -> {
            /*  CircularProgressIndicator(color = PrimaryColor)*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = listState,
                userScrollEnabled = true
            ) {
                item { HeaderContent(true) }
                item { LatestContent(true) }
                item { MovieContent(true) }
            }
        }

        is UIState.OnError -> {
            Text(
                text = "Hello ${(state as UIState.OnError).message}!",
            )
        }

        is UIState.OnSuccess<*> -> {
            val data = (state as UIState.OnSuccess<*>).data
            /*Text(
                text = "Hello ${data.toString()}!",
            )*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                item { HeaderContent(false) }
                item { LatestContent(false) }
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                state = listState,
            ) {
                item { HeaderContent(true) }
                item { LatestContent(true) }
                item { MovieContent(true) }
            }
        }
    }

    BackHandler {

    }

}

@Composable
private fun MovieContent(isLoading: Boolean) {
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Movies",
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForward, contentDescription = null,
                    tint = PrimaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            repeat(10) {
               item {  Box(
                   modifier = Modifier
                       .wrapContentHeight()
                       .padding(horizontal = 5.dp)
               ) {
                   Card(
                       modifier = Modifier
                           .width(230.sdp)
                           .shimmer()
                           .height(150.sdp),
                       shape = RoundedCornerShape(15),

                       ) {
                       Row(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(15.dp)
                       ) {
                           Box(
                               modifier = Modifier
                                   .fillMaxHeight()
                                   .width(100.sdp)
                                   .background(
                                       color = ShimmerColor,
                                       shape = RoundedCornerShape(15)
                                   )
                           ) {
                           }
                           Spacer(modifier = Modifier.width(10.dp))
                           Column(
                               modifier = Modifier
                                   .weight(1f)
                                   .fillMaxHeight()
                           ) {

                               Text(
                                   text = "",
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .height(25.sdp)
                                       .background(
                                           color = ShimmerColor,
                                           shape = RoundedCornerShape(15)
                                       ),
                               )
                               Spacer(modifier = Modifier.height(10.dp))
                               Text(
                                   text = "",
                                   modifier = Modifier
                                       .width(60.sdp)
                                       .height(25.sdp)
                                       .background(
                                           color = ShimmerColor,
                                           shape = RoundedCornerShape(15)
                                       ),
                               )
                               Spacer(modifier = Modifier.height(10.dp))
                               repeat(3) {
                                   Spacer(modifier = Modifier.height(5.dp))
                                   Text(
                                       text = "",
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .height(15.sdp)
                                           .background(
                                               color = ShimmerColor,
                                               shape = RoundedCornerShape(15)
                                           ),
                                   )
                               }
                           }
                       }
                   }
               } }
            }
        }
    }
}

@Composable
private fun LatestContent(isLoading: Boolean) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

    Column(modifier = Modifier.wrapContentHeight()) {
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .padding(horizontal = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Episode Update",
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForward, contentDescription = null,
                    tint = PrimaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            if (isLoading) {
                repeat(10) {
                    item {
                        Column(
                            modifier = Modifier
                                .width(100.sdp)
                                .padding(horizontal = 5.dp)
                                .shimmer(shimmerInstance)
                        ) {
                            Card(
                                Modifier
                                    .fillMaxWidth()
                                    .height(130.sdp)
                                    .clip(shape = RoundedCornerShape(15))
                            ) {
                                Box (modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize(),
                                    contentAlignment = Alignment.TopEnd){
                                    Box(
                                        modifier = Modifier
                                            .width(40.sdp)
                                            .height(18.sdp)
                                            .background(
                                                shape = RoundedCornerShape(15),
                                                color = ShimmerColor
                                            ),
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(18.dp)
                                    .background(
                                        shape = RoundedCornerShape(10),
                                        color = ShimmerColor
                                    ),
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .width(60.sdp)
                                    .height(18.sdp)
                                    .background(
                                        shape = RoundedCornerShape(10),
                                        color = ShimmerColor
                                    ),
                            )
                        }

                    }
                }


            }


        }
    }
}

@Composable
private fun HeaderContent(isLoading: Boolean) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    if (isLoading)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(10))
                .shimmer()
                .height(200.sdp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Box(modifier = Modifier.weight(1f))
                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.sdp)
                        .background(color = ShimmerColor, shape = RoundedCornerShape(15)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .width(200.sdp)
                        .height(25.sdp),
                    shape = RoundedCornerShape(15),
                    colors = CardColors(
                        containerColor = ShimmerColor, ShimmerColor, ShimmerColor,
                        ShimmerColor
                    )
                ) {}
            }
        }
    else
        Image(
            imageVector = Icons.Rounded.Person, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(10))
                .height(200.dp)
        )

}

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}
