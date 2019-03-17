package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.data.exception.wrapClarify
import com.diwixis.mangareader.domain.model.network.ErrorWrapper
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.*
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class RxErrorHandlingAdapterFactory(
    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create(),
    private val mapper: ObjectMapper = ObjectMapper()
) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return original.get(returnType, annotations, retrofit)?.let { adapter -> RxCallAdapterWrapper(adapter, mapper) }
    }

    private class RxCallAdapterWrapper<R>(
        private val wrapper: CallAdapter<R, *>,
        private val mapper: ObjectMapper
    ) : CallAdapter<R, Any> {

        override fun adapt(call: Call<R>): Any {
            val adapt = wrapper.adapt(call)
            return when(adapt) {
                is Completable -> adapt.onErrorResumeNext { Completable.error(it.asRetrofitException()) }
                is Single<*> -> adapt.onErrorResumeNext { Single.error(it.asRetrofitException()) }
                is Maybe<*> -> adapt.onErrorResumeNext { error: Throwable -> Maybe.error(error.asRetrofitException()) }
                is Observable<*> -> adapt.onErrorResumeNext { error: Throwable -> Observable.error(error.asRetrofitException()) }
                is Flowable<*> -> adapt.onErrorResumeNext { error: Throwable -> Flowable.error(error.asRetrofitException()) }
                else -> adapt
            }
        }

        override fun responseType(): Type = wrapper.responseType()

        private fun Throwable.asRetrofitException(): Throwable {
            return when (this) {
                is HttpException -> {
                    val error = response().errorBody()?.string()?.let { errorBody ->
                        try {
                            mapper.readValue(errorBody, ErrorWrapper::class.java)
                        } catch (err: Throwable) {
                            null
                        }
                    }
                    error?.let { ApiException(errorWrapper = it, cause = this).wrapClarify() } ?: this
                }
                else -> this
            }
        }
    }
}