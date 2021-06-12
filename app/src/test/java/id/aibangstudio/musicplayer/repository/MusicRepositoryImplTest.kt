package id.aibangstudio.musicplayer.repository

import com.google.gson.Gson
import id.aibangstudio.musicplayer.utils.Utils.getResponseString
import id.aibangstudio.musicplayer.data.remote.response.SearchMusicResultResponse
import id.aibangstudio.musicplayer.data.remote.service.MusicService
import id.aibangstudio.musicplayer.data.repository.MusicRepositoryImpl
import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import id.aibangstudio.musicplayer.domain.repository.MusicRepository
import id.aibangstudio.musicplayer.utils.ext.createService
import id.aibangstudio.musicplayer.utils.ext.enqueueResponse
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object MusicRepositoryImplTest: Spek({

    lateinit var mMusicRepository: MusicRepository
    lateinit var server: MockWebServer
    lateinit var recordedRequest: RecordedRequest

    beforeGroup {
        server = MockWebServer()
    }

    Feature("search music"){
        Scenario("api success"){
            val query = "Bruno"
            var testObserver = TestObserver<List<MusicEntity>>()
            val filename = "SearchResultMusic.json"

            Given("return data from api"){
                val service = server.createService(MusicService::class.java)
                mMusicRepository = MusicRepositoryImpl(service)
                server.enqueueResponse(filename)
            }

            When("call search music"){
                testObserver = mMusicRepository.searchMusic(query).test()
            }

            Then("data from api"){
                val response = Gson().fromJson(getResponseString(filename), SearchMusicResultResponse::class.java)
                val entity = response.results.map { it.toEntity() }

                testObserver.assertValue(entity)
            }

            Then("verify request url path"){
                recordedRequest = server.takeRequest()
                assertThat(recordedRequest.path, CoreMatchers.`is`("/search?term=$query&entity=song"))
                assertThat(recordedRequest.method, CoreMatchers.`is`("GET"))
            }
        }
    }
})