package side_projects.ahmedz.chucknorris.model.remote

import com.google.gson.annotations.SerializedName

data class JokeResponse(
		@SerializedName("id")
		val id: String,
		@SerializedName("value")
		val value: String,
		@SerializedName("icon_url")
		val iconUrl: String,
		@SerializedName("url")
		val webUrl: String
)

data class SearchResponse(
		@SerializedName("result")
		val jokeList: List<JokeResponse>
)