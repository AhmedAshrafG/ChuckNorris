package side_projects.ahmedz.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import side_projects.ahmedz.chucknorris.model.cache.JokesDAO

@Database(entities = [JokeCacheModel::class], version = 1)
abstract class ChuckNorrisDB: RoomDatabase() {

	abstract fun jokesDao(): JokesDAO

}