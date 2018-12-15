package side_projects.ahmedz.domain.common

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

abstract class UseCase<in I, O: Any>(private val schedulerProvider: ISchedulerProvider) {

	private var disposable: Disposable? = null

	protected operator fun invoke(
			input: I,
			onSuccess: (O) -> Unit = {},
			onError: (Throwable) -> Unit = {}
	) {
		disposable?.dispose()
		buildObservable(input)
			.subscribeOn(schedulerProvider.getWorkerScheduler())
			.observeOn(schedulerProvider.getPostExecuteScheduler())
			.subscribeBy(
					onError = {
						it.printStackTrace()
						onError(it)
					},
					onNext = onSuccess
			)
			.also { disposable = it }
	}

	abstract fun buildObservable(input: I): Observable<O>

	fun stop() = disposable?.dispose()
}
