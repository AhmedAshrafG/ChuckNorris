package side_projects.ahmedz.data

import io.reactivex.Completable
import io.reactivex.Flowable
import side_projects.ahmedz.domain.model.Joke

interface JokesLocalSource {
	fun getFavoriteJokes(): Flowable<List<Joke>>

	fun saveJoke(joke: Joke): Completable

	fun removeJoke(joke: Joke): Completable

	fun isJokeFavorite(joke: Joke): Boolean
}