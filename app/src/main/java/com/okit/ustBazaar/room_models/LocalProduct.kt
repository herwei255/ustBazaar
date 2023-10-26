package com.okit.ustBazaar.room_models

import androidx.room.Embedded
import androidx.room.Relation

class LocalProduct(
    @Embedded
    val product: Product,
    @Relation(parentColumn = "categoryId", entityColumn = "id")
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val copies: List<ProductColor>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val sizes: List<ProductSize>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val reviews: List<Review>?,

    )
