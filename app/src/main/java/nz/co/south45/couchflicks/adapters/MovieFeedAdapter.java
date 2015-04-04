package nz.co.south45.couchflicks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nz.co.south45.couchflicks.R;
import nz.co.south45.couchflicks.components.PosterImageView;
import nz.co.south45.data.models.Movie;

/**
 * Created by codie on 4/04/15.
 */
public class MovieFeedAdapter extends RecyclerView.Adapter<MovieFeedAdapter.ViewHolder>{
    private static final String TAG = MovieFeedAdapter.class.getSimpleName();

    private List<Movie> items;
    private int rowLayout;
    private Context mContext;

    public MovieFeedAdapter(List<Movie> items, int rowLayout, Context mContext) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
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
        Picasso.with(mContext).load(moviePosterUrl).into(holder.posterImageView);
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

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView posterImageView;
        TextView movieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.list_poster);
//            movieTitle = (TextView) itemView.findViewById(R.id.list_title);
        }
    }
}
