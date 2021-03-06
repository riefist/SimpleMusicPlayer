package id.aibangstudio.musicplayer.domain

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import id.aibangstudio.musicplayer.utils.TestSchedulerProvider
import id.aibangstudio.musicplayer.domain.entity.MusicEntity
import id.aibangstudio.musicplayer.domain.repository.MusicRepository
import id.aibangstudio.musicplayer.domain.usecase.music.SearchMusicUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object SearchMusicUseCaseTest: Spek({
    lateinit var mSearchMusicUseCase : SearchMusicUseCase
    lateinit var mMusicRepository: MusicRepository

    val query = "Bruno Mars"
    val entity = MusicEntity(
        "url",
        "Uptown Funk",
        "Bruno Mars",
        "album",
        "songUrl"
    )
    val result = listOf(entity)
    val params = SearchMusicUseCase.Params(query)

    beforeGroup {
        mMusicRepository = mock()
        mSearchMusicUseCase = SearchMusicUseCase(mMusicRepository, TestSchedulerProvider())
    }

    Feature("search music"){
        Scenario("success"){
            lateinit var testObserver: TestObserver<List<MusicEntity>>
            Given("setup return data from repository"){
                given { mMusicRepository.searchMusic(query) }.willReturn(Single.fromCallable { result })
            }
            When("execute"){
                testObserver = mSearchMusicUseCase(params).test()
            }
            Then("result"){
                testObserver.assertComplete()
                testObserver.assertResult(result)

            }
        }
    }
})