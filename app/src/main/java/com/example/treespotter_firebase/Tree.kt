package com.example.treespotter_firebase

import java.util.*

// data class gives free toString() method
data class Tree(
    val name: String? = null,
    val dateSpotted: Date? = null,
    val favorite: Boolean? = null) {
}