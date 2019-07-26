package app.interactive.academy.data.source.remote

import app.interactive.academy.data.source.remote.StatusResponse.*

/**
 * Created by nizamalfian on 26/07/2019.
 */
class ApiResponse<T>(val status:StatusResponse,val body:T,val message:String?) {
    companion object{
        fun <T>success(body:T):ApiResponse<T> = ApiResponse(SUCCESS,body,null)

        fun <T>empty(body:T,msg:String):ApiResponse<T> = ApiResponse(EMPTY,body,msg)

        fun <T>error(msg:String,body:T):ApiResponse<T> = ApiResponse(ERROR,body,msg)
    }
}