package com.diwixis.mangareader.data.remote

import kotlin.reflect.KClass

/**
 * Фабрика для взаимодействия с необходимым экземпляром клиента.
 *
 * @param client реальный клиент
 * @param stub заглушка с mock-сервером
 *
 * @author П. Густокашин (Diwixis)
 */
class HttpClientFactory(
    private val client: HttpClient
    //private val stub: HttpClient? = null
) {
    /** @return Новый экземпляр API сервиса. */
    fun <T : Any> create(service: KClass<T>): T {
        // [stub] сейчас не используется, планируется добавить.
        return client.retrofit.create(service.java)
    }
}