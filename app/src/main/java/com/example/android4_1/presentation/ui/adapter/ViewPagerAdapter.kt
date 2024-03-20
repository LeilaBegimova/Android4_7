package com.example.android4_1.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android4_1.presentation.ui.fragments.AnimeFragment
import com.example.android4_1.presentation.ui.fragments.MangaFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return if (position == 0) AnimeFragment() else MangaFragment()
    }
}