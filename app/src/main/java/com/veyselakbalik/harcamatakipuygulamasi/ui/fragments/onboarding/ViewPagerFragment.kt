package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.veyselakbalik.harcamatakipuygulamasi.R

import com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding.onboardingscreens.OnBoardingFirstFragment
import com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding.onboardingscreens.OnBoardingSecondFragment
import com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding.onboardingscreens.OnBoardingThirdFragment
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstFragment(),
            OnBoardingSecondFragment(),
            OnBoardingThirdFragment()

        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        
        view.viewPager.adapter = adapter


        return view
    }

}