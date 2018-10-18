package side_projects.ahmedz.chucknorris.di

import android.arch.persistence.room.Room
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import side_projects.ahmedz.chucknorris.model.cache.ChuckNorrisDB
import side_projects.ahmedz.chucknorris.model.cache.JokesLocalSourceImpl
import side_projects.ahmedz.chucknorris.model.mapper.JokeAPIMapper
import side_projects.ahmedz.chucknorris.model.mapper.JokeEntityMapper
import side_projects.ahmedz.chucknorris.model.remote.JokesAPIService
import side_projects.ahmedz.chucknorris.model.remote.JokesRemoteSourceImpl
import side_projects.ahmedz.data.*
import side_projects.ahmedz.usecases.*

val useCaseModule = module {
	factory { create<AddFavoriteJokeUC>() }
	factory { create<RemoveFavoriteJokeUC>() }
	factory { create<GetFavoriteJokesUC>() }
	factory { create<GetRandomJokeUC>() }
}

val repositoryModule = module {
	single { create<JokesRepository>() }
}

val dataSourceModule = module {
	single<JokesLocalSource> { create<JokesLocalSourceImpl>() }
	single<JokesRemoteSource> { create<JokesRemoteSourceImpl>() }
}

val networkingModule = module {
	single { getOkHttpClient() }
	single { getRetrofit(get(), getProperty("server_url")) }
	single { get<Retrofit>().create(JokesAPIService::class.java) }
}

fun getRetrofit(client: OkHttpClient, serverUrl: String): Retrofit =
		Retrofit.Builder()
			.client(client)
			.addCallAdapterFactory(RxJava2CallAdapterFactory
				.createWithScheduler(Schedulers.io()))
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(serverUrl)
			.build()

fun getOkHttpClient(): OkHttpClient = run {
	val logging = HttpLoggingInterceptor()
	logging.level = HttpLoggingInterceptor.Level.BODY
	OkHttpClient.Builder()
		.addInterceptor(logging)
		.build()
}

val databaseModule = module {
	single {
		Room.databaseBuilder(
				androidApplication(),
				ChuckNorrisDB::class.java,
				"thug_life.db"
		).build()
	}
	single { get<ChuckNorrisDB>().jokesDao() }
}

val mapperModule = module {
	factory { create<JokeEntityMapper>() }
	factory { create<JokeAPIMapper>() }
}

val schedulerModule = module {
	single<SchedulerProvider> { create<SchedulerProviderImp>() }
	single { get<SchedulerProvider>().getUIThread() }
}

