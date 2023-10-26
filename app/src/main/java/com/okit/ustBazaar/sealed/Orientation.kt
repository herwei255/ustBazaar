package com.okit.ustBazaar.sealed

sealed class Orientation {
    object Vertical : Orientation()
    object Horizontal : Orientation()
}