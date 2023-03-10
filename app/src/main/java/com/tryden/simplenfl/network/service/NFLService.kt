package com.tryden.simplenfl.network.service

import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse
import com.tryden.simplenfl.network.response.models.team.TeamResponse
import com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NFLService {

    @GET("teams")
    suspend fun getAllTeams(): Response<AllTeamsResponse>

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: String
    ): Response<TeamResponse>

    @GET("teams/{team-id}/roster")
    suspend fun getRosterByTeamId(
        @Path("team-id") teamId: String
    ): Response<RosterResponse>

    //scoreboard?limit=1000&dates=20220908-20230108
    @GET("scoreboard")
    suspend fun getScoresRange(
        @Query("dates") dates: String,
        @Query("limit") limit: String
    ): Response<ScoreboardResponse>

    @GET("scoreboard")
    suspend fun getScoresByWeek(
        @Query("week") week: String
    ): Response<ScoreboardResponse>

    @GET("scoreboard")
    suspend fun getScoresCalendar(
        @Query("limit") limit: String
    ): Response<ScoreboardResponse>

    @GET("news")
    suspend fun getBreakingNews(
        @Query("type") type: String,
        @Query("limit") limit: String
    ) : Response<NewsResponse>

    @GET("news")
    suspend fun getNewsByTeamId(
        @Query("team") teamId: String,
        @Query("limit") limit: String
    ) : Response<NewsResponse>


}