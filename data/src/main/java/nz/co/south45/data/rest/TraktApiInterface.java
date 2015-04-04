package nz.co.south45.data.rest;

import java.util.ArrayList;
import java.util.Map;

import nz.co.south45.data.models.Movie;
import nz.co.south45.data.models.TrendingResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by codie on 4/04/15.
 */
public interface TraktApiInterface {
    static final String MOVIES_POPULAR = "/movies/popular";
    static final String MOVIES_TRENDING = "/movies/trending";

    @GET(MOVIES_TRENDING) void getTrendingMovies(@QueryMap Map<String,Object> params, Callback<ArrayList<TrendingResponse>> trendingResponseCallback);

    @GET(MOVIES_POPULAR) void getPopularMovies(@QueryMap Map<String,Object> params, Callback<ArrayList<Movie>> popularResponseCallback);

}
