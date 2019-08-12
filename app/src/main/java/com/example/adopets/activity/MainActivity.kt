package com.example.adopets.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.adopets.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //variavel para autenticação do firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recupera a instancia
        auth = FirebaseAuth.getInstance()
    }

    fun cadastrar(view: View){

        //recupera as referencias da interface
        var email = edit_email
        var senha = edit_senha


        //cria um usuario com email e senha
        auth.createUserWithEmailAndPassword(email.text.toString(),senha.text.toString()).addOnCompleteListener(this){


        }

    }
}
