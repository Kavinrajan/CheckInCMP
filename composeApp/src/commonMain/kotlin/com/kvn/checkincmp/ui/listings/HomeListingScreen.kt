package com.kvn.checkincmp.ui.listings

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import checkincmp.composeapp.generated.resources.Res
import checkincmp.composeapp.generated.resources.dummy
import checkincmp.composeapp.generated.resources.notifications
import checkincmp.composeapp.generated.resources.user
import coil3.compose.AsyncImage
import com.kvn.checkincmp.navigation.NavRoutes
import com.kvn.checkincmp.theme.Orange
import com.kvn.checkincmp.widgets.MultiHighlightedText
import com.kvn.checkincmp.widgets.TextHighlight
import com.kvn.checkincmp.widgets.TravenorSpacer
import com.kvn.domain.model.TravelListing
import com.kvn.presentation.feature.listings.TravelListingViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeListingScreen(
    backStack: NavBackStack<NavKey>,
    viewModel: TravelListingViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    HomeListingContent(
        isLoading = uiState.value.isLoading,
        errorMessage = uiState.value.errorMessage,
        listings = uiState.value.travelListings,
        onItemClick = { travelListing ->
            backStack.add(NavRoutes.ListingDetails(id = travelListing.id))
        }
    )
}

@Composable
fun HomeListingContent(
    isLoading: Boolean,
    errorMessage: String?,
    listings: List<TravelListing>,
    onItemClick: (TravelListing) -> Unit
) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clip(RoundedCornerShape(32.dp)).background(
                        color = Color.LightGray.copy(alpha = 0.2f)).padding(4.dp)
                ) {
                    Image(painter = painterResource(Res.drawable.user), contentDescription = null)
                    Text("Leonardo", modifier = Modifier.padding(horizontal = 8.dp))
                }

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(Res.drawable.notifications),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(
                        color = Color.LightGray.copy(alpha = 0.2f)).padding(12.dp)
                )
            }
            MultiHighlightedText (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fullText = "Explore the \nbeautiful world!",
                highlights = listOf(
                    TextHighlight(word = "world!", color = Orange, showUnderline = true, fontWeight = FontWeight.Bold),
                    TextHighlight(word = "beautiful", color = Color.Black, showUnderline = false, fontWeight = FontWeight.Bold)),
                fontSize = 44.sp,
                peakHeight = 14.dp,
                thickness = 10.dp
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Best Destination", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                Spacer(Modifier.weight(1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text("See All", color = Orange)
                }
            }
            TravenorSpacer(8.dp)
            if (isLoading) {
                // Placeholder for loading state
                CircularProgressIndicator()
            }
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
            listings.takeIf { it.isNotEmpty() }?.let {
                LazyRow {
                    items(it.size) { index ->
                        DestinationCard(model = it[index]) { travelListing ->
                            onItemClick(travelListing)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationCard(model: TravelListing, onItemClick: (TravelListing) -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .width(250.dp)
            .background(Color.LightGray.copy(alpha = 0.1f), shape = RoundedCornerShape(16.dp))
            .clickable {
                onItemClick(model)
            }
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // FIXED: Moved painterResource from 'model' to 'placeholder', 'error', and 'fallback'
        // to avoid IllegalArgumentException: Unsupported type: Painter.
        AsyncImage(
            model = model.images?.firstOrNull(),
            contentDescription = null,
            modifier = Modifier
                .size(width = 230.dp, height = 300.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(Res.drawable.dummy),
            error = painterResource(Res.drawable.dummy),
            fallback = painterResource(Res.drawable.dummy)
        )

        TravenorSpacer(8.dp)
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text(
                text = model.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "${model.rating}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        TravenorSpacer(5.dp)
        Text(
            text = model.location,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            color = Color.Gray,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeListingScreenGeneratedPreview() {
    MaterialTheme {
        HomeListingContent(
            isLoading = false,
            errorMessage = null,
            listings = listOf(
                TravelListing(
                    id = "1",
                    title = "NiladriLake",
                    location = "Sunamganj, Sylhet, Test",
                    images = null,
                    rating = 4.7
                ),
                TravelListing(
                    id = "2",
                    title = "Dami Lake",
                    location = "Sunamganj, Sylhet",
                    images = null,
                    rating = 4.5
                ),
                TravelListing(
                    id = "3",
                    title = "Bali, Indonesia",
                    location = "Denpasar",
                    images = null,
                    rating = 4.9
                )
            ),
            onItemClick = {}
        )
    }
}
