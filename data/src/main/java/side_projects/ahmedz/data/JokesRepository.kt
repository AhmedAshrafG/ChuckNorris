package side_projects.ahmedz.data

import io.reactivex.*
import side_projects.ahmedz.domain.entity.JokeEntity
import side_projects.ahmedz.domain.repository.IJokesRepository

public class JokesRepository(
		private val localSource: JokesCache,
		private val remoteSource: JokesRemoteSource
): IJokesRepository {
	override fun getFavoriteJokes(): Flowable<List<JokeEntity>> = localSource.getFavoriteJokes()

	override fun searchForJokes(text: String): Single<List<JokeEntity>> = remoteSource.searchForJokes(text)

	override fun getRandomJoke(): Single<JokeEntity> = remoteSource.getRandomJoke()
		.map { it.copy(isFavorite = localSource.isJokeFavorite(it)) }

	override fun saveJoke(joke: JokeEntity): Completable = localSource.saveJoke(joke)

	override fun removeJoke(joke: JokeEntity): Completable = localSource.removeJoke(joke)
}