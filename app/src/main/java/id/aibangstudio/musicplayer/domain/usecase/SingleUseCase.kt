package id.aibangstudio.musicplayer.domain.usecase

import id.aibangstudio.musicplayer.utils.rx.SchedulerProvider
import id.aibangstudio.musicplayer.utils.rx.with
import io.reactivex.Single

abstract class SingleUseCase<T, in Params>(private val mSchedulers: SchedulerProvider) {

    protected abstract fun execute(params: Params? = null): Single<T>

    operator fun invoke(params: Params? = null) = execute(params).with(mSchedulers)
}