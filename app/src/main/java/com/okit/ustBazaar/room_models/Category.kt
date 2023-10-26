package com.okit.ustBazaar.room_models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @DrawableRes val icon: Int,
) {
    @Ignore
    val products = mutableListOf<Product>()
}
