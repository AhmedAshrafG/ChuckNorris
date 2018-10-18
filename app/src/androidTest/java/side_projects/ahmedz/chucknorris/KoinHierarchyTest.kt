package side_projects.ahmedz.chucknorris

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.checkModules
import side_projects.ahmedz.chucknorris.di.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class KoinHierarchyTest: KoinTest {

	@Test
	fun testHierarchy() {
		checkModules(listOf(
				databaseModule,
				dataSourceModule,
				repositoryModule,
				useCaseModule,
				mapperModule
		))
	}
}
