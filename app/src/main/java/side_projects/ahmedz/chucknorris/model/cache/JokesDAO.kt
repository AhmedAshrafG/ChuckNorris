package side_projects.ahmedz.chucknorris.model.cache

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface JokesDAO {
	@Query("SELECT * from Jokes")
	fun getAll(): Flowable<List<JokeEntity>>

	@Query("SELECT * from Jokes WHERE jokeId = :jokeId LIMIT 1")
	fun getJokeById(jokeId: String): JokeEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(jokeEntity: JokeEntity)

	@Delete
	fun delete(jokeEntity: JokeEntity)
}