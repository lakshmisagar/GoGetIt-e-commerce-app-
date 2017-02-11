package cart;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import DataModel.ProductModel;

/**
 * Created by Lakshmisagar on 2/10/2017.
 */

public class CartApplication extends Application {
    String TAG = CartApplication.class.getName();
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }
    public static ArrayList<ProductModel> myproducts = new ArrayList<ProductModel>();
    private ProductCart myCart = new ProductCart();

    public ProductModel getProducts(int index) {
        return myproducts.get(index);
    }

    public void setProducts(ProductModel products) {

        Log.d(TAG, "add product");
        myproducts.add(products);

    }

    public void removeProducts(ProductModel products) {
        Log.d(TAG, "remove product " );
        myproducts.remove(products);
    }

    public ProductCart getCart() {
        return myCart;
    }

    public int getProductArraylistsize() {
        return myproducts.size();
    }
}
