package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.onboarding.onboardingscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.veyselakbalik.harcamatakipuygulamasi.R
import kotlinx.android.synthetic.main.fragment_on_boarding_second.view.*


class OnBoardingSecondFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding_second, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.next2.setOnClickListener {
            viewPager?.currentItem = 2
        }


        return view
    }


}