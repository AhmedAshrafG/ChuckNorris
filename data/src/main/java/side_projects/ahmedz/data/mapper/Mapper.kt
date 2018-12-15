package side_projects.ahmedz.chucknorris.model.mapper

interface Mapper <I, O> {
	fun mapTo(input: I): O

	fun revert(output: O): I
}