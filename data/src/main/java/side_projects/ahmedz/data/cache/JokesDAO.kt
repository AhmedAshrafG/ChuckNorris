package side_projects.ahmedz.chucknorris.model.cache

import android.arch.persistence.room.*
import io.reactivex.Flowable
import side_projects.ahmedz.data.cache.JokeCacheModel

@Dao
interface JokesDAO {
	@Query("SELECT * from Jokes")
	fun getAll(): Flowable<List<JokeCacheModel>>

	@Query("SELECT * from Jokes WHERE jokeId = :jokeId LIMIT 1")
	fun getJokeById(jokeId: String): JokeCacheModel?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(jokeEntity: JokeCacheModel)

	@Delete
	fun delete(jokeEntity: JokeCacheModel)
}