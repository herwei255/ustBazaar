package com.okit.ustBazaar.room_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

enum class ConditionStatus {
    BRAND_NEW, LIKE_NEW, LIGHTLY_USED, WELL_USED, HEAVILY_USED
}
@Entity
@Serializable
data class Condition(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val name: String,
    val conditionStatus: ConditionStatus = ConditionStatus.LIGHTLY_USED
) {
//    @Ignore
//    val products = mutableListOf<Product>()

}