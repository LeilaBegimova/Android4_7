package com.example.android4_1.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4_1.R
import com.example.android4_1.databinding.FragmentMangaBinding
import com.example.android4_1.presentation.ui.adapter.MangaAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MangaFragment : Fragment(R.layout.fragment_manga) {

    private val binding by viewBinding(FragmentMangaBinding::bind)
    private val viewModel by viewModels<MangaViewModel>()
    private val mangaAdapter = MangaAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserver()
        handlePagingState()
    }

    private fun initialize() = with(binding) {
        recyclerView.adapter = mangaAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.fetchManga().collect {
                lifecycleScope.launch {
                    mangaAdapter.submitData(it)
                }
            }
        }
    }

    private fun handlePagingState() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                mangaAdapter.loadStateFlow.collect {
                    progressBar.isVisible = it.source.refresh is LoadState.Loading
                    appendProgress.isVisible = it.source.append is LoadState.Loading
                    prependProgress.isVisible = it.source.prepend is LoadState.Loading
                }
            }
        }
    }

    private fun onItemClick(id:String){
        findNavController().navigate(ViewPagerFragmentDirections.actionViewPagerFragment2ToDetailedKitsuFragment(id))
    }
}

