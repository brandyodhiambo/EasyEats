package com.odhiambodevelopers.easyeats.data.repository

import android.content.Context
import android.provider.Settings.System.getString
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.odhiambodevelopers.easyeats.R
import com.odhiambodevelopers.easyeats.model.User
import com.odhiambodevelopers.easyeats.utils.Constants
import com.odhiambodevelopers.easyeats.utils.Constants.WEB_CLIENT_ID
import com.odhiambodevelopers.easyeats.utils.Resource
import com.odhiambodevelopers.easyeats.utils.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository() {

    private var oneTapClient: SignInClient? = null
    private var signUpRequest: BeginSignInRequest? = null
    private var signInRequest: BeginSignInRequest? = null

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference =  FirebaseDatabase.getInstance().getReference("users")

    //Register
    suspend fun registerUsers(name:String,phone:String,email:String,password:String): Resource<AuthResult>{
        return withContext(Dispatchers.IO){
            safeCall {
                val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
                firebaseAuth.currentUser?.sendEmailVerification()?.await()
                val uid = result.user?.uid!!
                val user = User(name,phone,email,uid)
                databaseReference.child(uid).setValue(user).await()
                Resource.Success(result)
            }
        }
    }
    //Sign in
    suspend fun loginUser(email:String,password:String): Resource<AuthResult>{
        return withContext(Dispatchers.IO){
            safeCall {
                val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
                Resource.Success(result)
            }
        }
    }

    //forgot password
    suspend fun forgotPassword(email:String):Resource<Any>{
        return withContext(Dispatchers.IO){
            firebaseAuth.sendPasswordResetEmail(email).await()
            Resource.Success(Any())
        }
    }

    //google authentication
 /*   suspend fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(null,WEB_CLIENT_ID))
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }*/

}