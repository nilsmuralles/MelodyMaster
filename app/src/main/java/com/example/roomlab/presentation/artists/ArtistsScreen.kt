package com.example.roomlab.presentation.artists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomlab.domain.model.Artist
import com.example.roomlab.domain.model.Song
import com.example.roomlab.presentation.common.LoadingLayout
import com.example.roomlab.presentation.utilities.randomColor
import com.example.roomlab.ui.theme.RoomLabTheme

@Composable
fun ArtistsRoute(
    viewModel: ArtistsScreenViewModel = viewModel(factory = ArtistsScreenViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val artists by viewModel.artists.collectAsStateWithLifecycle(emptyList())
    ArtistsScreen(
        artists = artists,
        artistsScreenState = state,
        onFavClick = viewModel::onFavClick
    )
}

@Composable
private fun ArtistsScreen(
    artists: List<Artist>,
    artistsScreenState: ArtistsScreenState,
    onFavClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    if (artistsScreenState.isLoading) {
        LoadingLayout()
    } else {
        LazyColumn(modifier = modifier) {
            items(artists) { artist ->
                ArtistItem(
                    artist = artist,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp),
                    onFavClick = onFavClick
                )
            }
        }
    }
}

@Composable
private fun ArtistItem(
    artist: Artist,
    modifier: Modifier = Modifier,
    onFavClick: (Int, Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = artist.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = artist.monthlyListeners.toString()
            )

        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(artist.songs) { song ->
                ArtistSongItem(
                    song = song,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onFavClick = onFavClick
                )
            }
        }
    }
}

@Composable
private fun ArtistSongItem(
    song: Song,
    modifier: Modifier = Modifier,
    onFavClick: (Int, Boolean) -> Unit
) {
    OutlinedCard (
        modifier = modifier
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = song.color)
            )
            IconButton(
                onClick = {
                    onFavClick(
                        song.id,
                        song.isFavorite
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                if (song.isFavorite) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                } else {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        }
        Column (
            modifier = Modifier
                .width(180.dp)
                .padding(10.dp)
        ){
            Text(
                text = song.name,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = song.genre,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewArtistsScreen(){
    RoomLabTheme {
        Surface {
            ArtistsScreen(
                artists = listOf(
                    Artist(
                        name = "Juan Carlos Durini",
                        songs = listOf(
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = true
                            ),
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle ft Juna Carlos",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = false
                            ),
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle ft Juna Carlos",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = true
                            )
                        ),
                        monthlyListeners = 4_000_000
                    ),
                    Artist(
                        name = "Juan Carlos Durini",
                        songs = listOf(
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = false
                            ),
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle ft Juna Carlos",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = false
                            ),
                            Song(
                                id = 1,
                                name = "Himno de Guatemala",
                                artistName = "Rafael Álvarez Ovalle ft Juna Carlos",
                                color = randomColor(),
                                genre = "Himno",
                                isFavorite = true
                            )
                        ),
                        monthlyListeners = 4_000_000
                    )
                ),
                artistsScreenState =  ArtistsScreenState(
                    isLoading = false,
                ),
                onFavClick = { _, _ -> }
            )
        }
    }
}