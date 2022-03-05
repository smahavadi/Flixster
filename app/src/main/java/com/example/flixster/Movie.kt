package com.example.flixster


import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie (
    val title: String,
    val votingAverage: Double,
    val movieId: Int,
    private val posterPath: String,
    val overview: String, ) : Parcelable {

    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getString("title"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }

}