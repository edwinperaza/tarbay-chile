package cl.moriahdp.tarbaychile.models.category;

import java.io.Serializable;

/**
 * Created by edwinperaza on 8/27/16.
 */
public class Category implements Serializable {

    private String title;
    private String urlImage;

    public Category(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }

    public Category() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
