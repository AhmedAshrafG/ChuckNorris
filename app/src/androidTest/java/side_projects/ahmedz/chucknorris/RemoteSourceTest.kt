package side_projects.ahmedz.chucknorris

import org.junit.Assert
import org.junit.Test
import org.koin.dsl.module.Module
import org.koin.standalone.inject
import side_projects.ahmedz.data.JokesRemoteSource

class RemoteSourceTest: ThugKoinTest() {
	override fun getDependentModules(): List<Module> = listOf()

	private val jokesRemoteSource: JokesRemoteSource by inject()

	@Test
	fun testRemoteSource() {
		val joke = jokesRemoteSource.getRandomJoke().blockingGet()
		Assert.assertTrue(joke?.content != null)

		val jokes = jokesRemoteSource.searchForJokes("lol").blockingGet()
		Assert.assertTrue(jokes?.isNotEmpty() != null)
	}
}