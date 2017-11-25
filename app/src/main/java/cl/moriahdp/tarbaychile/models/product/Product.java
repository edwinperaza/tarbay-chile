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
    private String price;
    private String minPrice;
    private String sellPrice;
    private String urlMainImage;
    private String brand;
    private String code;
    private String seller;
    private String specifications;
    private String category;


    public Product() {
    }

    public Product(String title, String price, String urlMainImage) {
        this.title = title;
        this.price = price;
        this.urlMainImage = urlMainImage;
    }

    public static Product fromJsonObject(JSONObject jsonObjectProduct){
        Product product = new Product();

        try {

            product.setTitle(jsonObjectProduct.getString("nombre"));
            product.setPrice(jsonObjectProduct.getString("precio_venta"));
            product.setMinPrice(jsonObjectProduct.getString("precio_minimo"));
            product.setUrlMainImage(jsonObjectProduct.getString("foto"));
            product.setBrand(jsonObjectProduct.getString("marca"));
            product.setCode(jsonObjectProduct.getString("codigo"));
            product.setSeller(jsonObjectProduct.getString("vendedor"));
            product.setSpecifications(jsonObjectProduct.getString("especificaciones"));
            product.setCategory(jsonObjectProduct.getString("categoria"));

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrlMainImage() {
        return urlMainImage;
    }

    public void setUrlMainImage(String urlMainImage) {
        this.urlMainImage = urlMainImage;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }
}
