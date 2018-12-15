package side_projects.ahmedz.chucknorris.model.mapper

import side_projects.ahmedz.chucknorris.model.remote.JokeResponse
import side_projects.ahmedz.domain.entity.JokeEntity

public class JokeAPIMapper: Mapper<JokeResponse, JokeEntity> {
	override fun mapTo(input: JokeResponse) = JokeEntity(
			input.id,
			input.value,
			input.iconUrl,
			input.webUrl
	)

	override fun revert(output: JokeEntity) = throw NotImplementedError()
}