package side_projects.ahmedz.chucknorris.di

import android.arch.persistence.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import side_projects.ahmedz.chucknorris.model.cache.ChuckNorrisDB

val testDBModule = module(override = true) {
	single {
		Room.inMemoryDatabaseBuilder(
				androidApplication(),
				ChuckNorrisDB::class.java
		).build()
	}
	single { get<ChuckNorrisDB>().jokesDao() }
}