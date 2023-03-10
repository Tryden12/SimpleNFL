package com.tryden.simplenfl.network.service

import com.tryden.simplenfl.network.response.models.article.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleByIDService {

    @GET("{article-id}")
    suspend fun getArticleById(
        @Path("article-id") articleId: String
    ) : Response<ArticleResponse>
}