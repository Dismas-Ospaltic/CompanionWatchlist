package com.d12.companionwatchlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d12.companionwatchlist.utils.formatDate

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val watchlistId: String,              // Foreign key reference to WatchListEntity
    val previousEpisodeReached: Int,      // previous episode before update
    val newEpisodeReached: Int,           // episode after update
    val totalEpisodes: Int,               // total episodes for the show
    val progressPercent: Double,          // (newEpisodeReached / totalEpisodes * 100)
    val direction: String,                // "forward" or "backward"
    val note: String? = null,             // optional note like “rewatching”
    val dateUpdated: String = formatDate(System.currentTimeMillis()),
    val timestamp: Long = System.currentTimeMillis()
)
