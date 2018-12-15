package side_projects.ahmedz.chucknorris.model.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesAPIService {
	@GET("random")
	fun getRandomJoke(): Single<JokeResponse>

	@GET("search")
	fun search(@Query("query") query: String): Single<SearchResponse>
}