package com.example.android4_1.presentation.ui.fragments

import androidx.lifecycle.ViewModel
import com.example.android4_1.data.repositories.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MangaViewModel
@Inject constructor(
    private val repositories: KitsuRepository,
) : ViewModel() {

    fun fetchManga() = repositories.fetchManga()
}