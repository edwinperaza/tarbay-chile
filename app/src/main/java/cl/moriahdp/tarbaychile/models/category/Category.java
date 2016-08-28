package cl.moriahdp.tarbaychile.models.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.tarbaychile.models.subcategory.SubCategory;

public class Category implements Serializable {

    private long id;
    private String name;
    private String descr;
    private String urlImage;
    private List<SubCategory> itemList = new ArrayList<>();

    public Category() {
    }

    public Category(long id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<SubCategory> getItemList() {
        return itemList;
    }

    public void setItemList(List<SubCategory> itemList) {
        this.itemList = itemList;
    }
}