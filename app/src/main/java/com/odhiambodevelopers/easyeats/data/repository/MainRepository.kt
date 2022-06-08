package com.odhiambodevelopers.easyeats.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.UploadTask
import com.odhiambodevelopers.easyeats.utils.Resource
import com.odhiambodevelopers.easyeats.utils.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject


class MainRepository (private val context: Context) {
    private val storage = FirebaseStorage.getInstance().getReference("userImages")
    private val databaseReference = FirebaseDatabase.getInstance().getReference("userImages")

    suspend fun uploadImage(fileUri: Uri): Resource<Task<Uri?>> {
        return withContext(Dispatchers.IO){
            safeCall {
                val fileStorageRef = storage.child("${UUID.randomUUID()}${fileUri.lastPathSegment}")

                // Compress the image
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, fileUri)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream)
                val fileInBytes = byteArrayOutputStream.toByteArray()

                val uploadTask = fileStorageRef.putBytes(fileInBytes)

                val result = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    fileStorageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result
                    } else {
                        task.exception?.let {
                            throw it
                        }
                    }
                }
                Resource.Success(result)

            }

        }

    }

}