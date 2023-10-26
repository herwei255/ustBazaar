package com.okit.ustBazaar.sealed

sealed class AdvertisementType{
    object Store: AdvertisementType()
    object Product: AdvertisementType()
}