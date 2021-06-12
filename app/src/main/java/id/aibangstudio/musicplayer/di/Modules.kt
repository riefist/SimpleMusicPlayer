package id.aibangstudio.musicplayer.di

import id.aibangstudio.musicplayer.data.remote.createWebService
import id.aibangstudio.musicplayer.data.remote.provideOkHttpClient
import id.aibangstudio.musicplayer.data.remote.service.MusicService
import id.aibangstudio.musicplayer.data.repository.MusicRepositoryImpl
import id.aibangstudio.musicplayer.domain.repository.MusicRepository
import id.aibangstudio.musicplayer.domain.usecase.music.SearchMusicUseCase
import id.aibangstudio.musicplayer.presentation.music.SearchMusicViewModel
import id.aibangstudio.musicplayer.utils.rx.AppSchedulerProvider
import id.aibangstudio.musicplayer.utils.rx.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideOkHttpClient() }
    single { createWebService<MusicService>(get()) }

    single<SchedulerProvider> { AppSchedulerProvider() }

}

val dataModule = module {
    single<MusicRepository> { MusicRepositoryImpl(get()) }
}

val domainModule = module {
    single { SearchMusicUseCase(get(), get()) }
}

val viewModelModule = module {
    viewModel { SearchMusicViewModel(get()) }
}

val myAppModule = listOf(appModule, dataModule, domainModule, viewModelModule)