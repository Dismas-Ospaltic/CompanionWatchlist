package com.d12.companionwatchlist.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d12.companionwatchlist.model.ProgressEntity
import com.d12.companionwatchlist.repository.ProgressRepository
import com.d12.companionwatchlist.repository.WatchListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class  ProgressViewModel (

    private val repository: ProgressRepository
) : ViewModel() {

    private val _progressHistory = MutableStateFlow<List<ProgressEntity>>(emptyList())
    val progressHistory: StateFlow<List<ProgressEntity>> = _progressHistory

    fun loadProgressHistory(watchlistId: String) {
        viewModelScope.launch {
            repository.getProgressHistory(watchlistId).collect { list ->
                _progressHistory.value = list
            }
        }
    }

    fun updateProgress(watchlistId: String, newEpisode: Int, totalEpisodes: Int, note: String? = null) {
        viewModelScope.launch {
            repository.updateProgress(watchlistId, newEpisode, totalEpisodes, note)
        }
    }
}
