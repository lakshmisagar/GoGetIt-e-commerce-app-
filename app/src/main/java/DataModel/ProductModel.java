package DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lakshmisagar on 1/28/2017.
 */

public class ProductModel {

    @SerializedName("brandName")
    private String _brandName;
    @SerializedName("thumbnailImageUrl")
    private String _thumbnailImageUrl;
    @SerializedName("productId")
    private Integer _productId;
    @SerializedName("originalPrice")
    private String _originalPrice;
    @SerializedName("styleId")
    private Integer _styleId;
    @SerializedName("colorId")
    private Integer _colorId;
    @SerializedName("price")
    private String _price;
    @SerializedName("percentOff")
    private String _percentOff;
    @SerializedName("productUrl")
    private String _productUrl;
    @SerializedName("productName")
    private String _productName;

    public ProductModel(String _percentOff, String _brandName, String _thumbnailImageUrl, Integer _productId, String _originalPrice, Integer _styleId, Integer _colorId, String _price, String _productUrl, String _productName) {
        this._percentOff = _percentOff;
        this._brandName = _brandName;
        this._thumbnailImageUrl = _thumbnailImageUrl;
        this._productId = _productId;
        this._originalPrice = _originalPrice;
        this._styleId = _styleId;
        this._colorId = _colorId;
        this._price = _price;
        this._productUrl = _productUrl;
        this._productName = _productName;
    }

    public String get_brandName() {
        return _brandName;
    }

    public void set_brandName(String _brandName) {
        this._brandName = _brandName;
    }

    public String get_thumbnailImageUrl() {
        return _thumbnailImageUrl;
    }

    public void set_thumbnailImageUrl(String _thumbnailImageUrl) {
        this._thumbnailImageUrl = _thumbnailImageUrl;
    }

    public Integer get_productId() {
        return _productId;
    }

    public void set_productId(Integer _productId) {
        this._productId = _productId;
    }

    public String get_originalPrice() {
        return _originalPrice;
    }

    public void set_originalPrice(String _originalPrice) {
        this._originalPrice = _originalPrice;
    }

    public Integer get_styleId() {
        return _styleId;
    }

    public void set_styleId(Integer _styleId) {
        this._styleId = _styleId;
    }

    public Integer get_colorId() {
        return _colorId;
    }

    public void set_colorId(Integer _colorId) {
        this._colorId = _colorId;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public String get_percentOff() {
        return _percentOff;
    }

    public void set_percentOff(String _percentOff) {
        this._percentOff = _percentOff;
    }

    public String get_productUrl() {
        return _productUrl;
    }

    public void set_productUrl(String _productUrl) {
        this._productUrl = _productUrl;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }
}
