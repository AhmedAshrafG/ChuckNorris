package side_projects.ahmedz.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import side_projects.ahmedz.data.JokesRepository
import side_projects.ahmedz.domain.model.Joke

public class AddFavoriteJokeUC(
		private val jokesRepository: JokesRepository,
		workScheduler: Scheduler,
		postExecuteScheduler: Scheduler
)
	: UseCase<Joke, Unit>(workScheduler, postExecuteScheduler) {
	override fun execute(input: Joke): Observable<Unit> = jokesRepository.saveJoke(input).toObservable()
}