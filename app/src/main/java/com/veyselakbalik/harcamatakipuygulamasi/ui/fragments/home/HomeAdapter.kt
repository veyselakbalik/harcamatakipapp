package com.veyselakbalik.harcamatakipuygulamasi.ui.fragments.home

import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.veyselakbalik.harcamatakipuygulamasi.R
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar
import com.veyselakbalik.harcamatakipuygulamasi.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var paralar = 0

    private var harcamaListesi = emptyList<Harcamalar>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate( R.layout.custom_row , parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = harcamaListesi[position]
        val imgRes = when (currentItem.urunTipi) {
            "Fatura" -> {
                R.drawable.ic_note
            }
            "Kira" -> {
                R.drawable.ic_home
            }
            else -> {
                R.drawable.ic_shopping_bag
            }
        }

        holder.itemView.aciklama_rv.text = currentItem.urunAdi
        holder.itemView.harcama_rv.text = currentItem.urunHarcamaMiktari.toString()
        holder.itemView.harcama_birimi_rv.text = currentItem.urunParaBirimi
        holder.itemView.imageView_rv.setImageResource(imgRes)
        paralar += currentItem.urunHarcamaMiktari
        holder.itemView.rowLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("miktar",paralar.toString())

    }


    override fun getItemCount(): Int {
       return harcamaListesi.size
    }

    fun setData(harcamalar: List<Harcamalar>){
        this.harcamaListesi = harcamalar
        notifyDataSetChanged()
    }
}