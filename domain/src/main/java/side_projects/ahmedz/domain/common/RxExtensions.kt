package side_projects.ahmedz.domain.common

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

private fun <T : Any> ((T) -> Unit).asConsumer(): Consumer<T> {
	return if (this === onNextStub) Functions.emptyConsumer() else Consumer(this)
}

private fun ((Throwable) -> Unit).asOnErrorConsumer(): Consumer<Throwable> {
	return if (this === onErrorStub) Functions.ON_ERROR_MISSING else Consumer(this)
}

private fun (() -> Unit).asOnCompleteAction(): Action {
	return if (this === onCompleteStub) Functions.EMPTY_ACTION else Action(this)
}

fun <T : Any> Observable<T>.subscribeBy(
		onError: (Throwable) -> Unit = onErrorStub,
		onComplete: () -> Unit = onCompleteStub,
		onNext: (T) -> Unit = onNextStub
): Disposable = subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())


operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
	add(disposable)
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable
		= apply { compositeDisposable.add(this) }