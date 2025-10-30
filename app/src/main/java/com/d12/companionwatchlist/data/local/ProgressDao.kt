package com.d12.companionwatchlist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d12.companionwatchlist.model.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity)

    @Query("SELECT * FROM progress WHERE watchlistId = :watchlistId ORDER BY timestamp DESC")
    fun getProgressHistory(watchlistId: String): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE watchlistId = :watchlistId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastProgress(watchlistId: String): ProgressEntity?
}
