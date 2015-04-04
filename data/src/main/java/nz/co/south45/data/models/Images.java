
package nz.co.south45.data.models;

import java.util.HashMap;
import java.util.Map;

public class Images {

    private Fanart fanart;
    private Poster poster;
    private Logo logo;
    private Clearart clearart;
    private Banner banner;
    private Thumb thumb;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The fanart
     */
    public Fanart getFanart() {
        return fanart;
    }

    /**
     * 
     * @param fanart
     *     The fanart
     */
    public void setFanart(Fanart fanart) {
        this.fanart = fanart;
    }

    /**
     * 
     * @return
     *     The poster
     */
    public Poster getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The logo
     */
    public Logo getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo
     *     The logo
     */
    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The clearart
     */
    public Clearart getClearart() {
        return clearart;
    }

    /**
     * 
     * @param clearart
     *     The clearart
     */
    public void setClearart(Clearart clearart) {
        this.clearart = clearart;
    }

    /**
     * 
     * @return
     *     The banner
     */
    public Banner getBanner() {
        return banner;
    }

    /**
     * 
     * @param banner
     *     The banner
     */
    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public Thumb getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
