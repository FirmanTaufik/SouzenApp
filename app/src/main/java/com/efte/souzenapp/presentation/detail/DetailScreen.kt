package com.efte.souzenapp.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.efte.souzenapp.route.ScreenRoute
import com.efte.souzenapp.ui.theme.BlackColor
import com.efte.souzenapp.ui.theme.PrimaryColor
import com.efte.souzenapp.ui.theme.SemiBlackColor
import com.efte.souzenapp.ui.theme.ShimmerColor
import com.efte.souzenapp.ui.theme.WhiteColor
import ir.kaaveh.sdpcompose.sdp
import java.lang.Float.min

@Composable
fun DetailScreen(navHostController: NavHostController) {
    var showMore by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    ConstraintLayout() {

        Box(
            Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            AsyncImage(
                model = "https://i.pinimg.com/originals/a1/1b/0e/a11b0e6f27a1816bfef6009ec44454fd.jpg",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = min(1f, 1 - (scrollState.value / 600f))
                        translationY = -scrollState.value * 0.1f
                    },
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                if (isSystemInDarkTheme()) BlackColor else WhiteColor
                            ),
                            0f,
                            900f,
                        )
                    )
            ) {

            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(300.dp))
            Text(
                text = "1h 20m", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "18+",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Action",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = null,
                                tint = Color.Yellow
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "8.5", fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
                Row {
                    Box(

                        modifier = Modifier
                            .border(3.dp, ShimmerColor, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = ShimmerColor,
                        )

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Rounded.Share, contentDescription = null)

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Deadpool 3", fontWeight = FontWeight.Bold, fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Deadpool & Wolverine adalah sebuah film pahlawan super Amerika Serikat tahun 2024 berdasarkan karakter dari Marvel Comics dengan nama yg sama, diproduksi bersama oleh Marvel Studios, Maximum Effort, dan 21 Laps Entertainment, dan didistribusikan Walt Disney Studios Motion Pictures",
                overflow = TextOverflow.Ellipsis,
                maxLines = if (showMore) Int.MAX_VALUE else 4
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        showMore = !showMore
                    },
                ) {
                    Icon(
                        imageVector = if (showMore) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Actor", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                repeat(10) {
                    item {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = if (it > 0) 10.dp else 0.dp)
                        ) {
                            AsyncImage(
                                model = "https://cdn-icons-png.flaticon.com/512/3282/3282224.png",
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.sdp)
                                    .background(color = ShimmerColor, RoundedCornerShape(10.dp))
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Episode", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                repeat(20) {
                    Button(
                        onClick = {
                            navHostController.navigate(ScreenRoute.WatchAnime.route)
                        }, modifier = Modifier
                            .padding(
                                horizontal = 10.dp,
                                vertical = 5.dp
                            )
                            .fillMaxWidth(),
                        colors = ButtonColors(
                            PrimaryColor,
                            PrimaryColor,
                            PrimaryColor,
                            PrimaryColor
                        )
                    ) {
                        Text(
                            text = "Episode $it",
                            color = if (isSystemInDarkTheme()) BlackColor else WhiteColor
                        )
                    }
                }
            }
        }
    }
}