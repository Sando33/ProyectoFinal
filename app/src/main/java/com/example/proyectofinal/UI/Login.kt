package com.example.proyectofinal.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    //Nuevo
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()


        firebaseAuth = Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    authFirebaseWithGoogle(account.idToken)
                }catch (e: Exception){

                }
            }
        }
        sharedPreferences = this.getSharedPreferences(SESSION_PREFERENCES_KEY,Context.MODE_PRIVATE)
        val email: String =  sharedPreferences.getString(EMAIL_DATA,"")?:""
        if(email.isNotEmpty()){
            goToMain()
        }
    }
    private fun authFirebaseWithGoogle(idToken: String?) { // login con google
        val authCredential = GoogleAuthProvider.getCredential(idToken!!, null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    with(sharedPreferences.edit()){
                        putString(EMAIL_DATA,user?. email)//para allmacenar el correo
                        commit()
                    }
                    goToMain()
                }else{
                    Toast.makeText ( this,  "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun setViews(){
        binding.tilEmail.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(text.toString(), binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(binding.tilEmail.editText?.text.toString(), text.toString())
        }

        binding.btnLogin.setOnClickListener{
            val email = binding.tilEmail.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            signInWithFirebase(email, password)
        }
        binding.btnGoogle.setOnClickListener {
            signinWithGoogle()

        }
        binding.btnRegisteruser.setOnClickListener {
            val intent = Intent(this, CreateUser::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signInWithFirebase(email: String, password: String) {
        if (validateEmailPassword(email, password)){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        with(sharedPreferences.edit()){
                            putString(EMAIL_DATA,user?. email)
                            commit() //para allmacenar el correo
                        }
                        goToMain()
                    } else {
                        Toast.makeText(this,"Email y password no Incorrectos :(",Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText( this,"Email y password no validos :(",Toast.LENGTH_SHORT).show()
        }
    }



    // Resive datos de firebase
    private fun signinWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this,googleSignInOptions)
        val intent = googleClient.signInIntent
        googleLauncher.launch(intent)
    }


    private fun validateEmailPassword(email:String, password:String): Boolean {
        val validateEmail = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val validatePassword = password.length >= 6

        return validateEmail && validatePassword
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val SESSION_PREFERENCES_KEY = "SESSION_PRERENCES_KEY"
        const val EMAIL_DATA = "EMAIL_DATA "
    }
}