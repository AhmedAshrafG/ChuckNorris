package side_projects.ahmedz.domain.common

import io.reactivex.Scheduler

interface ISchedulerProvider {
	fun getWorkerScheduler(): Scheduler

	fun getPostExecuteScheduler(): Scheduler
}