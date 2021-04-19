package com.example.unittestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.unittestapp.R
import com.example.unittestapp.adapter.UserProfileListAdapter
import com.example.unittestapp.databinding.ActivityHomeBinding
import com.example.unittestapp.fragments.HomeFragment
import com.example.unittestapp.fragments.ProfileFragment
import com.example.unittestapp.fragments.SettingsFragment
import com.example.unittestapp.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private val viewModel : MainViewModel by viewModels()

    lateinit var userListAdapter: UserProfileListAdapter

    lateinit var homeFragament : HomeFragment

    lateinit var profileFragment: ProfileFragment

    lateinit var settingsFragment: SettingsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_home)


        replaceFragment(HomeFragment(),"Home")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFrg ->{
                Toast.makeText(this,"home", Toast.LENGTH_SHORT).show()
                replaceFragment(HomeFragment(),"HomeFragment")
            }
            R.id.profile ->{
                Toast.makeText(this,"profile", Toast.LENGTH_SHORT).show()
                replaceFragment(ProfileFragment(),"HomeFragment")

            }
            R.id.settings ->{
                Toast.makeText(this,"settings", Toast.LENGTH_SHORT).show()
                replaceFragment(SettingsFragment(),"HomeFragment")

            }
            else -> true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment, tag).commit()
    }
}