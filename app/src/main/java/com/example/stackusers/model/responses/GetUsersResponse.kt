package com.example.stackusers.model.responses

import com.google.gson.annotations.SerializedName

class GetUsersResponse(
    @SerializedName("items") val usersRaw: List<UserRaw> = listOf()
) {

    inner class UserRaw(
        @SerializedName("badge_counts") val badgeCount: BadgeGroup = BadgeGroup(),
        val collectives: List<CollectiveWrapper>? = listOf(),
        val reputation: Int = 0,
        @SerializedName("creation_date") val creationDate: Long = 0,
        val location: String = "",
        @SerializedName("profile_image") val profileImageUrl: String = "",
        @SerializedName("display_name") val displayName: String = ""
    )

    inner class BadgeGroup(
        val bronze: Int = 0,
        val silver: Int = 0,
        val gold: Int = 0
    )
    inner class CollectiveWrapper(
        val collective: Collective = Collective()
    )
    inner class Collective(
        val tags: List<String> = listOf()
    )
}

