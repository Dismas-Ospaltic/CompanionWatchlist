package com.d12.companionwatchlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d12.companionwatchlist.utils.formatDate

@Entity(tableName = "watchlist")
data class WatchListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val watchListTitle: String,
    val expectedCompleteDate: String = formatDate(System.currentTimeMillis()), //DD-MM-YYYY
    val link: String? = null,
    val type: String,
    val notes: String? = null,
    val category: String,
    val noEpisodesPage: Int = 0,
    val status: String = "incomplete",
    val seenPageEpisode: Int = 0,
    val watchlistStatus: String = "active",
    val watchlistId: String,
    val timestamp: Long = System.currentTimeMillis()
)
