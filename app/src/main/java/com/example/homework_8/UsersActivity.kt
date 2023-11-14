package com.example.homework_8


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_8.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private var usersList = mutableListOf<UserData>()
    private lateinit var adapter: Adapter

    private var etFirstName = ""
    private var etLastName = ""
    private var etEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //creating the instance of Adapter and passing the usersList in it
        adapter = Adapter(usersList)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter


        //add user button
        binding.addBtn.setOnClickListener {
            addUser()
            val newUserPosition = usersList.size - 1
            adapter.notifyItemInserted(newUserPosition)
        }
    }

    private fun addUser() {
        //getting input from edit text
        etFirstName = binding.etFirstName.text.toString()
        etLastName = binding.etLastName.text.toString()
        etEmail = binding.etEmail.text.toString()

        if (filledInputs(etFirstName, etLastName,etEmail)) {
            val userObject = UserData(etFirstName, etLastName,etEmail)
            usersList.add(userObject)
        }
        //clearing the input fields after completing the adding process
        binding.etFirstName.text?.clear()
        binding.etLastName.text?.clear()
        binding.etEmail.text?.clear()
    }
    //validation to check if all the fields are filled
    private fun filledInputs(etFirstName: String, etLastName: String, etEmail: String): Boolean {
        if (etEmail.isBlank() || etFirstName.isBlank() || etLastName.isBlank()) {
            Toast.makeText(this, "Please Fill all the Input Fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    //constant for the activity
    companion object {
        const val REQUESTCODE = 1
    }
    //callback method invocation for startActivityForResult() result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK) {
            val updatedUserData = data?.getParcelableExtra<UserData>("updatedUserData")
            if (updatedUserData != null) {
                val position = usersList.indexOfFirst { it.email == updatedUserData.email }

                usersList[position] = updatedUserData
                adapter.notifyItemChanged(position)
            }
        }
    }
}
