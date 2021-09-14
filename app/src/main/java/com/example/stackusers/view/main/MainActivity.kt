package com.example.stackusers.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackusers.databinding.ActivityMainBinding
import com.example.stackusers.model.entities.StackUser
import com.example.stackusers.view.BaseActivity
import com.example.stackusers.view.details.UserDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var stackUsersRvAdapter: StackUsersRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stackUsersRvAdapter = StackUsersRvAdapter { startDetailsActivity(it) }
        binding.usersRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = stackUsersRvAdapter
        }

        binding.searchButton.setOnClickListener {
            val filter = binding.filterEditText.editableText.toString().trim()
            viewModel.getUsers(filter)
        }

        viewModel.userListLD.observe(this) {
            stackUsersRvAdapter.updateStackUsers(it)
        }

        if (savedInstanceState == null) {
            viewModel.getUsers()
        }
    }

    private fun startDetailsActivity(stackUser: StackUser) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(UserDetailsActivity.STACK_USER, stackUser)
        startActivity(intent)
    }
}