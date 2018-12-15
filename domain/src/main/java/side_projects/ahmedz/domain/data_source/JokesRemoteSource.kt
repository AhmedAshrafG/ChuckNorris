package side_projects.ahmedz.data

import io.reactivex.Single
import side_projects.ahmedz.domain.entity.JokeEntity

interface JokesRemoteSource {
	fun searchForJokes(text: String): Single<List<JokeEntity>>

	fun getRandomJoke(): Single<JokeEntity>
}