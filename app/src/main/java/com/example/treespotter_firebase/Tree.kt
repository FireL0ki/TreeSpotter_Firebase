package com.example.treespotter_firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import java.util.*

// data class gives free toString() method
data class Tree(
    val name: String? = null,
    val favorite: Boolean? = null,
    val dateSpotted: Date? = null,
//    val documentReference: DocumentReference? = null  // we don't need these uploaded, it's the ids
    @get:Exclude @set:Exclude var documentReference: DocumentReference? = null
)