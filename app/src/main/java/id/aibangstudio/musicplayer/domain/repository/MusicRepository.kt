package id.aibangstudio.musicplayer.domain.repository

import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import io.reactivex.Single

interface MusicRepository {
    fun searchMusic(query: String): Single<List<MusicEntity>>
}