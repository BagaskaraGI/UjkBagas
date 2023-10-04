package com.example.ujkbagas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ujkbagas.databinding.ItemListPesananBinding
import com.example.ujkbagas.model.Pesanan

class ListPesananAdapter(private val onItemClickCallback: IOnItemClickCallback) : RecyclerView.Adapter<ListPesananAdapter.ListViewHolder>() {
    var listPesanan = ArrayList<Pesanan>()

    fun delete(index: Int) {
        this.listPesanan.removeAt(index)
    }

    class ListViewHolder(val binding: ItemListPesananBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListPesananBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pesanan = listPesanan[position]
        holder.binding.idtvNama.text = pesanan.namaMenu
        holder.binding.idtvHarga.text = "Rp. ${pesanan.hargaMenu}"

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(pesanan, position)
        }

    }

    override fun getItemCount(): Int {
        return listPesanan.size
    }

    interface IOnItemClickCallback {
        fun onItemClicked(data: Pesanan, position: Int)
    }
}