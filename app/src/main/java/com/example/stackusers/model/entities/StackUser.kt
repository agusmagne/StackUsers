package com.example.stackusers.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class StackUser(
    val bronzeBadgeCount: String = "",
    val silverBadgeCount: String = "",
    val goldBadgeCount: String = "",
    val topTags: List<String> = listOf(),
    val reputation: String = "",
    val creationDate: Date = Date(),
    val location: String? = "",
    val profileImageUrl: String = "",
    val displayName: String = "",
): Parcelable