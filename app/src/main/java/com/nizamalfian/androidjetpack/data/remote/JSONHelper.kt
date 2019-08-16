package com.nizamalfian.androidjetpack.data.remote

import android.app.Application
import android.util.Log
import com.nizamalfian.androidjetpack.data.remote.response.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by L
 *
 * on 7/8/2019
 */
class JSONHelper(private val application: Application) {
    private fun parsingFileToString(fileName: String): String? {
        var result: String? = null
        try {
            application.assets.open(fileName).also {
                ByteArray(it.available()).apply {
                    it.read(this)
                    it.close()
                    result = String(this)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.e("parsingg", e.message)
        } finally {
            return result
        }
    }

    fun loadMovie(id: Int): MovieResponse? = loadMovies().first { it.id == id }

    fun loadMovies(): ArrayList<MovieResponse> {
        return ArrayList<MovieResponse>().also { result ->
            try {
                JSONObject(parsingFileToString("movie.json")).apply {
                    this.getJSONArray("results").apply {
                        for (i in 0 until this.length() - 1) {
                            this.getJSONObject(i).run {
                                val id = this.getInt("id")
                                val title = this.getString("title")
                                val originalTitle = this.getString("original_title")
                                val posterPath = this.getString("poster_path")
                                val voteCount = this.getInt("vote_count")
                                val voteAverage = this.getDouble("vote_average")
                                val popularity = this.getDouble("popularity")
                                val backdropPath = this.getString("backdrop_path")
                                val isAdult = this.getBoolean("adult")
                                val overview = this.getString("overview")
                                val releaseDate = this.getString("release_date")
                                val language = this.getString("original_language")
                                val genres: ArrayList<Int> = ArrayList()
                                val genresObj = this.getJSONArray("genre_ids")
                                for (j in 0 until genresObj.length() - 1) {
                                    val genreId = genresObj.getInt(j)
                                    genres.add(genreId)
                                }
                                result.add(
                                    MovieResponse(
                                        id,
                                        title,
                                        originalTitle,
                                        posterPath,
                                        voteCount,
                                        voteAverage,
                                        popularity,
                                        backdropPath,
                                        overview,
                                        releaseDate,
                                        language,
                                        genres,
                                        false,
                                        isAdult
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    fun loadTVShow(id: Int): MovieResponse? = loadTVShows().first { it.id == id }

    fun loadTVShows(): ArrayList<MovieResponse> {
        return ArrayList<MovieResponse>().also { result ->
            try {
                JSONObject(parsingFileToString("tvshow.json")).apply {
                    this.getJSONArray("results").apply {
                        for (i in 0 until this.length() - 1) {
                            this.getJSONObject(i).run {
                                val id = this.getInt("id")
                                val title = this.getString("name")
                                val originalTitle = this.getString("original_name")
                                val posterPath = this.getString("poster_path")
                                val voteCount = this.getInt("vote_count")
                                val voteAverage = this.getDouble("vote_average")
                                val popularity = this.getDouble("popularity")
                                val backdropPath = this.getString("backdrop_path")
                                val isAdult = false
                                val overview = this.getString("overview")
                                val releaseDate = this.getString("first_air_date")
                                val language = this.getString("original_language")
                                val genres: ArrayList<Int> = ArrayList()
                                val genresObj = this.getJSONArray("genre_ids")
                                for (j in 0 until genresObj.length() - 1) {
                                    val id = genresObj.getInt(j)
                                    genres.add(id)
                                }
                                result.add(
                                    MovieResponse(
                                        id,
                                        title,
                                        originalTitle,
                                        posterPath,
                                        voteCount,
                                        voteAverage,
                                        popularity,
                                        backdropPath,
                                        overview,
                                        releaseDate,
                                        language,
                                        genres,
                                        true,
                                        isAdult
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    fun loadGenresByMovie(movieId: Int): List<GenreResponse> {
        return ArrayList<GenreResponse>().also {
            JSONObject(parsingFileToString("genre.json")).getJSONArray("genres").run {
                for (i in 0 until length() - 1) {
                    val genreObj: JSONObject = getJSONObject(i)
                    val genreId = genreObj.getInt("id")
                    if (genreId == movieId) {
                        it.add(
                            GenreResponse(
                                genreId,
                                genreObj.getString("name")
                            )
                        )
                    }
                }
            }
        }
    }


    fun loadCourses(): List<CourseResponse> {
        val result = ArrayList<CourseResponse>()
        try {
            JSONObject(parsingFileToString("CourseResponses.json")).getJSONArray("courses").run {
                for (i in 0 until length() - 1) {
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
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("parsing_json_1", e.message)
        } finally {
            return result
        }
    }

    fun loadModule(courseId: String): List<ModuleResponse> {
        val result = ArrayList<ModuleResponse>()
        try {
            parsingFileToString(String.format("Module_%s.json", courseId))?.run {
                JSONObject(this).getJSONArray("modules").run {
                    for (i in 0 until length() - 1) {
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
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("parsing_json_2", e.message)
        } finally {
            return result
        }
    }

    fun loadContent(moduleId: String): ContentResponse? {
        var result: ContentResponse? = null
        try {
            parsingFileToString(String.format("Content_%s.json", moduleId))?.run {
                result = ContentResponse(
                    moduleId,
                    JSONObject(this).getString("content")
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.d("parsing_json_3", e.message)
        } finally {
            return result
        }
    }
}