package side_projects.ahmedz.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

abstract class UseCase<in I, O: Any>(
		private val workScheduler: Scheduler,
		private val postExecuteScheduler: Scheduler
) {
	private val disposables by lazy { CompositeDisposable() }

	operator fun invoke(
			input: I,
			onNext: (O) -> Unit = {},
			onError: (Throwable) -> Unit = {},
			onComplete: () -> Unit = {}
	) {
		execute(input)
			.subscribeOn(workScheduler)
			.observeOn(postExecuteScheduler)
			.subscribeBy(onError, onComplete, onNext)
			.addTo(disposables)
	}

	abstract fun execute(input: I): Observable<O>

	fun stop() = disposables.clear()
}
