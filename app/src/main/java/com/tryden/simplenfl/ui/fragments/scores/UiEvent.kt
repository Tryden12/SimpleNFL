package com.tryden.simplenfl.ui.fragments.scores

data class UiEvent(
    val id: String,
    val date: String,
    val week: Int,
    val statusParent: UiStatusParent,
    val competitions: List<UiCompetition>
) {

    data class UiStatusParent(
        val status: UiEventStatus

    )

    data class UiEventStatus(
        val completed: Boolean,
        val description: String
    )

    data class UiCompetition(
        val teams: List<UiCompetitor>,
        val broadcast: List<UiBroadcast>,
        val notes: List<UiNote>
    )

    data class UiCompetitor(
        val homeAway: String,
        val winner: Boolean?,
        val score: String,
        val team: UiEventTeam,
    )

    data class UiNote(
        val headline: String
    )

    data class UiEventTeam(
        val id: String,
        val shortDisplayName: String,
        val abbreviation: String,
        val logo: String?
    )

    data class UiBroadcast(
        val shortName: String
    )

    data class UiHeadline(
        val description: String = "",
        val shortLinkText: String = "",
    )
}