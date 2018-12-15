package side_projects.ahmedz.chucknorris.di

import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import side_projects.ahmedz.chucknorris.SchedulerProvider
import side_projects.ahmedz.chucknorris.model.cache.RoomJokesCache
import side_projects.ahmedz.chucknorris.model.remote.RetrofitJokesRemoteSource
import side_projects.ahmedz.data.*
import side_projects.ahmedz.domain.common.ISchedulerProvider
import side_projects.ahmedz.usecases.*

val useCaseModule = module {
	factory { create<AddFavoriteJoke>() }
	factory { create<RemoveFavoriteJoke>() }
	factory { create<GetFavoriteJokes>() }
	factory { create<GetRandomJoke>() }
	factory { create<SearchForJokes>() }
}

val repositoryModule = module {
	single { create<JokesRepository>() }
}

val dataSourceModule = module {
	single<JokesCache> { create<RoomJokesCache>() }
	single<JokesRemoteSource> { create<RetrofitJokesRemoteSource>() }
}

val schedulerModule = module {
	single<ISchedulerProvider> { create<SchedulerProvider>() }
	single { get<ISchedulerProvider>().getPostExecuteScheduler() }
}

