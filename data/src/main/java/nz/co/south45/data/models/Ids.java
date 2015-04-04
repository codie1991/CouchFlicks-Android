
package nz.co.south45.data.models;

import java.util.HashMap;
import java.util.Map;


public class Ids {

    private Integer trakt;
    private String slug;
    private String imdb;
    private Integer tmdb;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The trakt
     */
    public Integer getTrakt() {
        return trakt;
    }

    /**
     * 
     * @param trakt
     *     The trakt
     */
    public void setTrakt(Integer trakt) {
        this.trakt = trakt;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    /**
     * 
     * @return
     *     The tmdb
     */
    public Integer getTmdb() {
        return tmdb;
    }

    /**
     * 
     * @param tmdb
     *     The tmdb
     */
    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
