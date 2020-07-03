package com.ottoboni.movies.connectivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.movies.connectivity.ConnectivityViewModel.ConnectivityError.GENERIC_ERROR
import com.ottoboni.movies.connectivity.ConnectivityViewModel.ConnectivityError.HTTP_ERROR
import com.ottoboni.movies.connectivity.ConnectivityViewModel.ConnectivityError.NO_INTERNET
import com.ottoboni.movies.data.source.remote.error.NotFoundException
import com.ottoboni.movies.data.source.remote.error.UnauthorizedException
import com.ottoboni.movies.data.source.remote.error.UnknownNetworkErrorException
import com.ottoboni.movies.domain.dispatchers.DispatcherMap
import com.ottoboni.movies.util.SingleLiveEvent
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

abstract class ConnectivityViewModel(private val dispatcherMap: DispatcherMap) : ViewModel() {

    protected val actionOnNoInternetError = SingleLiveEvent<Any>()
    protected val actionOnHttpError = SingleLiveEvent<String?>()
    protected val actionOnGenericError = SingleLiveEvent<Any>()

    protected fun safeLaunch(block: suspend () -> Unit) {
        viewModelScope.launch(dispatcherMap.ui) {
            try {
                withContext(dispatcherMap.io) { block() }
            } catch (e: ConnectException) {
                onLaunchException(NO_INTERNET)
            } catch (e: UnknownHostException) {
                onLaunchException(NO_INTERNET)
            } catch (e: UnauthorizedException) {
                onLaunchException(HTTP_ERROR, e.message)
            } catch (e: NotFoundException) {
                onLaunchException(HTTP_ERROR, e.message)
            } catch (e: UnknownNetworkErrorException) {
                onLaunchException(HTTP_ERROR, e.message)
            } catch (e: IOException) {
                onLaunchException(GENERIC_ERROR)
            } catch (e: JsonDataException) {
                onLaunchException(GENERIC_ERROR)
            }
        }
    }

    private fun onLaunchException(error: ConnectivityError, errorMessage: String? = null) =
        when (error) {
            NO_INTERNET -> actionOnNoInternetError.call()
            HTTP_ERROR -> actionOnHttpError.call(errorMessage)
            GENERIC_ERROR -> actionOnGenericError.call()
        }

    enum class ConnectivityError {
        NO_INTERNET,
        HTTP_ERROR,
        GENERIC_ERROR
    }
}