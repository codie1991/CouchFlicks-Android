package nz.co.south45.couchflicks.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
    String movieId;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_movie_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieId = extras.getString(Constants.MOVIE_ID);
            if (movieId != null) {
                this.movieId = movieId;
            }
        }

        setupToolbar();
        downloadDetails();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void downloadDetails(){
        TraktClient.getApiInterface().getMovieDetail(movieId, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
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

        mToolbar.setTitle(movie.getTitle());
        tagline.setText(movie.getTagline());
        String moviePosterUrl = movie.getImages().getPoster().getThumb();
        String heroUrl = movie.getImages().getBanner().getFull();
        Picasso.with(this).load(moviePosterUrl).into(posterImage);
        Picasso.with(this).load(heroUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                collapsingTitleLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        description.setText(movie.getOverview());
    }
}
