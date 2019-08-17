package com.example.adopets.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.adopets.R
import com.example.adopets.model.Usuario
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.title = "Nova conta"

        auth = FirebaseAuth.getInstance()
    }

    fun cadastrar(view: View) {
        var email = editTextEmail.text.toString()
        var senha = editTextSenha.text.toString()

        var valido = true

        if (email.isEmpty()) {
            editTextEmail.error = "Campo obrigatório!"
            valido = false
        }

        if (senha.isEmpty()) {
            editTextSenha.error = "Campo obrigatório!"
            valido = false
        }

        if (valido) {
            var usuario = Usuario()
            usuario.email = email
            usuario.senha = senha
            //cria um usuario com email e senha
            auth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(this@CadastroActivity, "Senha fraca!", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this@CadastroActivity, "Email inválido!", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseAuthUserCollisionException) {
                            Toast.makeText(this@CadastroActivity, "Usuário já cadastrado!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@CadastroActivity, "" + e.message, Toast.LENGTH_SHORT).show()
                            // Toast.makeText(CadastroActivity.this, "Não foi possível cadastrar o usuário!", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
        }

    }

}
