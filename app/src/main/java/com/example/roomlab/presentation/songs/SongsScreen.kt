package com.example.roomlab.presentation.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.roomlab.domain.model.Song
import com.example.roomlab.domain.model.sortAlphabetically
import com.example.roomlab.presentation.common.LoadingLayout
import com.example.roomlab.presentation.utilities.randomColor
import com.example.roomlab.ui.theme.RoomLabTheme

@Composable
fun SongsRoute(
    viewModel: SongsScreenViewModel = viewModel(factory = SongsScreenViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val songs by viewModel.songs.collectAsStateWithLifecycle(emptyList())
    SongsScreen(
        songs = songs.sortAlphabetically(),
        songsScreenState = state,
        onFavClick = viewModel::onFavClick
    )
}

@Composable
private fun SongsScreen(
    songs: List<Song>,
    songsScreenState: SongsScreenState,
    onFavClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    if (songsScreenState.isLoading) {
        LoadingLayout(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyColumn (
            modifier = modifier
        ){
            items(songs) { song ->
                SongItem(
                    song = song,
                    onFavClick = onFavClick,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun SongItem(
    song: Song,
    onFavClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Spacer(modifier = Modifier.size(8.dp))
    Row (
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = song.color)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = song.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.artistName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.genre,
                style = MaterialTheme.typography.labelSmall
            )
        }
        IconButton(
            onClick = {
                onFavClick(
                    song.id,
                    song.isFavorite
                )
            }
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
}

@Preview
@Composable
private fun PreviewSongsScreen() {
    RoomLabTheme {
        Surface {
            SongsScreen(
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
                songsScreenState = SongsScreenState(
                    isLoading = false,
                ),
                onFavClick = { _, _ -> }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSongsScreenLoading() {
    RoomLabTheme {
        Surface {
            SongsScreen(
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
                songsScreenState = SongsScreenState(
                    isLoading = true,
                ),
                onFavClick = { _, _ -> }
            )
        }
    }
}