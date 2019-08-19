package com.example.adopets

import com.example.adopets.model.Doador
import com.example.adopets.model.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Test

class ExampleUnitTest {

    private lateinit var referencia: DatabaseReference

    @Test
    fun insereUsuario() {
        referencia = FirebaseDatabase.getInstance().reference
        var usuarios = referencia.child("usuarios")

        val usuario = Usuario()
        usuario.email = "Eddunic"

        val doador = Doador()
        doador.nome = "a"

        usuarios.push().setValue(usuario)
    }

}
