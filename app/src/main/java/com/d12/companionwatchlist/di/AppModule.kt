package com.d12.companionwatchlist.di



import com.d12.companionwatchlist.data.local.AppDatabase
import com.d12.companionwatchlist.repository.ProgressRepository
import com.d12.companionwatchlist.repository.WatchListRepository
import com.d12.companionwatchlist.viewmodel.ProgressViewModel
import com.d12.companionwatchlist.viewmodel.WatchListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {

single { AppDatabase.getDatabase(get()).watchListDao() }
 single { WatchListRepository(get()) }
viewModel { WatchListViewModel(get()) }

 single { AppDatabase.getDatabase(get()).progressDao() }
 single { ProgressRepository(get(),get()) }
 viewModel { ProgressViewModel(get()) }

}