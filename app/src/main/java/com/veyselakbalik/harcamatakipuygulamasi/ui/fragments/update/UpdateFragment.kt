package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentUpdateBinding
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar
//import com.veyselakbalik.harcamatakipuygulamasi.databinding.FragmentUpdateBinding
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.HarcamalarViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mHarcamalarViewModel: HarcamalarViewModel
//    private lateinit var binding: FragmentUpdateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_update, container, false)

        mHarcamalarViewModel = ViewModelProvider(this)[HarcamalarViewModel::class.java]
        binding.btnSil.setOnClickListener{
            harcamaSil()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }


        val imageview = when (args.currentHarcama.urunTipi) {
            "Fatura" -> {
                R.drawable.ic_note
            }
            "Kira" -> {
                R.drawable.ic_home
            }
            else -> {R.drawable.ic_shopping_bag}
        }.toInt()

        binding.updateBackButton.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }

        binding.updateHarcamaTuru.setText(args.currentHarcama.urunAdi)
        binding.updateHarcamaMiktari.setText(args.currentHarcama.urunHarcamaMiktari.toString())
        binding.updateHarcamaBirimi.setText(args.currentHarcama.urunParaBirimi)
        binding.updateImageView.setImageResource(imageview)


        return binding.root
    }

    private fun harcamaGuncelle(){
        val imageview = when (args.currentHarcama.urunTipi) {
            "Fatura" -> {
                R.drawable.ic_note
            }
            "Kira" -> {
                R.drawable.ic_home
            }
            else -> {R.drawable.ic_shopping_bag}
        }
        val urunAdi = update_harcamaTuru.text.toString()
        val urunHarcamaMiktari = Integer.parseInt(update_harcamaMiktari.text.toString())
        val urunTipi = update_imageView.setImageResource(imageview).toString()
        val urunParabirimi = update_harcamaBirimi.text.toString()
        val guncellenmisHarcama = Harcamalar(args.currentHarcama.id,urunAdi,urunHarcamaMiktari,urunTipi,urunParabirimi)

        mHarcamalarViewModel.harcamayiGuncelle(guncellenmisHarcama)


    }


    private fun harcamaSil(){

            mHarcamalarViewModel.harcamayiSil(args.currentHarcama)
    }


}