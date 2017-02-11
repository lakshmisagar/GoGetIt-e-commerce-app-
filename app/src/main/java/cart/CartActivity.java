package cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import DataModel.ProductModel;
import ilovezappos.android.com.ilovezappos.R;


public class CartActivity extends AppCompatActivity {
    String TAG = CartActivity.class.getSimpleName();
    private List<ProductModel> cartList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter mAdapter;

    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CartAdapter(cartList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getProductData();
    }

    private void getProductData() {
        final CartApplication cartApplication = (CartApplication) getApplicationContext();
        int productsize = cartApplication.getProductArraylistsize();

        Log.d(TAG, "productsize" + productsize);
        for (int j = 0; j < productsize; j++){
            //Log.d(TAG, "Product cart" + cartApplication.getProducts(j).getProductDesc());
            cartList.add(cartApplication.getProducts(j));
        }

        mAdapter.notifyDataSetChanged();
    }
}