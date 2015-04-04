
package nz.co.south45.data.models;

import com.google.gson.annotations.Expose;

public class TrendingResponse {

    private Integer watchers;
    private Movie movie;

    /**
     * 
     * @return
     *     The watchers
     */
    public Integer getWatchers() {
        return watchers;
    }

    /**
     * 
     * @param watchers
     *     The watchers
     */
    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    /**
     * 
     * @return
     *     The movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * 
     * @param movie
     *     The movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
