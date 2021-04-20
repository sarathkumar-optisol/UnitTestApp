package com.example.unittestapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unittestapp.R
import com.example.unittestapp.adapter.UserProfileListAdapter
import com.example.unittestapp.databinding.FragmentHomeBinding
import com.example.unittestapp.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 * Created by SARATH on 10-04-2021
 */
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var viewModel: MainViewModel

    lateinit var userProfileListAdapter: UserProfileListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewModel.getUserProfileList()

        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.userProfileList.collect { event->
                when(event){
                    is MainViewModel.ProfileListEvent.Success ->{
                        //binding.progressBarMainActivity.isVisible = false
                        val data = event.result as MutableList<*>

                        userProfileListAdapter.userListAdapter = event.result.toMutableList()
                        for (i in data) Log.d("DATA","$i")

                    }
                    is MainViewModel.ProfileListEvent.Failure ->{
                        Toast.makeText(activity,R.string.invalid_credential, Toast.LENGTH_LONG).show()
                        /**
                         * code for failed to retrieve data
                         */
                    }
                    is MainViewModel.ProfileListEvent.Loading -> {
                        // binding.progressBarMainActivity.isVisible = true
                    }
                    else -> Unit
                }

            }
        }
    }


    private fun setupRecyclerView() = binding.rvUserList.apply{

        userProfileListAdapter = UserProfileListAdapter(context)
        adapter = userProfileListAdapter
        userProfileListAdapter.notifyDataSetChanged()
        layoutManager = LinearLayoutManager(context)

    }
}