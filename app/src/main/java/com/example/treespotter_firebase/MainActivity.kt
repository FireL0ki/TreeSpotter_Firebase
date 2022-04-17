package com.example.treespotter_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

private const val TAG = "MAIN_ACTIVITY"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

//        val tree = mapOf("name" to "pine", "dateSpotted" to Date())
//        db.collection("trees").add(tree)
//        val tree2 = mapOf("name" to "oak", "dateSpotted" to Date())
//        db.collection("trees").add(tree2)

//        val tree = Tree("Pine", Date())
//        db.collection("trees").add(tree)

        db.collection("trees")
            .whereEqualTo("name", "Pine")
            .whereEqualTo("favorite", true)
            .orderBy("dateSpotted", Query.Direction.DESCENDING) // sorted with most recent first
            .limit(10)
            .addSnapshotListener { treeDocuments, error ->

            if (error != null) {
                Log.e(TAG, "Error getting all trees", error)
            }

            if (treeDocuments != null) {
                for (treeDoc in treeDocuments) {
                    val treeFromFirebase = treeDoc.toObject(Tree::class.java)
//                    val name = treeDoc["name"]
//                    val dateSpotted = treeDoc["dateSpotted"]
//                    val favorite = treeDoc["favorite"] // added in Firebase example
                    val path = treeDoc.reference.path
                    Log.d(TAG, "$treeFromFirebase, $path")
                }
            }
        }

    }
}