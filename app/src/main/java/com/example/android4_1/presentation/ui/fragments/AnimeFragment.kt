package com.example.android4_1.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4_1.R
import com.example.android4_1.databinding.FragmentAnimeBinding
import com.example.android4_1.presentation.ui.adapter.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : Fragment(R.layout.fragment_anime) {

    private val binding by viewBinding(FragmentAnimeBinding::bind)
    private val viewModel by viewModels<AnimeViewModel>()
    private val animeAdapter = AnimeAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserver()
        handlePagingState()
    }

    private fun initialize() = with(binding) {
        recyclerViewSecond.adapter = animeAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.fetchAnime().collect {
                lifecycleScope.launch {
                    animeAdapter.submitData(it)
                }
            }
        }
    }

    private fun handlePagingState() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                animeAdapter.loadStateFlow.collect {
                    progressBarSecond.isVisible = it.source.refresh is LoadState.Loading
                    appendProgressSecond.isVisible = it.source.append is LoadState.Loading
                    prependProgressSecond.isVisible = it.source.prepend is LoadState.Loading
                }
            }
        }
    }

    private fun onItemClick(id: String) {
        findNavController().navigate(
            ViewPagerFragmentDirections.actionViewPagerFragment2ToDetailedKitsuFragment(
                id
            )
        )
    }
}