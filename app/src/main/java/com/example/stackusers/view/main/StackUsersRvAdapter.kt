package com.example.stackusers.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stackusers.databinding.UsersRvSingleBinding
import com.example.stackusers.model.entities.StackUser

class StackUsersRvAdapter(private val onUserClick: (StackUser) -> Unit) : RecyclerView.Adapter<StackUsersRvAdapter.StackUsersViewHolder>() {

    private var stackUsers: List<StackUser> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackUsersViewHolder {
        val binding =
            UsersRvSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StackUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StackUsersViewHolder, position: Int) {
        holder.bindView(stackUsers[position])
    }

    override fun getItemCount(): Int {
        return stackUsers.size
    }

    fun updateStackUsers(newUsers: List<StackUser>) {
        stackUsers = listOf()
        stackUsers = newUsers
        notifyDataSetChanged()
    }

    inner class StackUsersViewHolder(private val binding: UsersRvSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(user: StackUser) {
            binding.userDisplayName.text = user.displayName
            binding.reputation.text = user.reputation
            binding.root.setOnClickListener { onUserClick(user) }
        }
    }
}