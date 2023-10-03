package com.example.ujkbagas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu_table")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val namaMenu: String,
    val hargaMenu: Int,
    val gambarMenu: String,
    val deskripsiMenu: String,
    val kategoriMenu : String
) : Parcelable
