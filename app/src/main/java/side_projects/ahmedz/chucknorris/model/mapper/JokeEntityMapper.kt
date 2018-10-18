package side_projects.ahmedz.chucknorris.model.mapper

import side_projects.ahmedz.chucknorris.model.cache.JokeEntity
import side_projects.ahmedz.domain.model.Joke

public class JokeEntityMapper: Mapper<JokeEntity, Joke> {
	override fun mapTo(input: JokeEntity) = Joke(
			input.jokeId,
			input.content,
			input.iconUrl,
			input.webUrl,
			true
	)

	override fun revert(output: Joke) = JokeEntity(
			output.jokeId,
			output.content,
			output.iconUrl,
			output.webUrl
	)
}