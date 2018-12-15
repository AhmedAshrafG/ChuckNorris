package side_projects.ahmedz.data.cache

import android.arch.persistence.room.*

@Entity(tableName = "Jokes")
data class JokeCacheModel(
		@PrimaryKey @ColumnInfo(name = "jokeId") var jokeId: String,
		@ColumnInfo(name = "content") var content: String,
		@ColumnInfo(name = "iconUrl") var iconUrl: String,
		@ColumnInfo(name = "webUrl") var webUrl: String
)