package nz.co.south45.couchflicks.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nz.co.south45.couchflicks.R;
import nz.co.south45.couchflicks.adapters.MovieFeedAdapter;
import nz.co.south45.couchflicks.components.EndlessRecyclerOnScrollListener;
import nz.co.south45.data.models.Movie;
import nz.co.south45.data.models.TrendingResponse;
import nz.co.south45.data.rest.TraktClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by codie on 4/04/15.
 */
public class PopularFragment extends Fragment {
    private static final String TAG = PopularFragment.class.getSimpleName();

    ViewGroup mRootView;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MovieFeedAdapter mAdapter;
    Context context;

    TraktClient traktClient;
    List<Movie> popularMovies = new ArrayList<>();
    Integer page=1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_popular,container,false);
        context = getActivity();

        traktClient = TraktClient.getInstance();
        setupRecyclerView();

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getMovieFeedItems();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.clear();
        popularMovies.clear();
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.popular_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final Activity c = getActivity();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new MovieFeedAdapter(popularMovies, R.layout.item_movie_poster, c);
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
            }
        }).start();
        Log.i(TAG, "finished setup");

        // custom Listeners

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.popular_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrershMovieFeedItems();
            }
        });

        RecyclerView.OnScrollListener mOnScroll = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreMovieFeedItems();
            }
        };

        mRecyclerView.setOnScrollListener(mOnScroll);
    }

    private void loadFeed() {
        Log.i(TAG,"LoadingFeed");
        mAdapter.clear();

        for (int i = 0; i < popularMovies.size(); i++) {
            mAdapter.addItem(i, popularMovies.get(i));
        }
        mAdapter.updateList(popularMovies);
        mSwipeRefreshLayout.setRefreshing(false);
        page++;
        Log.i(TAG, "Reload Feed Data");
    }

    private void getMovieFeedItems() {
        Map<String,Object> params = new HashMap<>();
        params.put("extended","full,images");
        Log.i(TAG, "GET FEED: params = " + params.toString());

        downloadFeed(params);
    }

    private void refrershMovieFeedItems() {
        Map<String,Object> params = new HashMap<>();
        params.put("extended","full,images");
        Log.i(TAG,"REFRESH:" + params.toString());


        downloadFeed(params);
    }

    private void loadMoreMovieFeedItems() {
        Map<String,Object> params = new HashMap<>();
        params.put("extended","full,images");
        params.put("page",page);
        params.put("limit", 10);
        Log.i(TAG,"LOAD MORE: params = " + params.toString());

        downloadFeed(params);
    }

    private void downloadFeed(Map params) {
        traktClient.getApiInterface().getPopularMovies(params, new Callback<ArrayList<Movie>>() {
            @Override
            public void success(ArrayList<Movie> popularResponses, Response response) {
                popularMovies.addAll(popularResponses);
                Log.i(TAG, "SUCCESS");
                loadFeed();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "FAILURE: " + error.getResponse().getStatus());
                Log.i(TAG, "FAILURE: " + error.getLocalizedMessage());
            }
        });
    }
}
