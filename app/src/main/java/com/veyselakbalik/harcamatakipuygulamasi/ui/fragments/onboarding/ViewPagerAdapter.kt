package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    list:ArrayList<Fragment>,
    fm:FragmentManager,
    lifecyle: Lifecycle
) : FragmentStateAdapter(fm,lifecyle) {

    private val fragmentList : ArrayList<Fragment> = list

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}