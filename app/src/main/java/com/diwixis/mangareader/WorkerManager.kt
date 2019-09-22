package com.diwixis.mangareader

import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.get

/**
 * Предоставляет
 *
 * @author П. Густокашин (Diwixis)
 */
class WorkerManager(name: String) : KoinComponent {
    private val job = SupervisorJob()
    val main = MainScope() + CoroutineName(name)
    val backgroundScope = CoroutineScope(get<CoroutineDispatcher>() + job)
    val bg = backgroundScope.coroutineContext

    fun cancel() {
        job.cancel()
    }
}