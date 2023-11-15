package com.leslienan.core_network

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/15.
 * PS:
 */
object ViewModelExt {

    /**
     * 简单的处理当前接口的失败，然后可自定义失败逻辑
     */
    suspend fun <T> ViewModel.handleSingle(
        block: suspend () -> T,
        showLoading: Boolean = false,
        autoToast: Boolean = true,//自动弹出错误toast
        onFailure: suspend (e: Exception) -> Unit = {}
    ): T? {
        return try {
            if (showLoading) loading.emit(true)
            val invoke = block.invoke()
            if (showLoading) loading.emit(false)
            invoke
        } catch (e: Exception) {
            //通用处理异常
            onError(this, e, autoToast)
            //通用处理异常结束
            onFailure.invoke(e)
            if (showLoading) loading.emit(false)
            null
        }
    }

    /**
     * 顺序执行接口，若中间有接口失败则后边任务不执行
     * 此对象对失败进行处理
     */
    val ViewModel.exceptionHandler: CoroutineExceptionHandler
        get() {
            return CoroutineExceptionHandler { context, e ->
                viewModelScope.launch {
                    onError(this@exceptionHandler, e, true)
                    loading.emit(false)
                }
            }
        }

    /**
     * 同时异步请求多个接口，全部回调后返回
     */
    suspend fun ViewModel.asyncLaunch(
        vararg block: suspend () -> Any?,
        showLoading: Boolean = false,
        onFailure: suspend (e: Exception) -> Unit = {}
    ): List<Any?> {
        return supervisorScope {
            if (showLoading) loading.emit(true)
            val deferredList = mutableListOf<Deferred<*>>()
            val answerList = mutableListOf<Any?>()
            block.forEach {
                deferredList.add(async { it.invoke() })
            }
            deferredList.forEach {
                answerList.add(
                    try {
                        it.await()
                    } catch (e: Exception) {
                        //通用处理异常
                        onError(this@asyncLaunch, e, true)
                        //通用处理异常结束
                        onFailure.invoke(e)
                        if (showLoading) loading.emit(false)
                        null
                    }
                )
            }
            if (showLoading) loading.emit(false)
            return@supervisorScope answerList
        }
    }

    /**
     * 统一处理网络异常
     * @param autoToast:是否自动弹出所有失败msg的toast
     */
    private suspend fun onError(viewModel: ViewModel, throwable: Throwable, autoToast: Boolean) {
        if (throwable is NetworkException) {
            /*if (throwable.code == NetworkException.SHOW_API_MESSAGE) {
                Log.e("net", "|||||| ApiException.SHOW_API_MESSAGE ||||||")
                if (!TextUtils.isEmpty(throwable.apiMessage)) viewModel.toast.emit(throwable.apiMessage)
            } else if (throwable.code == NetworkException.LOGIN_FAILED || throwable.code == NetworkException.LOGIN_FAILED2) {
                Log.e("net", "|||||| ApiException.LOGIN_FAILED ||||||")
                viewModel.toast.emit(BaseApplication.INSTANCE.getString(R.string.net_login_failed))
                viewModel.login.emit(Unit)
            } else {
                if (autoToast) viewModel.toast.emit(throwable.apiMessage)
            }*/
            if (autoToast) viewModel.toast.emit(throwable.apiMessage)

        } else {
            Log.e("net", "|||||| onError throwable ||||||   " + throwable.message)
            throwable.printStackTrace()
            /*val toastMsg = when (throwable) {
                is SocketTimeoutException -> BaseApplication.INSTANCE.getString(R.string.net_connect_timeout)
                is ConnectException -> BaseApplication.INSTANCE.getString(R.string.net_can_not_connect)
                is FileNotFoundException -> BaseApplication.INSTANCE.getString(R.string.net_server_error)
                else -> {
                    throwable.printStackTrace()
                    BaseApplication.INSTANCE.getString(R.string.net_back_error)
                }
            }
            viewModel.toast.emit(toastMsg)*/
            viewModel.toast.emit(throwable.message ?: "")
        }
    }
}
