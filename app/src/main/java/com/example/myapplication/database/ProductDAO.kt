package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDAO{
    @Query ("SELECT * FROM ProductDatabase")
    fun getAll(): List<ProductDatabase>

    @Insert
    fun InsertAll(vararg products: ProductDatabase)
}