package com.d12.companionwatchlist.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d12.companionwatchlist.model.WatchListEntity
import com.d12.companionwatchlist.repository.WatchListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WatchListViewModel(private val watchlistRepository: WatchListRepository) : ViewModel() {

    // Holds the list of wishlist
    private val _watchlist = MutableStateFlow<List<WatchListEntity>>(emptyList())
    val watchlist: StateFlow<List<WatchListEntity>> = _watchlist


    fun insertWatchlist(watchlist: WatchListEntity) {
        viewModelScope.launch {
            watchlistRepository.insertWatchlist(watchlist)
        }
    }

    fun updateWatchlist(watchlist: WatchListEntity) {
        viewModelScope.launch {
            watchlistRepository.updateWatchlist(watchlist)
        }
    }



    init {
        getAllWatchList()
    }

    /**
     * Fetch all watchlist
     */
    private fun getAllWatchList() {
        viewModelScope.launch {
            watchlistRepository.getAllWatchList().collectLatest { watchList ->
                _watchlist.value = watchList
            }
        }
    }


    private val _watchlistState = MutableStateFlow<WatchListEntity?>(null)
    val watchlistState: StateFlow<WatchListEntity?> = _watchlistState



    fun getWatchListById(itemId: String) {
        viewModelScope.launch {
            watchlistRepository.getWatchListById(itemId).collect { watchlist ->
                _watchlistState.value = watchlist
            }
        }
    }


    fun deleteWatchListById(itemId: String) {
        viewModelScope.launch {
            watchlistRepository.deleteWatchListById(itemId)
        }
    }


    fun updateWatchlistStatusById(itemId: String, newStatus: String) {
        viewModelScope.launch {
            val success = watchlistRepository.updateWatchlistStatusById(itemId, newStatus)
            if (success) {
                // Refresh debt list if update is successful
                _watchlist.value = _watchlist.value.map { watchlist ->
                    if (watchlist.watchlistId == itemId) watchlist.copy(watchlistStatus = newStatus) else watchlist
                }
            }

            }
    }


    fun updateStatusById(itemId: String, newStatus: String) {
        viewModelScope.launch {
            val success = watchlistRepository.updateStatusById(itemId, newStatus)

            if (success) {

                _watchlist.value = _watchlist.value.map { watchlist ->
                    if (watchlist.watchlistId == itemId) watchlist.copy(status = newStatus) else watchlist
                }
            }
            }
    }


    fun updateSeenPageEpisodeById(itemId: String, newSeenPageEpisode: Int) {
        viewModelScope.launch {
            val success = watchlistRepository.updateSeenPageEpisodeById(itemId, newSeenPageEpisode)

            if (success) {
                _watchlist.value = _watchlist.value.map { watchlist ->
                    if (watchlist.watchlistId == itemId) watchlist.copy(seenPageEpisode = newSeenPageEpisode) else watchlist
                }

            }

        }
    }

    fun updateWatchlistById(itemId: String, watchListTile: String,expectedCompleteDate : String, link: String?, type: String, notes: String?, category: String, noEpisodesPage: Int) {
        viewModelScope.launch {
            val success = watchlistRepository.updateWatchlistById(itemId, watchListTile,expectedCompleteDate , link, type, notes, category, noEpisodesPage)
            if (success) {
                _watchlist.value = _watchlist.value.map { watchlist ->
                    if (watchlist.watchlistId == itemId) watchlist.copy(watchListTitle = watchListTile) else watchlist
                }
            }

            }
    }







}