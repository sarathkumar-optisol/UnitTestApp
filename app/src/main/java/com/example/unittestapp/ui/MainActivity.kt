package com.example.unittestapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.unittestapp.R
import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.databinding.ActivityMainBinding
import com.example.unittestapp.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var users = arrayListOf<UserProfile>(
        UserProfile("sarath", "sarath1@gmail.com", 23, "sarath123"),
        UserProfile("kumar", "abc54@gmail.com", 24, "sarath35"),
        UserProfile("Oliver", "Olive1r@gmail.com", 28, "Oliver"),
        UserProfile("Lucas", "Lucas@gmail.com", 23, "Lucas"),
        UserProfile("William", "William@gmail.com", 30, "William"),
        UserProfile("Ethan", "Ethan@gmail.com", 29, "Ethan1231"),
        UserProfile("sarath", "sarath@gmail.com", 23, "sarath"),
        UserProfile("kumar", "abc@gmail.com", 24, "sarath"),
        UserProfile("Oliver", "Oliver21@gmail.com", 28, "Oliver"),
        UserProfile("kumar1", "ab1c@gmail.com", 24, "sarath56"),
        UserProfile("Oliver", "Oliver@gmail.com", 28, "Oliver"),
        UserProfile("Lucas", "Lucas@gmail.com", 23, "Lucas"),
        UserProfile("William", "William@gmail.com", 30, "William"),
        UserProfile("Ethan", "Ethan@gmail.com", 29, "Ethan"),
        UserProfile("sarath", "sarath@gmail.com", 23, "sarath"),
        UserProfile("kumar", "abc@gmail.com", 24, "sarath"),
        UserProfile("Oliver", "Oliver@gmail.com", 28, "Oliver")
    )

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        for (i in users){
            viewModel.insertUserProfile(i)
        }

        /**
         * navigate to Registration Screen
         */
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        /**
         * hide keyboard on click outside views
         */
        binding.layoutMainActivity.setOnClickListener {
            hideKeyboard(binding.layoutMainActivity)
        }
        /**
         * check user data and Login
         */
        binding.btnSignIn.setOnClickListener {
            val userName : String = binding.etUserName.text.toString()
            if (userName.isEmpty()){
                Toast.makeText(this,"Enter Valid Credentials",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.verifyUserLogin(userName)
            }

        }


        lifecycleScope.launchWhenStarted {
            viewModel.loginData.collect{ event->
                when(event){
                    is MainViewModel.LoginEvent.Success ->{
                        binding.progressBarMainActivity.isVisible = false
                        val password = event.result
                        if (password == binding.etPassword.text.toString()){
                            val intent = Intent(this@MainActivity,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@MainActivity,R.string.invalid_credential,Toast.LENGTH_LONG).show()
                        }
                    }
                    is MainViewModel.LoginEvent.Failure ->{
                        Toast.makeText(this@MainActivity,R.string.invalid_credential,Toast.LENGTH_LONG).show()
                        /**
                         * code for unsuccessful login
                         */
                    }
                    is MainViewModel.LoginEvent.Loading -> {
                        binding.progressBarMainActivity.isVisible = true
                    }
                    else -> Unit
                }

            }
        }
    }
    /**
     * hide keyboard on user touches outside
     */
    private fun hideKeyboard(view : View){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}