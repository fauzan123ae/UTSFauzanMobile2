package com.example.apploginzn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.apploginzn.viewmodel.UserViewModel

class HomeActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", -1)

        val name = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)
        val phone = findViewById<TextView>(R.id.phone)
        val address = findViewById<TextView>(R.id.address)
        val updateBtn = findViewById<Button>(R.id.btnUpdate)
        val backBtn = findViewById<Button>(R.id.btnBack)

        viewModel.getUserById(userId) { user ->
            user?.let {
                name.text = it.name
                email.text = it.email
                phone.text = it.phone
                address.text = it.address
            }
        }

        updateBtn.setOnClickListener {
            startActivity(Intent(this, UpdateProfileActivity::class.java))
        }

        backBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
