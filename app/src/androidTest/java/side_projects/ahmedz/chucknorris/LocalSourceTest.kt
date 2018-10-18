package side_projects.ahmedz.chucknorris

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.Module
import org.koin.standalone.inject
import side_projects.ahmedz.chucknorris.di.testDBModule
import side_projects.ahmedz.chucknorris.model.cache.ChuckNorrisDB
import side_projects.ahmedz.data.JokesLocalSource
import side_projects.ahmedz.domain.model.Joke

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LocalSourceTest: ThugKoinTest() {
	private val database: ChuckNorrisDB by inject()
	private val jokesLocalSource: JokesLocalSource by inject()

	override fun getDependentModules(): List<Module> = listOf(testDBModule)

	override fun after() {
		database.close()
		super.after()
	}

	@Test
	fun testInsert() {
		val joke = Joke("1", "JokeContent", "", "", true)
		val expectedJokes = listOf(joke, joke.copy("2"), joke.copy("3"))
		expectedJokes.map { jokesLocalSource.saveJoke(it).blockingAwait() }

		val actualJokes = jokesLocalSource.getFavoriteJokes().blockingFirst()
		Assert.assertEquals(expectedJokes, actualJokes)
	}
}
