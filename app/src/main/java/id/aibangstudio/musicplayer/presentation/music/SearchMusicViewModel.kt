package id.aibangstudio.musicplayer.presentation.music

import androidx.lifecycle.MutableLiveData
import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import id.aibangstudio.musicplayer.domain.usecase.music.SearchMusicUseCase
import id.aibangstudio.musicplayer.presentation.base.BaseViewModel
import id.aibangstudio.musicplayer.utils.UiState

class SearchMusicViewModel(
    val mSearchMusicUseCase: SearchMusicUseCase,
): BaseViewModel() {

    val musicResult = MutableLiveData<UiState<List<MusicEntity>>>()

    fun searchMusic(artistName: String){
        musicResult.value = UiState.Loading()
        val params = SearchMusicUseCase.Params(artistName)
        compositeDisposable.add(mSearchMusicUseCase(params)
            .subscribe({
                musicResult.value = UiState.Success(it)
            },{
                musicResult.value = UiState.Error(it)
            }))
    }

}