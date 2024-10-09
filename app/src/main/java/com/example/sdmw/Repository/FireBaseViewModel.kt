package com.example.sdmw.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class FirebaseViewModel : ViewModel() {
    private val _userData = MutableStateFlow<ExpenseCardData?>(null)
    val userData: StateFlow<ExpenseCardData?> = _userData

    fun readUserData(userId: String) {
        val database = Firebase.database
        val myRef = database.getReference("expenses").child(userId)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(ExpenseCardData::class.java)
                viewModelScope.launch {
                    _userData.emit(user)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read user data: ${error.message}")
            }
        })
    }
}
