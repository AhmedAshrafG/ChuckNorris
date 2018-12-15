package side_projects.ahmedz.chucknorris.model.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import side_projects.ahmedz.chucknorris.model.mapper.JokeEntityMapper
import side_projects.ahmedz.data.JokesCache
import side_projects.ahmedz.domain.entity.JokeEntity

public class RoomJokesCache(
		private val jokesDAO: JokesDAO,
		private val mapper: JokeEntityMapper
): JokesCache {
	override fun isJokeFavorite(joke: JokeEntity): Boolean = jokesDAO.getJokeById(joke.jokeId) != null

	override fun getFavoriteJokes(): Flowable<List<JokeEntity>> = jokesDAO.getAll()
		.map { entities -> entities.map(mapper::mapTo) }

	override fun saveJoke(joke: JokeEntity): Completable = Completable.fromCallable { jokesDAO.insert(mapper.revert(joke)) }

	override fun removeJoke(joke: JokeEntity): Completable = Completable.fromCallable { jokesDAO.delete(mapper.revert(joke)) }
}