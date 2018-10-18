package side_projects.ahmedz.chucknorris

import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.dsl.module.Module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
abstract class ThugKoinTest: KoinTest {

	@Before
	open fun before() {
		loadKoinModules(getDependentModules())
	}

	abstract fun getDependentModules(): List<Module>

	@After
	open fun after() {
		stopKoin()
	}
}