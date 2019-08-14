package com.nizamalfian.androidjetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nizamalfian.androidjetpack.data.remote.ApiResponse
import com.nizamalfian.androidjetpack.data.vo.Resource
import com.nizamalfian.androidjetpack.data.remote.StatusResponse.*
import com.nizamalfian.androidjetpack.utils.AppExecutors

/**
 * Created by nizamalfian on 26/07/2019.
 */
abstract class NetworkBoundResource<ResultType,RequestType>(private val appExecutor:AppExecutors){

    private val result:MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        result.value = Resource.loading()
        val dbSource : LiveData<ResultType> = loadFromDB()
        result.addSource(dbSource) { data->
            result.removeSource(dbSource)
            if(shouldFetch(data))
                fetchFromNetwork(dbSource)
            else
                result.addSource(dbSource) { newData->result.value= Resource.success(newData)}
        }
    }

    protected fun onFetchFailed(){}

    protected abstract fun loadFromDB():LiveData<ResultType>

    protected abstract fun shouldFetch(data:ResultType):Boolean

    protected abstract fun createCall():LiveData<ApiResponse<RequestType>>?

    protected abstract fun saveCallResult(data:RequestType)

    private fun fetchFromNetwork(dbSource:LiveData<ResultType>){
        val apiResponse:LiveData<ApiResponse<RequestType>>? = createCall()
        result.addSource(dbSource){newData->result.value= Resource.loading(newData)}
        apiResponse?.let{
            result.addSource(it){response->
                result.removeSource(it)
                result.removeSource(dbSource)
                when(response.status){
                    SUCCESS->{
                        appExecutor.diskIO.execute{
                            saveCallResult(response.body)
                            appExecutor.mainThread.execute{
                                result.addSource(loadFromDB()){newData-> result.value=
                                    Resource.success(newData) }
                            }
                        }
                    }
                    EMPTY->{
                        appExecutor.mainThread.execute{result.addSource(loadFromDB()) { newData->result.value=
                            Resource.success(newData)} }
                    }
                    ERROR->{
                        onFetchFailed()
                        result.addSource(dbSource){newData->result.value=
                            Resource.error(response.message?:"",newData)}
                    }
                }
            }
        }
    }

    fun asLiveData():LiveData<Resource<ResultType>> = result
}