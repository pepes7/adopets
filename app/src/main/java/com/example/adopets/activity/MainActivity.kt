package com.example.adopets.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.adopets.R
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class MainActivity : AppCompatActivity() {

    //variavel para autenticação do firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        auth = FirebaseConfig.getFirebaseAuth()
        auth = FirebaseAuth.getInstance()
        var botaoEntrar = findViewById<Button>(R.id.botaoEntrar)

        botaoEntrar.setOnClickListener {view -> logar(view)}

        supportActionBar?.hide()
    }

    fun logar(view: View) {
        var email = editTextEmail.text.toString()
        var senha = editTextSenha.text.toString()

        var valido = true

        if (email.isEmpty()) {
            editTextEmail.error = "Campo vazio!"
            valido = false
        }

        if (senha.isEmpty()) {
            editTextSenha.error = "Campo vazio!"
            valido = false
        }

        if (valido) {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                    } else {
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(
                                applicationContext,
                                "Credenciais inválidas!", Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                applicationContext,
                                "Erro ao realizar login: " + e.message, Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                })
        }
    }

    fun telaCadastro(view: View) {
        startActivity(Intent(applicationContext, CadastroActivity::class.java))
    }

}
