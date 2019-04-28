package com.diwixis.mangareader.utils

import com.diwixis.mangareader.presentation.common.Response
import kotlinx.coroutines.*

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AsyncUtils(private val coroutineContexts: CoroutineContexts, root: Job) : CoroutineScope {
    override val coroutineContext = coroutineContexts.ui + root

    @Synchronized
    fun launchSuspend(tryBlock: Task) = launch { tryBlock() }

    @Synchronized
    suspend fun <T> asyncAwait(block: TaskResult<T>): Response<T> = async(block).await()

    @Synchronized
    suspend fun <T> async(block: TaskResult<T>): Deferred<Response<T>> =
        coroutineScope { async(coroutineContexts.bg) { block() } }
}