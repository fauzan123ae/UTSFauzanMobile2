package com.example.apploginzn

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.apploginzn.viewmodel.UserViewModel

class UpdateProfileActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        val sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", -1)

        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val address = findViewById<EditText>(R.id.address)
        val updateBtn = findViewById<Button>(R.id.btnUpdate)

        viewModel.getUserById(userId) { user ->
            user?.let { currentUser ->
                name.setText(currentUser.name)
                email.setText(currentUser.email)
                phone.setText(currentUser.phone)
                address.setText(currentUser.address)

                updateBtn.setOnClickListener {
                    val updatedUser = currentUser.copy(
                        name = name.text.toString(),
                        email = email.text.toString(),
                        phone = phone.text.toString(),
                        address = address.text.toString()
                    )

                    viewModel.updateUser(updatedUser) {
                        Toast.makeText(this, "Profil diperbarui", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}
