package side_projects.ahmedz.data

import io.reactivex.Single
import side_projects.ahmedz.domain.model.Joke

interface JokesRemoteSource {
	fun searchForJokes(text: String): Single<List<Joke>>

	fun getRandomJoke(): Single<Joke>
}