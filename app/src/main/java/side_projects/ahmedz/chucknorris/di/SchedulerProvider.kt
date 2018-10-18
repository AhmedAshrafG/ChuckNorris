package side_projects.ahmedz.chucknorris.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImp : SchedulerProvider {
	override fun getWorkerScheduler(): Scheduler = Schedulers.io()

	override fun getUIThread(): Scheduler = AndroidSchedulers.mainThread()
}

interface SchedulerProvider {
	fun getWorkerScheduler(): Scheduler

	fun getUIThread(): Scheduler
}
