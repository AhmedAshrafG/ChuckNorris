package side_projects.ahmedz.data

import io.reactivex.*
import side_projects.ahmedz.domain.model.Joke

public class JokesRepository(
		private val localSource: JokesLocalSource,
		private val remoteSource: JokesRemoteSource
) {
	fun getFavoriteJokes(): Flowable<List<Joke>> = localSource.getFavoriteJokes()

	fun searchForJokes(text: String): Single<List<Joke>> = remoteSource.searchForJokes(text)

	fun getRandomJoke(): Single<Joke> = remoteSource.getRandomJoke()
		.map { it.copy(isFavorite = localSource.isJokeFavorite(it)) }

	fun saveJoke(joke: Joke): Completable = localSource.saveJoke(joke)

	fun removeJoke(joke: Joke): Completable = localSource.removeJoke(joke)
}