package com.okit.ustBazaar.room_models

import androidx.room.Embedded
import androidx.room.Relation

class LocalCategory (
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        entity = Product::class,
    )
    val products: List<LocalProduct>,
)