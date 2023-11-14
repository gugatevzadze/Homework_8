package com.example.homework_8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_8.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userData: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = intent.getParcelableExtra("userData")!!

        binding.etFirstNameEdit.setText(userData.firstName)
        binding.etLastNameEdit.setText(userData.lastName)
        binding.etEmailEdit.setText(userData.email)

        //save button click to update the item in the UsersActivity
        binding.saveBtn.setOnClickListener {
            //new UserData object with the edited values
            val updatedUserData = UserData(
                binding.etFirstNameEdit.text.toString(),
                binding.etLastNameEdit.text.toString(),
                binding.etEmailEdit.text.toString()
            )

            //intent to send back the updated data
            val resultIntent = Intent()
            resultIntent.putExtra("updatedUserData", updatedUserData)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}







