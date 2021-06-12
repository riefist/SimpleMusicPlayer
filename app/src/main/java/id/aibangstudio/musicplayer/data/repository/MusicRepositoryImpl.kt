package id.aibangstudio.musicplayer.data.repository

import id.aibangstudio.musicplayer.data.remote.service.MusicService
import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import id.aibangstudio.musicplayer.domain.repository.MusicRepository
import io.reactivex.Single

class MusicRepositoryImpl(private val musicService: MusicService): MusicRepository {

    override fun searchMusic(query: String): Single<List<MusicEntity>> {
        return musicService.searchMusic(query).map { it.results.map { it.toEntity() } }
    }

}