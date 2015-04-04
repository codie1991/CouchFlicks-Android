
package nz.co.south45.data.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie {

    private String title;
    private Integer year;
    private Ids ids;
    private String tagline;
    private String overview;
    private String released;
    private Integer runtime;
    private String trailer;
    private Object homepage;
    private Double rating;
    private Integer votes;
    private String updatedAt;
    private String language;
    private List<String> availableTranslations = new ArrayList<String>();
    private List<String> genres = new ArrayList<String>();
    private String certification;
    private Images images;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The ids
     */
    public Ids getIds() {
        return ids;
    }

    /**
     * 
     * @param ids
     *     The ids
     */
    public void setIds(Ids ids) {
        this.ids = ids;
    }

    /**
     * 
     * @return
     *     The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * 
     * @param tagline
     *     The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The released
     */
    public String getReleased() {
        return released;
    }

    /**
     * 
     * @param released
     *     The released
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The trailer
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * 
     * @param trailer
     *     The trailer
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * 
     * @return
     *     The homepage
     */
    public Object getHomepage() {
        return homepage;
    }

    /**
     * 
     * @param homepage
     *     The homepage
     */
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public Integer getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The availableTranslations
     */
    public List<String> getAvailableTranslations() {
        return availableTranslations;
    }

    /**
     * 
     * @param availableTranslations
     *     The available_translations
     */
    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The certification
     */
    public String getCertification() {
        return certification;
    }

    /**
     * 
     * @param certification
     *     The certification
     */
    public void setCertification(String certification) {
        this.certification = certification;
    }

    /**
     * 
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
