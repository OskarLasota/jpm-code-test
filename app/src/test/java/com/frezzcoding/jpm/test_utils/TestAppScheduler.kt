package com.frezzcoding.jpm.test_utils

import com.frezzcoding.jpm.common.scheduler.AppScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


class TestAppScheduler: AppScheduler {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun mainThread(): Scheduler {
        return Schedulers.trampoline()
    }

}