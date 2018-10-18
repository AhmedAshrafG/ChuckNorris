package side_projects.ahmedz.chucknorris

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import side_projects.ahmedz.chucknorris.di.*

class ThugApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin(
				this,
				listOf(
						useCaseModule,
						repositoryModule,
						dataSourceModule,
						databaseModule,
						networkingModule,
						mapperModule,
						schedulerModule
				),
				extraProperties = mapOf("server_url" to "https://api.chucknorris.io/jokes/"),
				logger = AndroidLogger()
		)
	}
}