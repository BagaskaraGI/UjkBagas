package com.example.ujkbagas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pesanan_table")
data class Pesanan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nomerMeja: String,
    val namaMenu: String,
    val hargaMenu: Int,
    val waktuPesan : String,
    val statusPesan : String
) : Parcelable
