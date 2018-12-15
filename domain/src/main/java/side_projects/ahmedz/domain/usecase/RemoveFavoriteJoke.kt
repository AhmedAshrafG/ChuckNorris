package side_projects.ahmedz.usecases

import io.reactivex.Observable
import side_projects.ahmedz.domain.common.ISchedulerProvider
import side_projects.ahmedz.domain.common.UseCase
import side_projects.ahmedz.domain.entity.JokeEntity
import side_projects.ahmedz.domain.repository.IJokesRepository

public class RemoveFavoriteJoke(
		private val jokesRepository: IJokesRepository,
		schedulerProvider: ISchedulerProvider
)
	: UseCase<JokeEntity, Unit>(schedulerProvider) {
	override fun buildObservable(input: JokeEntity): Observable<Unit> = jokesRepository.removeJoke(input).toObservable()
}