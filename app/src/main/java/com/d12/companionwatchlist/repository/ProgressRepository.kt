package com.d12.companionwatchlist.repository


import com.d12.companionwatchlist.data.local.ProgressDao
import com.d12.companionwatchlist.data.local.WatchListDao
import com.d12.companionwatchlist.model.ProgressEntity
import kotlinx.coroutines.flow.Flow


    class ProgressRepository(
    private val progressDao: ProgressDao,
    private val watchListDao: WatchListDao
) {

    fun getProgressHistory(watchlistId: String): Flow<List<ProgressEntity>> =
        progressDao.getProgressHistory(watchlistId)

    suspend fun updateProgress(
        watchlistId: String,
        newEpisode: Int,
        totalEpisodes: Int,
        note: String? = null
    ) {
        val lastProgress = progressDao.getLastProgress(watchlistId)
        val previousEpisode = lastProgress?.newEpisodeReached ?: 0

        val direction = when {
            newEpisode > previousEpisode -> "forward"
            newEpisode < previousEpisode -> "backward"
            else -> "same"
        }

        val percent = if (totalEpisodes > 0)
            (newEpisode.toDouble() / totalEpisodes.toDouble()) * 100.0
        else 0.0

        val progress = ProgressEntity(
            watchlistId = watchlistId,
            previousEpisodeReached = previousEpisode,
            newEpisodeReached = newEpisode,
            totalEpisodes = totalEpisodes,
            progressPercent = percent,
            direction = direction,
            note = note
        )

        progressDao.insertProgress(progress)

        // Always reflect the latest state in WatchListEntity
        watchListDao.updateSeenEpisode(watchlistId, newEpisode)
    }
}
