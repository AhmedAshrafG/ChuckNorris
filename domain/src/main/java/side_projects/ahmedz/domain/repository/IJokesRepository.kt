package side_projects.ahmedz.domain.repository

import io.reactivex.*
import side_projects.ahmedz.domain.entity.JokeEntity

interface IJokesRepository {
	fun getFavoriteJokes(): Flowable<List<JokeEntity>>
	fun searchForJokes(text: String): Single<List<JokeEntity>>
	fun getRandomJoke(): Single<JokeEntity>
	fun saveJoke(joke: JokeEntity): Completable
	fun removeJoke(joke: JokeEntity): Completable
}
