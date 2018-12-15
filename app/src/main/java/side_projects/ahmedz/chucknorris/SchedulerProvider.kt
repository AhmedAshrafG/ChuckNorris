package side_projects.ahmedz.chucknorris

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import side_projects.ahmedz.domain.common.ISchedulerProvider

class SchedulerProvider : ISchedulerProvider {
	override fun getWorkerScheduler(): Scheduler = Schedulers.io()

	override fun getPostExecuteScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
