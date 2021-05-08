package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.HarcamalarViewModel
import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {


    private lateinit var binding: FragmentAddBinding
    private lateinit var mHarcamaViewModel : HarcamalarViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add, container, false)

        mHarcamaViewModel = ViewModelProvider(this)[HarcamalarViewModel::class.java]


        binding.addBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }
        binding.btnDatabseEkle.setOnClickListener {
            insertDataToDatabase()
        }




        return binding.root
    }




    private fun insertDataToDatabase() {

        val urunTipi = when {
            binding.Fatura.isChecked -> {
                binding.Fatura.text.toString()
            }
            binding.Kira.isChecked -> {
                binding.Kira.text.toString()
            }
            else -> {
                binding.Diger.text.toString()
            }
        }.toString()

        val urunParaBirimi = when {
            binding.TL.isChecked -> {
                binding.TL.text.toString()
            }
            binding.Dolar.isChecked -> {
                binding.Dolar.text.toString()
            }
            binding.Euro.isChecked -> {
                binding.Euro.text.toString()
            }
            else -> {
                binding.Sterlin.text.toString()
            }
        }.toString()

        val aciklama = aciklama_et.text.toString()
        val harcama = harcama_et.text



        if (inputCheck(aciklama,harcama)){
            val harcamalar = Harcamalar(0,aciklama,Integer.parseInt(harcama.toString()),urunTipi,urunParaBirimi)
            mHarcamaViewModel.harcamaEkle(harcamalar)
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(),"Tüm Alanları Doldurun",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(aciklama:String,harcama:Editable):Boolean{
        return !(TextUtils.isEmpty(aciklama) || harcama.isEmpty())
    }

}