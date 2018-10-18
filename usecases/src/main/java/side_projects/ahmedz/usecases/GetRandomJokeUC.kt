package side_projects.ahmedz.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import side_projects.ahmedz.data.JokesRepository
import side_projects.ahmedz.domain.model.Joke

public class GetRandomJokeUC(
		private val jokesRepository: JokesRepository,
		workScheduler: Scheduler,
		postExecuteScheduler: Scheduler
): UseCase<Unit, Joke>(workScheduler, postExecuteScheduler) {
	override fun execute(input: Unit): Observable<Joke> = jokesRepository.getRandomJoke().toObservable()
}