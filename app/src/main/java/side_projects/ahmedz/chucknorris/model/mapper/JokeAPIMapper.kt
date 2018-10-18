package side_projects.ahmedz.chucknorris.model.mapper

import side_projects.ahmedz.chucknorris.model.remote.JokeResponse
import side_projects.ahmedz.domain.model.Joke

public class JokeAPIMapper: Mapper<JokeResponse, Joke> {
	override fun mapTo(input: JokeResponse) = Joke(
			input.id,
			input.value,
			input.iconUrl,
			input.webUrl
	)

	override fun revert(output: Joke) = throw NotImplementedError()
}