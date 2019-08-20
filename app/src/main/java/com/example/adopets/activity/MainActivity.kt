package com.example.adopets.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.adopets.R
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    //variavel para autenticação do firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient : GoogleSignInClient
    private lateinit var botaoGoogle: SignInButton
    private lateinit var botaoEntrar : Button
    private val RC_CODE_GOOGLE : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configuração para o login com o Google
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        botaoEntrar = botao_entrar
        botaoGoogle = sign_in_button

        botaoEntrar.setOnClickListener {logar()}
        botaoGoogle.setOnClickListener{telaGoogle()}

        supportActionBar?.hide()
    }

    fun logar() {
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

    fun telaCadastro() {
        startActivity(Intent(applicationContext, CadastroActivity::class.java))
    }

    //abre a mini tela do google
    fun telaGoogle(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_CODE_GOOGLE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_CODE_GOOGLE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //O login do Google foi bem-sucedido, autenticado com o Firebase
                val account = task.getResult(ApiException::class.java)
                //método para logar com o Google
                logarGoogle(account!!)
            } catch (e: ApiException) {
                // erro no login do Google
                Log.i("erro", "Código falho = ${e.getStatusCode()}")
            }
        }
    }

    fun logarGoogle(acct: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            //se o login concluido com sucesso
                if (task.isSuccessful) {
                    //val user = auth.currentUser
                    Toast.makeText(applicationContext, "Sucesso  ao realizar login: ",Toast.LENGTH_SHORT).show()
                   // updateUI(user)
                } else {
                    Toast.makeText(applicationContext, "Erro  ao realizar login: ",Toast.LENGTH_SHORT).show()
                }
            }

    }

}
