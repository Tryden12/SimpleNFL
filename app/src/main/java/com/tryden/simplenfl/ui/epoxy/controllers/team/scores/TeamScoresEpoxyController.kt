package com.tryden.simplenfl.ui.epoxy.controllers.team.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEpoxyItem.*
import com.tryden.simplenfl.ui.epoxy.models.SectionFooterEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderCenteredEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.ScoresCompletedEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.ScoresUpcomingEpoxyModel

class TeamScoresEpoxyController: TypedEpoxyController<List<EventEpoxyItem>>() {

    override fun buildModels(items: List<EventEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        var bottomId = 0
        items.forEach { item ->
            when (item) {
                is HeaderItem -> {
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = item.header
                    ).id("gameday-${item.header}").addTo(this)
                }
                is EventItem -> {
                    when (item.event) {
                        is EventEntity.Completed -> {
                            ScoresCompletedEpoxyModel(item.event)
                                .id("event-${item.event.id}").addTo(this)
                        }
                        is EventEntity.Upcoming -> {
                            ScoresUpcomingEpoxyModel(item.event)
                                .id("event-${item.event.id}").addTo(this)
                        }
                    }
                }
                is FooterItem -> {
                    SectionFooterEpoxyModel(
                        useSection = true
                    ).id("bottom-${bottomId++}").addTo(this)
                }
            }
        }
    }
}