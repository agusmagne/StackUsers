package com.example.stackusers.utils

import com.example.stackusers.model.entities.StackUser
import com.example.stackusers.model.responses.GetUsersResponse
import java.util.*

object EntityMapper {

    fun mapUsersRawToUsersList(usersRaw: List<GetUsersResponse.UserRaw>): List<StackUser> {
        val users = mutableListOf<StackUser>()
        usersRaw.forEach {
            users.add(
                StackUser(
                    bronzeBadgeCount = it.badgeCount.bronze.toString(),
                    silverBadgeCount = it.badgeCount.silver.toString(),
                    goldBadgeCount = it.badgeCount.gold.toString(),
                    topTags = buildTopTags(it.collectives?.get(0)?.collective?.tags),
                    reputation = it.reputation.toString(),
                    creationDate = Date(it.creationDate * 1000),
                    location = it.location,
                    profileImageUrl = it.profileImageUrl,
                    displayName = it.displayName
                )
            )
        }
        return users
    }

    private fun buildTopTags(tags: List<String>?): List<String> {
        if (tags == null) return listOf()
        if (tags.size >= 3) return tags.subList(0, 3)
        return tags.subList(0, tags.size)
    }

}