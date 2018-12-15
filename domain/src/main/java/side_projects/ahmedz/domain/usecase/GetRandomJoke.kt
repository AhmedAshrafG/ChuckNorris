package side_projects.ahmedz.usecases

import io.reactivex.Observable
import side_projects.ahmedz.domain.common.ISchedulerProvider
import side_projects.ahmedz.domain.common.UseCase
import side_projects.ahmedz.domain.entity.JokeEntity
import side_projects.ahmedz.domain.repository.IJokesRepository

public class GetRandomJoke(
		private val jokesRepository: IJokesRepository,
		schedulerProvider: ISchedulerProvider
): UseCase<Unit, JokeEntity>(schedulerProvider) {
	override fun buildObservable(input: Unit): Observable<JokeEntity> = jokesRepository.getRandomJoke().toObservable()
}