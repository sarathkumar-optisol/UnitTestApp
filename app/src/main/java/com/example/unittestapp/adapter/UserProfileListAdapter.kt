package com.example.unittestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.databinding.UserListBinding

/**
 * Created by SARATH on 16-04-2021
 */
class UserProfileListAdapter(private val context: Context) : RecyclerView.Adapter<UserProfileListAdapter.UserListViewHolder>() {

    inner class UserListViewHolder(val binding : UserListBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<UserProfile>(){
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this,diffCallback)
    var userListAdapter : MutableList<UserProfile>

        get()=differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileListAdapter.UserListViewHolder {
        return UserListViewHolder(
            UserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: UserProfileListAdapter.UserListViewHolder, position: Int) {
        val userData  = userListAdapter[position]

        holder.binding.apply {
            tvUserName.text = userData.name
            tvUsermail.text = userData.email
            tvUserAge.text = userData.age.toString()
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}