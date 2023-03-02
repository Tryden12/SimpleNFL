package com.tryden.simplenfl.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteTeamDao {

    @Query("SELECT * FROM favorite_team_entity")
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTeamEntity: FavoriteTeamEntity)

    @Delete
    suspend fun delete(favoriteTeamEntity: FavoriteTeamEntity)

    @Update
    suspend fun update(favoriteTeamEntity: FavoriteTeamEntity)

}