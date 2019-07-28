package app.interactive.academy.data.source.vo

import app.interactive.academy.data.source.vo.Status.*

/**
 * Created by nizamalfian on 26/07/2019.
 */
data class Resource<T>(val status:Status,val data:T?,private val message:String?) {
    companion object{
        fun <T>success(data:T):Resource<T> = Resource(SUCCESS,data,null)
        fun <T>error(msg:String,data:T):Resource<T> = Resource(ERROR,data,msg)
        fun <T>loading(data:T):Resource<T> = Resource(LOADING,data,null)
        fun <T>loading():Resource<T> = Resource(LOADING,null,null)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true
        if(other==null||javaClass!=other.javaClass)
            return false
        val resource:Resource<*> = other as Resource<*>
        if(status!=resource.status)
            return false
        return if (if (message != null) message != resource.message else resource.message != null) {
            false
        } else data?.equals(resource.data) ?: (resource.data == null)
    }

    override fun hashCode(): Int {
        var result=status.hashCode()
        result=31*result+(message?.hashCode()?:0)
        result=31*result+(data?.hashCode()?:0)
        return result
    }
}