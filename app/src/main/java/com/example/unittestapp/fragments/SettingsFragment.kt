package com.example.unittestapp.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.unittestapp.R
import com.example.unittestapp.databinding.FragmentSettingsBinding
import com.example.unittestapp.ui.MainActivity

/**
 * Created by SARATH on 10-04-2021
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {


    private lateinit var binding : FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)


        binding.btnLogOut.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }


    }



}