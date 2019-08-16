package com.example.adopets.config

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Classe de configuração do Firebase para o App.
 */
class FirebaseConfig {

    private lateinit var database: DatabaseReference

    fun getFirebaseDatabase(): DatabaseReference {
        if (database == null) {
            database = FirebaseDatabase.getInstance().reference
        }

        return database
    }

}