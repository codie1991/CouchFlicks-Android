package nz.co.south45.couchflicks.adapters;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nz.co.south45.couchflicks.R;
import nz.co.south45.couchflicks.activities.MovieDetailActivity;
import nz.co.south45.data.Constants;
import nz.co.south45.data.models.Movie;

/**
 * Created by codie on 4/04/15.
 */
public class MovieFeedAdapter extends RecyclerView.Adapter<MovieFeedAdapter.ViewHolder>{
    private static final String TAG = MovieFeedAdapter.class.getSimpleName();

    private List<Movie> items;
    private int rowLayout;
    private Context mContext;
    private Fragment fragment;
    private String TAB_ID;

    public MovieFeedAdapter(List<Movie> items, int rowLayout, Context mContext, Fragment fragment, String TAB_ID) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
        this.fragment = fragment;
        this.TAB_ID = TAB_ID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = items.get(position);
        String moviePosterUrl = movie.getImages().getPoster().getMedium();
        holder.movieId = movie.getIds().getTrakt();

        if (TAB_ID.equals("TRENDING")){
            Glide.with(fragment).load(moviePosterUrl).placeholder(R.drawable.movie_placeholder).into(holder.posterImageView);
        } else {
            Picasso.with(mContext).load(moviePosterUrl).placeholder(R.drawable.movie_placeholder).into(holder.posterImageView);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<Movie> data) {
        items = data;
        notifyDataSetChanged();
    }

    public void addItem(int position, Movie movie) {
        items.add(position, movie);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        items = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView posterImageView;
        TextView movieTitle;
        Integer movieId;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            posterImageView = (ImageView) itemView.findViewById(R.id.list_poster);
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "OnCLick movie item");
            Intent i = new Intent(view.getContext(), MovieDetailActivity.class);
            Bundle pkg = new Bundle();
            pkg.putString(Constants.MOVIE_ID, movieId.toString());
            i.putExtras(pkg);
            view.getContext().startActivity(i);
        }
    }
}
