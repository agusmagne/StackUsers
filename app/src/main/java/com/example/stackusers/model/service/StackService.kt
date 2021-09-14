package com.example.stackusers.model.service

import com.example.stackusers.model.entities.StackUser
import com.example.stackusers.network.StackApi
import com.example.stackusers.utils.EntityMapper

class StackService(private val stackApi: StackApi) {

    suspend fun getUsers(filter: String): List<StackUser> {
        val res = stackApi.getUsers(filter)
        if (res.isSuccessful) {
            val body = res.body()
            val users = EntityMapper.mapUsersRawToUsersList(body!!.usersRaw)
            users.sortedBy { it.displayName }
            return users
        }
        throw Throwable()
    }
}