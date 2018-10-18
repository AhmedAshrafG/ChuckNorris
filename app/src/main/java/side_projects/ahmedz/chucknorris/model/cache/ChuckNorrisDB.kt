package side_projects.ahmedz.chucknorris.model.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [JokeEntity::class], version = 1)
abstract class ChuckNorrisDB: RoomDatabase() {

	abstract fun jokesDao(): JokesDAO

}