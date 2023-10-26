package com.okit.ustBazaar.room_models

import androidx.room.Embedded
import androidx.room.Relation

data class UserPaymentProviderDetails(
    @Embedded
    val userPaymentProvider: UserPaymentProvider,
    @Relation(
        parentColumn = "providerId",
        entityColumn = "id",
    )
    val paymentProvider: PaymentProvider,
)
