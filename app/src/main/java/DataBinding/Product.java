package DataBinding;

/**
 * Created by Lakshmisagar on 1/03/2017.
 */

public class Product  {
    public static String brandName;
    public static String thumbnail;
    public static String productDesc;
    public static String productPrice;
    public static Boolean isFavorite = false;
    public static String productLink;
    public static String discount;

    public Product(String brand, String thumnailURL, String description, String price, Boolean isFav, String product_link, String disc){
        this.brandName = brand;
        this.thumbnail = thumnailURL;
        this.productDesc = description;
        this.productPrice = price;
        this.isFavorite = isFav;
        this.productLink = product_link;
        this.discount = disc;
    }

    public static String getBrandName() {
        return brandName;
    }

    public static void setBrandName(String brandName) {
        Product.brandName = brandName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public static Boolean getFavorite() {
        return isFavorite;
    }

    public static void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String disc) {
        this.discount = disc;
    }
}