package cl.moriahdp.tarbaychile.models.product;

import java.io.Serializable;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class Product implements Serializable {

    private String title;
    private int price;
    private String urlMainImage;

    public Product(String title, int price, String urlMainImage) {
        this.title = title;
        this.price = price;
        this.urlMainImage = urlMainImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrlMainImage() {
        return urlMainImage;
    }

    public void setUrlMainImage(String urlMainImage) {
        this.urlMainImage = urlMainImage;
    }
}
