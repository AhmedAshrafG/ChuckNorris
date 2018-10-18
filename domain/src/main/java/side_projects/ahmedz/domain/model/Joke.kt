package side_projects.ahmedz.domain.model

data class Joke(
		val jokeId: String,
		val content: String,
		val iconUrl: String,
		val webUrl: String,
		val isFavorite: Boolean = false
)