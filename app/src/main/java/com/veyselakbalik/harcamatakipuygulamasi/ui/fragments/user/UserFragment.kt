package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.user

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentUserBinding
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentUserBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user, container, false)




        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        userViewModel.name.observe(viewLifecycleOwner,{name ->
            binding.userNameEt.setText(name)
        })
        userViewModel.cinsiyet.observe(viewLifecycleOwner,{sex ->
           val secilmis = when(sex) {
                binding.erkekRb.text-> {
                    R.id.erkek_rb
                }
                binding.kadinRb.text-> {
                    R.id.kadin_rb
                }
                else -> {
                    R.id.belirsiz_rb
                }
            }
            user_radioGrup.check(secilmis)

        })



        sharedPreferences = this.requireActivity().getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)


        binding.userNameBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_homeFragment)
        }



      binding.btnUserKaydet.setOnClickListener {

          userViewModel.saveSex(when {
              binding.erkekRb.isChecked -> {
                  binding.erkekRb.text.toString()
              }
              binding.kadinRb.isChecked -> {
                  binding.kadinRb.text.toString()
              }
              else -> {
                  binding.belirsizRb.text.toString()
              }
          }.toString())
          userViewModel.saveName(binding.userNameEt.text.toString())
          saveName()
          findNavController().navigate(R.id.action_userFragment_to_homeFragment)
        }
        return binding.root
    }

    private fun saveName(){
        val username : String = userName_et.text.toString()
        val cinsiyet : String = when {
            binding.erkekRb.isChecked -> {
                binding.erkekRb.text.toString()
            }
            binding.kadinRb.isChecked -> {
                binding.kadinRb.text.toString()
            }
            else -> {
                binding.belirsizRb.text.toString()
            }
        }.toString()


        userName_et.setText(username)
        user_radioGrup.check(when {
            binding.erkekRb.isChecked -> {
                R.id.erkek_rb
            }
            binding.kadinRb.isChecked -> {
                R.id.kadin_rb
            }
            else -> {
                R.id.belirsiz_rb
            }
        })
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("name",username)
        editor.putString("cinsiyet",cinsiyet)
        editor.apply()
    }



}