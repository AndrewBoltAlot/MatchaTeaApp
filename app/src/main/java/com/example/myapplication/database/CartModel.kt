package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity
data class CartModel (
    @PrimaryKey(autoGenerate = true) val uid: Int?,

    @ColumnInfo val title: String,

    @ColumnInfo val price: Double,

    @ColumnInfo val quantity: Int
)