package com.tryden.simplenfl.epoxy.models.scores

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresScheduledItemBinding

data class ScoresUpcomingEpoxyModel(
    val logoAway: String,
    val logoHome: String,
    val teamNameAway: String,
    val teamNameHome: String,
    val recordAway: String,
    val recordHome: String,
    val dateScheduled: String,
    val broadcast: String,
    val headline: String,
): ViewBindingKotlinModel<ModelScoresScheduledItemBinding>
    (R.layout.model_scores_scheduled_item) {

    override fun ModelScoresScheduledItemBinding.bind() {

        // Logos
        if (logoAway.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(awayLogoImageView)
        } else {
            Picasso.get().load(logoAway).into(awayLogoImageView)
        }

        if (logoHome.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(homeLogoImageView)
        } else {
            Picasso.get().load(logoHome).into(homeLogoImageView)
        }

        // Team names
        when (teamNameAway.isEmpty()) {
            true -> teamNameAwayTextview.text = "TBD"
            false -> teamNameAwayTextview.text = teamNameAway
        }

        when (teamNameHome.isEmpty()) {
            true -> teamNameHomeTextview.text = "TBD"
            false -> teamNameHomeTextview.text = teamNameHome
        }


        // Game headline (bottom)
        if (headline.isEmpty()) {
            descriptionGameBottomItemTextview.visibility = View.GONE
        } else {
            descriptionGameBottomItemTextview.visibility = View.VISIBLE
            descriptionGameBottomItemTextview.text = headline
        }


        // Parse ISO format to "4:30 PM"
//        val actual = OffsetDateTime
//            .parse(dateScheduled, DateTimeFormatter.ISO_DATE_TIME)
//            .atZoneSameInstant(
//                ZoneId.of(SimpleNFLApplication.zoneId))
//        val formatter2 = DateTimeFormatter.ofPattern("h:mm a")
//        val gameTime = actual.format(formatter2) // todo put in mapper?

        // Right side of item
        recordAwayItemTextview.text = recordAway
        recordHomeItemTextview.text = recordHome
        datePreGameItemTextview.text = dateScheduled
        timePreGameItemTextview.text = "GT" // todo fix
        broadcastPreGameItemTextview.text = broadcast

    }
}