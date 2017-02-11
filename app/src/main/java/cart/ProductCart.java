package cart;

import java.util.ArrayList;

import DataModel.ProductModel;

/**
 * Created by Lakshmisagar on 2/10/2017.
 */

public class ProductCart {
    private ArrayList<ProductModel> cartItems = new ArrayList<ProductModel>();
    public ProductModel getProducts(int position){
        return cartItems.get(position);
    }
    public void setProducts(ProductModel products){
        cartItems.add(products);
    }

    public int getCartsize(){
        return cartItems.size();
    }

    public boolean CheckProductInCart(ProductModel productModel){
        return cartItems.contains(productModel);
    }
}