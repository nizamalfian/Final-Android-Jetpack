package app.interactive.academy.data.source.remote

import android.app.Application
import android.util.Log
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Created by L
 *
 * on 7/8/2019
 */
class JSONHelper(private val application: Application){
    private fun parsingFileToString(fileName:String):String{
        var result=""
        try{
            application.assets.open(fileName).also{
                ByteArray(it.available()).apply{
                    it.read(this)
                    it.close()
                    result=String(this)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
            Log.e("parsing",e.message)
        }finally {
            return result
        }
    }

    fun loadCourses():List<CourseResponse>{
        val result=ArrayList<CourseResponse>()
        try{
            JSONObject(parsingFileToString("CourseResponses.json")).getJSONArray("courses").run {
                for(i in 0 until length()-1){
                    getJSONObject(i).run {
                        result.add(
                            CourseResponse(
                                getString("id"),
                                getString("title"),
                                getString("description"),
                                getString("date"),
                                getString("imagePath")
                            )
                        )
                    }
                }
            }
        }catch(e:IOException){
            e.printStackTrace()
            Log.e("parsing_io_1",e.message)
        }catch(e:JSONException){
            e.printStackTrace()
            Log.e("parsing_json_1",e.message)
        }catch(e:Exception){
            e.printStackTrace()
            Log.e("parsing_ex_1",e.message)
        }finally {
            return result
        }
    }

    fun loadModule(courseId:String):List<ModuleResponse>{
        val result=ArrayList<ModuleResponse>()
        try{
            parsingFileToString(String.format("Module_%s.json",courseId))?.run {
                JSONObject(this).getJSONArray("modules").run {
                    for(i in 0 until length()-1){
                        getJSONObject(i).run {
                            result.add(
                                ModuleResponse(
                                    getString("moduleId"),
                                    courseId,
                                    getString("title"),
                                    getString("position").toInt()
                                )
                            )
                        }
                    }
                }
            }
        }catch(e:IOException){
            e.printStackTrace()
            Log.e("parsing_io_2",e.message)
        }catch(e:JSONException){
            e.printStackTrace()
            Log.e("parsing_json_2",e.message)
        }finally {
            return result
        }
    }

    fun loadContent(moduleId:String):ContentResponse?{
        var result:ContentResponse?=null
        try{
            parsingFileToString(String.format("Content_%s.json",moduleId))?.run {
                result= ContentResponse(
                    moduleId,
                    JSONObject(this).getString("content")
                )
            }
        }catch(e:IOException){
            e.printStackTrace()
            Log.e("parsing_io_3",e.message)
        }catch (e:JSONException){
            e.printStackTrace()
            Log.d("parsing_json_3",e.message)
        }finally {
            return result
        }
    }
}