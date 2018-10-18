package side_projects.ahmedz.chucknorris.model.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import side_projects.ahmedz.chucknorris.model.mapper.JokeEntityMapper
import side_projects.ahmedz.data.JokesLocalSource
import side_projects.ahmedz.domain.model.Joke

public class JokesLocalSourceImpl(
		private val jokesDAO: JokesDAO,
		private val mapper: JokeEntityMapper
): JokesLocalSource {
	override fun isJokeFavorite(joke: Joke): Boolean = jokesDAO.getJokeById(joke.jokeId) != null

	override fun getFavoriteJokes(): Flowable<List<Joke>> = jokesDAO.getAll()
		.map { entities -> entities.map(mapper::mapTo) }

	override fun saveJoke(joke: Joke): Completable = Completable.fromCallable { jokesDAO.insert(mapper.revert(joke)) }

	override fun removeJoke(joke: Joke): Completable = Completable.fromCallable { jokesDAO.delete(mapper.revert(joke)) }
}