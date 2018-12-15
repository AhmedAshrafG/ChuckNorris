package side_projects.ahmedz.usecases

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import side_projects.ahmedz.domain.common.ISchedulerProvider
import side_projects.ahmedz.domain.common.UseCase
import side_projects.ahmedz.domain.entity.JokeEntity
import side_projects.ahmedz.domain.repository.IJokesRepository
import java.util.concurrent.TimeUnit

public class SearchForJokes(
		private val jokesRepository: IJokesRepository,
		schedulerProvider: ISchedulerProvider
): UseCase<String, List<JokeEntity>>(schedulerProvider) {
	private val textChannel by lazy { PublishSubject.create<String>() }

	override fun buildObservable(input: String): Observable<List<JokeEntity>> = run {
		if (input.isNotBlank())
			textChannel.onNext(input)

		textChannel
			.map { it.toLowerCase().trim() }
			.distinctUntilChanged()
			.filter { it.isNotBlank() }
			.filter { it.length >= 3 }
			.debounce(500, TimeUnit.MILLISECONDS)
			.doOnNext { println("Searching for $it..") }
			.flatMapSingle { jokesRepository.searchForJokes(input) }
	}

	fun search(text: String) = textChannel.onNext(text)
}