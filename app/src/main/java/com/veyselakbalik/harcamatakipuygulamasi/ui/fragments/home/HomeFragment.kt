package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.HarcamalarViewModel
import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentHomeBinding
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_user.*


class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userViewModel: UserViewModel
    private lateinit var mHarcamalarViewModel: HarcamalarViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        //RecyclerView
        val recyclerview = binding.recyclerView
        val adapter = HomeAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        mHarcamalarViewModel = ViewModelProvider(this)[HarcamalarViewModel::class.java]
        mHarcamalarViewModel.readAllData.observe(viewLifecycleOwner, Observer { harcamalar ->
            adapter.setData(harcamalar)
        })


        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        userViewModel.name.observe(viewLifecycleOwner,{name ->
            binding.kullaniciAdiTv.setText(name)
        })
        userViewModel.cinsiyet.observe((viewLifecycleOwner),{cinsiyet ->
            val secilmis = when(cinsiyet) {
                "Erkek"-> {
                    "Bey"
                }
                "Kadın"-> {
                    "Hanım"
                }
                else -> {
                    ""
                }
            }
            binding.cinsiyetTv.setText(secilmis)
        })
        userViewModel.para.observe(viewLifecycleOwner, { para ->
            binding.toggleGroup.check(when(para) {
                binding.btnTL.text -> {
                    R.id.btnTL
                }
                binding.btnEuro.text -> {
                    R.id.btnEuro
                }
                binding.btnSterlin.text -> {
                    R.id.btnSterlin
                }
                else -> {
                    R.id.btnDollar
                }
            })

        })




        // SharedPreferences
        sharedPreferences = this.requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("name","İsminiz")
        val cinsiyet = sharedPreferences.getString("cinsiyet", "Bey")
        val birimi = sharedPreferences.getString("mone","Euro")
        userViewModel.saveMoney(birimi.toString())
        userViewModel.saveSex(cinsiyet.toString())
        userViewModel.saveName(name.toString())



        //Listeners
        binding.kullaniciAdiTv.setOnClickListener{
            val action = R.id.action_homeFragment_to_userFragment
            findNavController().navigate(action)
        }

        binding.btnEkle.setOnClickListener {
            val action = R.id.action_homeFragment_to_addFragment
            findNavController().navigate(action)
        }
        binding.toggleGroup.setOnCheckedChangeListener { group, checkedId ->
            birimiKaydet()
            when(checkedId){
                R.id.btnTL -> {
                    binding.birimTv.text = btnTL.text
                }
                R.id.btnDollar -> {
                    binding.birimTv.text = btnDollar.text
                }
                R.id.btnEuro -> {
                    binding.birimTv.text = btnEuro.text
                }
                else -> {
                    binding.birimTv.text = btnSterlin.text
                }
            }

        }


        return binding.root
    }
    private fun birimiKaydet(){
        val moneyBirim : String =  if(binding.toggleGroup.checkedRadioButtonId == R.id.btnTL){
            btnTL.text.toString()
        }else if(binding.toggleGroup.checkedRadioButtonId == R.id.btnEuro){
            btnEuro.text.toString()
        }else if(binding.toggleGroup.checkedRadioButtonId == R.id.btnDollar){
            btnDollar.text.toString()
        }
        else{
            btnSterlin.text.toString()
        }
        binding.toggleGroup.check(when {
            binding.btnTL.isChecked -> {
                R.id.btnTL
            }
            binding.btnEuro.isChecked -> {
                R.id.btnEuro
            }
            binding.btnSterlin.isChecked -> {
                R.id.btnSterlin
            }
            else -> {
                R.id.btnDollar
            }
        })
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("mone",moneyBirim)
        editor.apply()
    }
}