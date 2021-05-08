package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentSplashBinding
import com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.splash.SplashFragmentDirections


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash, container, false)
        Handler().postDelayed({

            if(onBoardingFinished()){
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }else{
                val action = SplashFragmentDirections.actionSplashFragmentToViewPagerFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }


        },4000)


        return binding.root
    }

    private fun onBoardingFinished():Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        return sharedPref.getBoolean("Finished",false)

    }

}