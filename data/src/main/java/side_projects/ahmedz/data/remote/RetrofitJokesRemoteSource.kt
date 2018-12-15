package side_projects.ahmedz.chucknorris.model.remote

import io.reactivex.Single
import side_projects.ahmedz.chucknorris.model.mapper.JokeAPIMapper
import side_projects.ahmedz.data.JokesRemoteSource
import side_projects.ahmedz.domain.entity.JokeEntity

class RetrofitJokesRemoteSource(
		private val jokesAPIService: JokesAPIService,
		private val jokeEntityMapper: JokeAPIMapper
): JokesRemoteSource {
	override fun searchForJokes(text: String): Single<List<JokeEntity>> = jokesAPIService.search(text)
		.map { it.jokeList }
		.map { jokeList -> jokeList.map(jokeEntityMapper::mapTo) }

	override fun getRandomJoke(): Single<JokeEntity> = jokesAPIService.getRandomJoke()
		.map(jokeEntityMapper::mapTo)
}