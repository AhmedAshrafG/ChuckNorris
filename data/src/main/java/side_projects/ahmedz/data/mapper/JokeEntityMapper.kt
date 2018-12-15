package side_projects.ahmedz.chucknorris.model.mapper

import side_projects.ahmedz.data.cache.JokeCacheModel
import side_projects.ahmedz.domain.entity.JokeEntity

public class JokeEntityMapper: Mapper<JokeCacheModel, JokeEntity> {
	override fun mapTo(input: JokeCacheModel) = JokeEntity(
			input.jokeId,
			input.content,
			input.iconUrl,
			input.webUrl,
			true
	)

	override fun revert(output: JokeEntity) = JokeCacheModel(
			output.jokeId,
			output.content,
			output.iconUrl,
			output.webUrl
	)
}