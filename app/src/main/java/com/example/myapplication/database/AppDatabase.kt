package com.example.myapplication.database


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductDatabase::class, CartModel::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun productDao(): ProductDAO
    abstract fun cartDao(): CartDAO
}