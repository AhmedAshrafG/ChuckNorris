package side_projects.ahmedz.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

abstract class UseCase<in I, O: Any>(
		private val workScheduler: Scheduler,
		private val postExecuteScheduler: Scheduler
) {
	private var disposable: Disposable? = null

	operator fun invoke(
			input: I,
			onNext: (O) -> Unit = {},
			onError: (Throwable) -> Unit = {},
			onComplete: () -> Unit = {}
	) {
		disposable?.dispose()
		execute(input)
			.subscribeOn(workScheduler)
			.observeOn(postExecuteScheduler)
			.subscribeBy(
					{
						it.printStackTrace()
						onError(it)
					},
					onComplete,
					onNext
			)
			.also { disposable = it }
	}

	abstract fun execute(input: I): Observable<O>

	fun stop() = disposable?.dispose()
}
