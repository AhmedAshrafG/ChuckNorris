package side_projects.ahmedz.domain.entity

data class JokeEntity(
		val jokeId: String,
		val content: String,
		val iconUrl: String,
		val webUrl: String,
		val isFavorite: Boolean = false
)