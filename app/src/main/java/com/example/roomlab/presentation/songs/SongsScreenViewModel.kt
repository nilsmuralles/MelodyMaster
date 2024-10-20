package com.example.roomlab.presentation.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomlab.data.local.dao.SongDAO
import com.example.roomlab.data.repository.LocalSongRepository
import com.example.roomlab.di.Dependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SongsScreenViewModel(
    private val songDAO: SongDAO
): ViewModel() {
    private val localSongRepository = LocalSongRepository(
        songDAO = songDAO
    )
    private val _uiState: MutableStateFlow<SongsScreenState> = MutableStateFlow(SongsScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        populateSongsDB()
    }

    val songs = localSongRepository.getAllSongs()

    private fun populateSongsDB() {
        viewModelScope.launch {
            localSongRepository.populateLocalSongDatabase()
        }

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun onFavClick(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            localSongRepository.setFavorite(
                songId = id,
                isFavorite = isFavorite
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                SongsScreenViewModel(
                    songDAO = db.songDao()
                )
            }
        }
    }
}