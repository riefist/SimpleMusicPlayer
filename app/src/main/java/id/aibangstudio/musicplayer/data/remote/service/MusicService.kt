package id.aibangstudio.musicplayer.data.remote.service

import id.aibangstudio.musicplayer.data.remote.response.SearchMusicResultResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("search")
    fun searchMusic(
        @Query("term") term: String,
        @Query("entity") entity: String = "song"
    ) : Single<SearchMusicResultResponse>
}