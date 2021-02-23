package GUI.Overlays.ItemCreation;

import java.io.Serializable;

public class StockXProduct implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8366403397493841561L;
    String title;
    String url;
    long retail;
    String brand;
    String sku;
    String color;
    String releaseDate;
    String LargeImageUrl;
    String MediumImageUrl;
    String SmallImageUrl;
    String Search;
    String size;
    String groupName;
    /**
     * 
     * @param title Title of the item/product
     * @param retail The retail price of the item
     * @param brand The brand of the item
     * @param urlPart The title part of the item used going to the url for buying/selling
     */
    public StockXProduct(String title, long retail, String brand, String urlPart, String color, String SKU, String releaseDate) {
        this.title = title;
        this.url = urlPart;
        this.retail = retail;
        this.brand = brand;
        if(SKU != null && !SKU.trim().equals("")) {
            this.sku = SKU;
        } else {
            this.sku = "None";
        }
        
        this.color = color;
        try {
            String year = releaseDate.split("-")[0];
            String month = releaseDate.split("-")[1];
            String day = releaseDate.split("-")[2];
            this.releaseDate = month + "/" + day + "/" + year;
        } catch(Exception e) {
            this.releaseDate = "11/19/2000";
        }
    }


    /**
     * 
     * @param title Title of the item/product
     * @param retail The retail price of the item
     * @param brand The brand of the item
     * @param urlPart The title part of the item used going to the url for buying/selling
     */
    public StockXProduct(StockXProduct product, String size) {
        this.size = size;
        this.title = product.getTitle();
        this.url = product.getUrl();
        this.retail = product.getRetail();
        this.brand = product.getBrand();
        if(product.getSku() != null && !product.getSku().trim().equals("")) {
            this.sku = product.getSku();
        } else {
            this.sku = "None";
        }
        
        this.color = product.getColor();
        try {
            String year = product.getReleaseDate().split("-")[0];
            String month = product.getReleaseDate().split("-")[1];
            String day = product.getReleaseDate().split("-")[2];
            this.releaseDate = month + "/" + day + "/" + year;
        } catch(Exception e) {
            this.releaseDate = "11/19/2000";
        }
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getSize() {
        return this.size;
    }
    public void setSearched(String search) {
        this.Search = search;
    }
    public String getSearched() {
        return Search;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public long getRetail() {
        return retail;
    }
    public String getBrand() {
        return brand;
    }
    public void setSmallImageUrl(String url) {
        this.SmallImageUrl = url;
    }
    public String getSmallImageUrl() {
        return SmallImageUrl;
    }
    public String getColor() {
        return color;
    }
    public String getSku() {
        return sku;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setReleaseDate(String date) {
        this.releaseDate = date;
    }
    public void setRetail(Long retail) {
        this.retail = retail;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String name) {
        this.groupName = name;
    }
}
