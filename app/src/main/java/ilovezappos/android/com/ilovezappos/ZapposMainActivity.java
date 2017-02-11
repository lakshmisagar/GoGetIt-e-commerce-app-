package ilovezappos.android.com.ilovezappos;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import DataBinding.Product;
import DataModel.ProductModel;
import DataModel.ProductResponse;
import RetroImpl.RetroApiInterface;
import RetroImpl.RetroClientAPI;
import cart.CartActivity;
import cart.CartApplication;
import ilovezappos.android.com.ilovezappos.databinding.ActivityZapposMainBinding;
import ilovezappos.android.com.ilovezappos.databinding.ContentScrollingBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static GlobalConstants.Constants.API_KEY;
import static GlobalConstants.Constants.API_TERM;

public class ZapposMainActivity extends AppCompatActivity {

    private static final String TAG = ZapposMainActivity.class.getSimpleName();

    List<ProductModel> items;
    CollapsingToolbarLayout productBrand;
    ContentScrollingBinding scrollingBinding;
    TextView productDesc;
    TextView price;
    public ImageView thumbnail;
    ActivityZapposMainBinding binding;
    Product product;
    Product cartProduct;
    SearchView searchView;
    MenuItem searchItem;
    CartApplication cartApplication;

    private ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

                binding = DataBindingUtil.setContentView(this,R.layout.activity_zappos_main);
        setSupportActionBar(binding.toolbar);

        scrollingBinding = DataBindingUtil.findBinding(findViewById(R.id.content_scroll));

        binding.setFabHandler(new FabHandler());

        cartApplication = (CartApplication)getApplicationContext();

        productBrand = binding.toolbarLayout;
        productBrand.setContentScrimColor(ContextCompat.getColor(getApplication(),R.color.orange));
        productBrand.setExpandedTitleColor(ContextCompat.getColor(getApplication(),R.color.orange));
        getSupportActionBar().setHomeButtonEnabled(false);
        thumbnail = binding.thumbnail;
        thumbnail.setAlpha((float) 0.8);
        productDesc = scrollingBinding.productDescCard;
        price = scrollingBinding.priceCard;

        handleIntent(getIntent());

        Intent intent = getIntent();
            API_TERM = intent.getStringExtra("SeachItem");
        Log.d(TAG,"APITTERM: "+API_TERM);

        if(API_TERM != null){
            searchFunction(API_TERM);
        }else{
            searchFunction("");
        }

    }


    public class FabHandler {
        public void onBaseFabClick(View view) {

            if(Product.getFavorite()){
                Log.d(TAG, "favorite add" );
                cartApplication.removeProducts(items.get(0));
                Snackbar.make(view, "Item Removed from cart", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                binding.fab.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.cartclear));
                scrollingBinding.favoriteCard.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.nolike));
                Product.setFavorite(false);

            }else{
                Log.d(TAG, "favorite remove" );
                cartApplication.setProducts(items.get(0));
                Snackbar.make(view, "Item added to cart", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                binding.fab.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.cartfull));
                scrollingBinding.favoriteCard.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.llike));
                Product.setFavorite(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchItem = menu.findItem(R.id.search);
        searchView =(SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntent(createShareIntent());

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String url = "http://www.iloveZappos.com/launch";
        shareIntent.putExtra(Intent.EXTRA_TEXT,url);
        Log.d(TAG,"API_TERM: "+API_TERM);
        shareIntent.putExtra("SeachItem",API_TERM);
        return shareIntent;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG,"onNewIntent");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d(TAG,"handleIntent");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.d(TAG,"ACTION_SEARCH");
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "query is " + query);
            API_TERM = query;
            searchFunction(API_TERM);
        }
    }
    private void searchFunction(String apiTerm){
        RetroApiInterface apiService =   RetroClientAPI.getClient().create(RetroApiInterface.class);
        Log.d(TAG, "apiService " + apiService);
        Call<ProductResponse> call = apiService.getSearch(apiTerm, API_KEY);
        Log.d(TAG, "call " + call);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse>call, Response<ProductResponse> response) {
                searchView.clearFocus();

                MenuItemCompat.collapseActionView(searchItem);
                Log.d(TAG, "onResponse" );
                items = response.body().get_results();
                Log.d(TAG, "Number of items received: " + items.size());
                if(items.size()==0){
                    searchFunction("");
                }else {
                    ProductModel item = items.get(0);
                    Picasso.with(getApplicationContext()).load(item.get_thumbnailImageUrl()).into(thumbnail);
                    String name = item.get_brandName().substring(0,1).toUpperCase() + item.get_brandName().substring(1);
                    String discount = item.get_percentOff();
                    if(discount.equals("0%")){
                        discount = "";
                    }else{
                        discount = discount+" "+"OFF";
                    }
                    binding.fab.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.cartclear));
                    scrollingBinding.favoriteCard.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.nolike));
                    productBrand.setTitle(name);
                    product = new Product(name,item.get_thumbnailImageUrl(),item.get_productName(),item.get_price(),false,item.get_productUrl(),discount);
                    binding.setProduct(product);
                    scrollingBinding.setProduct(product);

                    Log.d(TAG, "Number of items received: " + items.size());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse>call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void onBuy(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getProductLink()));
        startActivity(browserIntent);
    }

    public void launchCart(View v) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}