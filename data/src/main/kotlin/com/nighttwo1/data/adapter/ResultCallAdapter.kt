package com.nighttwo1.data.adapter

import com.nighttwo1.domain.NetworkResult
import okhttp3.Request
import okio.IOException
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapter<R>(private val responseType: Type) : CallAdapter<R, Call<NetworkResult<R>>> {
    override fun responseType(): Type = responseType

    //    override fun adapt(call: Call<R>): NetworkResult<R> = NetworkResponseCall(call)
    override fun adapt(call: Call<R>): Call<NetworkResult<R>> { // in(Call<앞>), out(Call<뒤>)의 타입이 정해짐
        return NetworkResponseCall(call) // 얘는 커스텀으로 구현한 클래스로 아래에 나옴!
    }
}

class NightCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // 먼저 리턴 타입의 로우 타입이 Call인지 확인한다.
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        // 이후 리턴타입이 제네릭 인자를 가지는지 확인한다. 리턴 타입은 Call<?>가 돼야 한다.
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResult<Foo>> or Call<NetworkResult<out Foo>>"
        }
        // 리턴 타입에서 첫 번째 제네릭 인자를 얻는다.
        val responseType = getParameterUpperBound(0, returnType)

        // 기대한것 처럼 동작하기 위해선 추출한 제네릭 인자가 Result 타입이어야 한다.
        if (getRawType(responseType) != NetworkResult::class.java) {
            return null
        }

        // Result 클래스가 제네릭 인자를 가지는지 확인한다. 제네릭 인자로는 응답을 변환할 클래스를 받아야 한다.
        check(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResult<Foo> or NetworkResult<out Foo>"
        }

        // 마지막으로 Result의 제네릭 인자를 얻어서 CallAdapter를 생성한다.
        val successBodyType = getParameterUpperBound(0, responseType)

        return ResultCallAdapter<Any>(successBodyType)
    }
}

class NetworkResponseCall<T>(
    private val delegate: Call<T>,
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResult.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResult.Error(Exception()))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            error
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResult.Error(Exception()))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResult.Error(
                                    Exception()
                                )
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val networkResponse: NetworkResult<T> = when (throwable) {
                    is IOException -> NetworkResult.Error(
                        Exception()
                    )

                    else -> NetworkResult.Error(
                        Exception()
                    )
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResult<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}