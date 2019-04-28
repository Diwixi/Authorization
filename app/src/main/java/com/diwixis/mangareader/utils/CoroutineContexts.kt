package com.diwixis.mangareader.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Обобщение сразу для двух диспетчеров
 *
 * @author П. Густокашин (Diwixis)
 */
class CoroutineContexts(val ui: CoroutineDispatcher, val bg: CoroutineDispatcher)