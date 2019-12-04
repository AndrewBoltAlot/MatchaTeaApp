package com.example.myapplication.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDAO {
    @Query ("SELECT * FROM CartModel")
    fun getAll(): List<CartModel>

    @Insert
    fun InsertAll(vararg item: CartModel)
}