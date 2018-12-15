package side_projects.ahmedz.data

import io.reactivex.Completable
import io.reactivex.Flowable
import side_projects.ahmedz.domain.entity.JokeEntity

interface JokesCache {
	fun getFavoriteJokes(): Flowable<List<JokeEntity>>

	fun saveJoke(joke: JokeEntity): Completable

	fun removeJoke(joke: JokeEntity): Completable

	fun isJokeFavorite(joke: JokeEntity): Boolean
}