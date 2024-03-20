package com.example.android4_1.presentation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4_1.data.remote.models.Data
import com.example.android4_1.data.repositories.KitsuRepository
import com.example.android4_1.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailedKitsuApi: KitsuRepository,
) : ViewModel() {

    private val _detailState = MutableLiveData<UiState<Data>>(UiState.Loading)
    val detailState: LiveData<UiState<Data>> = _detailState
    private val id = savedStateHandle.get<String>(ID_KEY)

    fun setId(id: String) {
        savedStateHandle[ID_KEY] = id
    }

    init {
        getMangaById()
        getAnimeById()
    }

    private fun getAnimeById() {
        viewModelScope.launch {
            id?.let {
                detailedKitsuApi.getAnimeById(id.toInt()).catch {
                    _detailState.value = UiState.Error(it, it.message ?: "Unknown error")
                }
                    .collect {
                        _detailState.value = UiState.Success(it)
                    }
            }
        }
    }

    private fun getMangaById() {
        viewModelScope.launch {
            id?.let {
                detailedKitsuApi.getMangaById(id.toInt())
                    .catch {
                        _detailState.value = UiState.Error(it, it.message ?: "Unknown error")
                    }
                    .collect {
                        _detailState.value = UiState.Success(it)
                    }
            }
        }
    }

    companion object {
        private const val ID_KEY = "id"
    }
}