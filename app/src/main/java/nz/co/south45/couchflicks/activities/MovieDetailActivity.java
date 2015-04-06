package nz.co.south45.couchflicks.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashMap;
import java.util.Map;

import nz.co.south45.couchflicks.R;
import nz.co.south45.couchflicks.components.CollapsingTitleLayout;
import nz.co.south45.couchflicks.components.PosterImageView;
import nz.co.south45.data.Constants;
import nz.co.south45.data.models.Movie;
import nz.co.south45.data.rest.TraktClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by codie on 5/04/15.
 */
public class MovieDetailActivity extends BaseActivity {
    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    String movieId;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Log.i("MovieDetailActivity", "onCreate");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i("MovieDetailActivity", "extras != null");
            String movieId = extras.getString(Constants.MOVIE_ID);
            if (movieId != null) {
                Log.i("MovieDetailActivity", "movieId = " + movieId);
                this.movieId = movieId;
            }
        }

        setupToolbar();
        downloadDetails();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.movie_toolbar);
        setSupportActionBar(mToolbar);
        Log.i("MovieDetailActivity", "setup Toolbar");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void downloadDetails(){
        Map<String,Object> params = new HashMap<>();
        params.put("extended","full,images");


        TraktClient.getApiInterface().getMovieDetail(movieId, params, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                Log.i("MovieDetailActivity", "Success on Download");
                setupLayout(movie);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void setupLayout(Movie movie) {
        final CollapsingTitleLayout collapsingTitleLayout = (CollapsingTitleLayout) findViewById(R.id.backdrop_toolbar);
        TextView tagline = (TextView) findViewById(R.id.movie_tagline);
        PosterImageView posterImage = (PosterImageView) findViewById(R.id.movie_poster);
        TextView description = (TextView) findViewById(R.id.movie_description);
        TextView description2 = (TextView) findViewById(R.id.movie_description2);
        TextView description3 = (TextView) findViewById(R.id.movie_description3);
        TextView description4 = (TextView) findViewById(R.id.movie_description4);

        collapsingTitleLayout.setTitle(movie.getTitle());
        collapsingTitleLayout.setScrollOffset(100);
        tagline.setText(movie.getTagline());
        String moviePosterUrl = movie.getImages().getPoster().getThumb();
        String heroUrl = movie.getImages().getBanner().getFull();

        Picasso.with(this).load(moviePosterUrl).into(posterImage);
        Picasso.with(this).load(heroUrl).into(new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                collapsingTitleLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                Log.i(TAG,"Success TO LOAD BITMAP");
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.i(TAG,"FAILED TO LOAD BITMAP");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.i(TAG,"ON PREPARE TO LOAD BITMAP");
            }
        });

        description.setText(movie.getOverview());
        description2.setText(movie.getOverview());
        description3.setText(movie.getOverview());
        description4.setText(movie.getOverview());
    }
}
