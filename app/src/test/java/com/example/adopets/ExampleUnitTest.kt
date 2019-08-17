package com.example.adopets

import com.example.adopets.config.FirebaseConfig
import com.example.adopets.model.Doador
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Test

class ExampleUnitTest {

    private lateinit var database: DatabaseReference

    @Test
    fun insereUsuario() {
        database = FirebaseDatabase.getInstance().reference
        var usuarios : DatabaseReference = database.child("usuarios")

        val doador = Doador()
        doador.nome = "Eddunic"

        usuarios.child("001").setValue(doador)
    }

}
