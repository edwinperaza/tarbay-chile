package cl.moriahdp.tarbaychile.models.product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class Product implements Serializable {

    private String title;
    private int price;
    private String urlMainImage;

    public Product() {
    }

    public Product(String title, int price, String urlMainImage) {
        this.title = title;
        this.price = price;
        this.urlMainImage = urlMainImage;
    }

    public static Product fromJsonObject(JSONObject jsonObjectProduct){
        Product product = new Product();

        try {

            product.setTitle(jsonObjectProduct.getString("title"));
            product.setPrice(jsonObjectProduct.getInt("price"));
            product.setUrlMainImage(jsonObjectProduct.getString("url_image"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  product;

    }

    public static List<Product> fromJsonArray(JSONArray jsonArrayProducts){
        List<Product> productList = new ArrayList<>();

        for (int i=0; i < jsonArrayProducts.length(); i++){
            try {
                JSONObject jsonObject = jsonArrayProducts.getJSONObject(i);
                Product product = Product.fromJsonObject(jsonObject);

                if (product != null){
                    productList.add(product);
                }

            }catch (JSONException e){
                e.printStackTrace();
                continue;
            }
        }

        return productList;
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
