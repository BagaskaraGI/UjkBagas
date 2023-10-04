package com.example.ujkbagas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ujkbagas.databinding.ItemListDapurBinding
import com.example.ujkbagas.model.Pesanan

class ListDapurAdapter(val onItemClickCallback: IOnItemClickCallback) :
    RecyclerView.Adapter<ListDapurAdapter.DapurViewHolder>() {
    var listDapur = ArrayList<Pesanan>()

    fun delete(index: Int) {
        this.listDapur.removeAt(index)
    }

    class DapurViewHolder(val binding: ItemListDapurBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DapurViewHolder {
        return DapurViewHolder(
            ItemListDapurBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DapurViewHolder, position: Int) {
        val pesanan = listDapur[position]
        holder.binding.idtvNama.text = pesanan.namaMenu
        holder.binding.idtvNomerMeja.text = "No Meja\n${pesanan.nomerMeja}"
        holder.binding.idtvHarga.text = "Rp. ${pesanan.hargaMenu}"
        holder.binding.idtvWaktu.text = pesanan.waktuPesan

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(pesanan, position)
        }
    }

    override fun getItemCount(): Int {
        return listDapur.size
    }

    interface IOnItemClickCallback {
        fun onItemClicked(data: Pesanan, position: Int)
    }
}
