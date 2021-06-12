package id.aibangstudio.musicplayer.domain.usecase.music

import id.aibangstudio.musicplayer.domain.repository.MusicRepository
import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import id.aibangstudio.musicplayer.domain.usecase.SingleUseCase
import id.aibangstudio.musicplayer.utils.rx.SchedulerProvider
import io.reactivex.Single

class SearchMusicUseCase(
    private val mMusicRepository: MusicRepository,
    mSchedulerProvider: SchedulerProvider
): SingleUseCase<List<MusicEntity>, SearchMusicUseCase.Params>(mSchedulerProvider) {

    override fun execute(params: Params?): Single<List<MusicEntity>> {
        requireNotNull(params, { "params can't be null"})
        return mMusicRepository.searchMusic(params.artist)
    }

    data class Params(
        val artist: String
    )


}