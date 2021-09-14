package com.example.stackusers.view.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.stackusers.databinding.ActivityUserDetailsBinding
import com.example.stackusers.model.entities.StackUser
import com.example.stackusers.view.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsActivity : BaseActivity() {

    companion object {
        const val STACK_USER = "STACK_USER"
    }

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<StackUser>(STACK_USER)!!

        supportActionBar?.title = user.displayName

        binding.apply {

            Glide.with(this@UserDetailsActivity).load(user.profileImageUrl).into(userImage)

            userDisplayName.text = user.displayName

            val bronzeCountText = "Bronze: " + user.bronzeBadgeCount
            bronzeBadges.text = bronzeCountText

            val silverCountText = "Silver: " + user.silverBadgeCount
            silverBadges.text = silverCountText

            val goldCountText = "Gold: " + user.goldBadgeCount
            goldBadges.text = goldCountText

            val reputationText = "Reputation: " + user.reputation
            userReputation.text = reputationText

            View.GONE.let {
                when (user.topTags.size) {
                    0 -> {
                        topTagsHeader.visibility = it
                        tag1.visibility = it
                        tag2.visibility = it
                        tag3.visibility = it
                    }
                    1 -> {
                        tag1.visibility = it
                        tag2.visibility = it

                        tag3.text = user.topTags[0]
                    }
                    2 -> {
                        tag1.visibility = it

                        tag2.text = user.topTags[0]
                        tag3.text = user.topTags[1]
                    }
                    3 -> {
                        tag1.text = user.topTags[0]
                        tag2.text = user.topTags[1]
                        tag3.text = user.topTags[2]

                    }

                }
            }

            val userLocationText = "Location: " + (user.location ?: "unkown")
            userLocation.text = userLocationText

            val df = SimpleDateFormat("dd-MM-yyyy, hh:ss", Locale.US)
            val creationDateText = "Creation date: " + df.format(user.creationDate)
            creationDate.text = creationDateText

        }


    }
}