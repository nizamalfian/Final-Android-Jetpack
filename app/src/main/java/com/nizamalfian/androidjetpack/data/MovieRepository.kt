package com.nizamalfian.androidjetpack.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nizamalfian.androidjetpack.data.local.LocalRepository
import com.nizamalfian.androidjetpack.data.local.entity.*
import com.nizamalfian.androidjetpack.data.remote.ApiResponse
import com.nizamalfian.androidjetpack.data.remote.RemoteRepository
import com.nizamalfian.androidjetpack.data.remote.response.*
import com.nizamalfian.androidjetpack.data.vo.Resource
import com.nizamalfian.androidjetpack.ui.movie.MovieFragment
import com.nizamalfian.androidjetpack.utils.AppExecutors

/**
 * Created by L
 *
 * on 7/8/2019
 */
class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {
    companion object {
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteRepository,
            appExecutors: AppExecutors
        ): MovieRepository {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            MovieRepository(localRepository, remoteRepository, appExecutors)
                    }
                }
            }
            return INSTANCE as MovieRepository
        }
    }

    override fun getAllMoviesAsPaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localRepository.getAllMoviesAsPaged(), 20).build()

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? = remoteRepository.getAllMoviesAsLiveData()

            override fun saveCallResult(data: List<MovieResponse>) {
                ArrayList<MovieEntity>().run {
                    data.forEach {
                        add(
                            MovieEntity(
                                it.id,
                                it.title,
                                it.originalTitle,
                                it.posterPath,
                                it.voteCount,
                                it.voteAverage,
                                it.popularity,
                                it.backdropPath,
                                it.overview,
                                it.releaseDate,
                                it.language,
                                false
                            )
                        )
                    }
                    Log.d(MovieFragment.TAG+"_saveCallResult",data.size.toString())
                    localRepository.insertMovies(this)
                }
            }

        }.asLiveData()
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                val result=localRepository.getAllMovies()
                Log.d(MovieFragment.TAG+"_loadFromDB",result.value?.size.toString())
                return result
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                val result = data.isEmpty()
                Log.d(MovieFragment.TAG+"_shouldFetch",result.toString())
                return result
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                val result = remoteRepository.getAllMoviesAsLiveData()
                Log.d(MovieFragment.TAG+"_createCall",result.value?.body?.size.toString())
                return result
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                ArrayList<MovieEntity>().run {
                    data.forEach {
                        add(
                            MovieEntity(
                                it.id,
                                it.title,
                                it.originalTitle,
                                it.posterPath,
                                it.voteCount,
                                it.voteAverage,
                                it.popularity,
                                it.backdropPath,
                                it.overview,
                                it.releaseDate,
                                it.language,
                                false
                            )
                        )
                    }
                    Log.d(MovieFragment.TAG+"_saveCallResult",data.size.toString())
                    localRepository.insertMovies(this)
                }
            }
        }.asLiveData()
    }

    override fun getAllTVShows(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localRepository.getAllTVShows()

            override fun shouldFetch(data: List<MovieEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteRepository.getAllTVShowsAsLiveData()

            override fun saveCallResult(data: List<MovieResponse>) {
                ArrayList<MovieEntity>().run {
                    data.forEach {
                        add(
                            MovieEntity(
                                it.id,
                                it.title,
                                it.originalTitle,
                                it.posterPath,
                                it.voteCount,
                                it.voteAverage,
                                it.popularity,
                                it.backdropPath,
                                it.overview,
                                it.releaseDate,
                                it.language,
                                true
                            )
                        )
                    }
                    localRepository.insertMovies(this)
                }
            }
        }.asLiveData()
    }

    override fun getMovieWithGenre(movieId: Int): LiveData<Resource<MovieWithGenres>> {
        return object : NetworkBoundResource<MovieWithGenres, List<GenreResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieWithGenres> = localRepository.getMovieWithGenres(movieId)

            override fun shouldFetch(data: MovieWithGenres): Boolean = data.genres.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<GenreResponse>>> =
                remoteRepository.getAllGenresByMovieAsLiveData(movieId)

            override fun saveCallResult(data: List<GenreResponse>) {
                ArrayList<GenreEntity>().run {
                    data.forEach {
                        add(
                            GenreEntity(
                                it.id,
                                movieId,
                                it.name
                            )
                        )
                    }
                    localRepository.insertGenres(this)
                }
            }
        }.asLiveData()
    }

    override fun getAllGenresByMovie(movieId: Int): LiveData<Resource<List<GenreEntity>>> {
        return object : NetworkBoundResource<List<GenreEntity>, List<GenreResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<GenreEntity>> = localRepository.getAllGenresByMovie(movieId)

            override fun shouldFetch(data: List<GenreEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<GenreResponse>>>? =
                remoteRepository.getAllGenresByMovieAsLiveData(movieId)

            override fun saveCallResult(data: List<GenreResponse>) {
                ArrayList<GenreEntity>().run {
                    data.forEach {
                        add(
                            GenreEntity(
                                it.id,
                                movieId,
                                it.name
                            )
                        )
                    }
                    localRepository.insertGenres(this)
                }
            }
        }.asLiveData()
    }

    override fun getBookmarkedMoviesAsPaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localRepository.getBookmarkedMoviesAsPaged(), 20).build()

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? = null

            override fun saveCallResult(data: List<MovieResponse>) {}

        }.asLiveData()
    }

    override fun getBookmarkedTVShowsAsPaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localRepository.getBookmarkedTVShowsAsPaged(), 20).build()

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? = null

            override fun saveCallResult(data: List<MovieResponse>) {}

        }.asLiveData()
    }

    override fun setMovieBookmark(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO.execute {
            localRepository.setMovieBookmark(movie, state)
        }
    }

    override fun getBookmarkedCoursesAsPaged(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object : NetworkBoundResource<PagedList<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CourseEntity>> =
                LivePagedListBuilder(localRepository.getBookmarkedCoursesAsPaged(), 20).build()

            override fun shouldFetch(data: PagedList<CourseEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>>? = null

            override fun saveCallResult(data: List<CourseResponse>) {}

        }.asLiveData()
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CourseEntity>> = localRepository.getAllCourses()

            override fun shouldFetch(data: List<CourseEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> =
                remoteRepository.getAllCoursesAsLiveData()

            override fun saveCallResult(data: List<CourseResponse>) {
                ArrayList<CourseEntity>().run {
                    data.forEach {
                        add(
                            CourseEntity(
                                it.id,
                                it.title,
                                it.description,
                                it.date,
                                it.imagePath
                            )
                        )
                    }
                    localRepository.insertCourses(this)
                }
            }
        }.asLiveData()
    }

    override fun getCourseWithModule(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CourseWithModule> = localRepository.getCourseWithModules(courseId)

            override fun shouldFetch(data: CourseWithModule): Boolean = data.modules.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteRepository.getAllModulesByCourseAsLiveData(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                ArrayList<ModuleEntity>().run {
                    data.forEach {
                        add(
                            ModuleEntity(
                                it.moduleId,
                                courseId,
                                it.title,
                                it.position
                            )
                        )
                    }
                    localRepository.insertModules(this)
                }
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> = localRepository.getAllModulesByCourse(courseId)

            override fun shouldFetch(data: List<ModuleEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>>? =
                remoteRepository.getAllModulesByCourseAsLiveData(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                ArrayList<ModuleEntity>().run {
                    data.forEach {
                        add(
                            ModuleEntity(
                                it.moduleId,
                                courseId,
                                it.title,
                                it.position
                            )
                        )
                    }
                    localRepository.insertModules(this)
                }
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> = localRepository.getModuleWithContent(moduleId)

            override fun shouldFetch(data: ModuleEntity): Boolean = data.contentEntity == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>>? =
                remoteRepository.getContentAsLiveData(moduleId) as LiveData<ApiResponse<ContentResponse>>

            override fun saveCallResult(data: ContentResponse) {
                localRepository.updateContent(data.content, moduleId)
            }
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        appExecutors.diskIO.execute {
            localRepository.setCourseBookmark(course, state)
        }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutors.diskIO.execute {
            localRepository.setReadModule(module)
        }
    }

}