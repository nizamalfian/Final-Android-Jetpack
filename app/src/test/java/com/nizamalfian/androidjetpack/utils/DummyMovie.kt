package com.nizamalfian.androidjetpack.utils
import com.nizamalfian.androidjetpack.data.local.entity.GenreEntity
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.data.local.entity.MovieWithGenres
import com.nizamalfian.androidjetpack.data.remote.response.MovieResponse

/**
 * Created by nizamalfian on 01/07/2019.
 */

fun getDummyRemoteMovies():ArrayList<MovieResponse>{
    val movies:ArrayList<MovieResponse> =  ArrayList()
    movies.run {
        add(MovieResponse(
            429617,
            "Spider-Man: Far from Home",
            "Spider-Man: Far from Home",
            "/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
            134,
            7.1,
            367.979,
            "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            "2019-06-28",
            "en",
            ArrayList<Int>().apply {
                add(28)
                add(12)
                add(878)
                add(35)
                add(10749)
            }
        ))

        add(MovieResponse(
            301528,
            "Toy Story 4",
            "Toy Story 4",
            "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
            814,
            7.8,
            309.734,
            "/m67smI1IIMmYzCl9axvKNULVKLr.jpg",
            "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
            "2019-06-19",
            "en",
            ArrayList<Int>().apply {
                add(12)
                add(16)
                add(35)
                add(10751)
            }
        ))

        add(MovieResponse(
            486131,
            "Shaft",
            "Shaft",
            "/kfZqwGuvEBAysAbCsa0QLKoSYR.jpg",
            170,
            6.1,
            150.521,
            "/103d4ObBCWbB6PtOOjZ7C1FSpVl.jpg",
            "JJ, aka John Shaft Jr., may be a cyber security expert with a degree from MIT, but to uncover the truth behind his best friend’s untimely death, he needs an education only his dad can provide. Absent throughout JJ’s youth, the legendary locked-and-loaded John Shaft agrees to help his progeny navigate Harlem’s heroin-infested underbelly.",
            "2019-06-14",
            "en",
            ArrayList<Int>().apply {
                add(28)
                add(35)
                add(80)
            }
        ))
    }
    return movies
}

fun getDummyLocalMovie(): MovieEntity = getDummyLocalMovies()[0]

fun getDummyLocalMovies():ArrayList<MovieEntity>{
    val movies:ArrayList<MovieEntity> =  ArrayList()
    movies.run {
        add(
            MovieEntity(
                429617,
                "Spider-Man: Far from Home",
                "Spider-Man: Far from Home",
                "/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
                134,
                7.1,
                367.979,
                "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "2019-06-28",
                "en"
            )
        )

        add(
            MovieEntity(
                301528,
                "Toy Story 4",
                "Toy Story 4",
                "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                814,
                7.8,
                309.734,
                "/m67smI1IIMmYzCl9axvKNULVKLr.jpg",
                "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                "2019-06-19",
                "en"
            )
        )

        add(
            MovieEntity(
                486131,
                "Shaft",
                "Shaft",
                "/kfZqwGuvEBAysAbCsa0QLKoSYR.jpg",
                170,
                6.1,
                150.521,
                "/103d4ObBCWbB6PtOOjZ7C1FSpVl.jpg",
                "JJ, aka John Shaft Jr., may be a cyber security expert with a degree from MIT, but to uncover the truth behind his best friend’s untimely death, he needs an education only his dad can provide. Absent throughout JJ’s youth, the legendary locked-and-loaded John Shaft agrees to help his progeny navigate Harlem’s heroin-infested underbelly.",
                "2019-06-14",
                "en"
            )
        )
    }
    return movies
}

fun getDummyLocalTVShows():ArrayList<MovieEntity>{
    val tvShows:ArrayList<MovieEntity> =  ArrayList()
    tvShows.run {
        add(
            MovieEntity(
                63926,
                "One-Punch Man",
                "ワンパンマン",
                "/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg",
                420,
                8.0,
                510.566,
                "/s0w8JbuNNxL1YgaHeDWih12C3jG.jpg",
                "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
                "2015-10-04",
                "ja",
                true
            )
        )

        add(
            MovieEntity(
                11634,
                "See No Evil: The Moors Murders",
                "See No Evil: The Moors Murders",
                "/b71BaRjp9TwxUZodLGgSRIlkfL8.jpg",
                8,
                6.6,
                477.731,
                "/7AKhSfJHnVi0zXQS4eJirHDs22p.jpg",
                "The dramatisation of one of the most notorious killing sprees in British history.",
                "2006-05-14",
                "en",
                true
            )
        )

        add(
            MovieEntity(
                60735,
                "The Flash",
                "The Flash",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                2726,
                6.7,
                254.184,
                "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                "en",
                true
            )
        )
    }
    return tvShows
}

fun getDummyLocalGenres(movieId:Int):ArrayList<GenreEntity>{
    return ArrayList<GenreEntity>().apply{
        add(
            GenreEntity(28, movieId, "Action")
        )
        add(
            GenreEntity(12, movieId, "Adventure")
        )
        add(
            GenreEntity(16, movieId, "Animation")
        )
    }
}

fun getGenerateDummyMovieWithGenres(movie: MovieEntity, isBookmarked:Boolean): MovieWithGenres {
    movie.isBookmarked=isBookmarked
    return MovieWithGenres(
        movie,
        getDummyLocalGenres(movie.movieId)
    )
}