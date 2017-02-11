package cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import DataModel.ProductModel;
import ilovezappos.android.com.ilovezappos.R;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemViewHolder> {
    private List<ProductModel> items;
    private Context context;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemsLayout;
        TextView itemBrand;
        TextView productName;
        TextView price;
        ImageView thumbnail;

        public ItemViewHolder(View v) {
            super(v);
            itemsLayout = (LinearLayout) v.findViewById(R.id.items_layout);
            itemBrand = (TextView) v.findViewById(R.id.brand_name);
            productName = (TextView) v.findViewById(R.id.product_name);
            price = (TextView) v.findViewById(R.id.price);
            thumbnail = (ImageView) v.findViewById(R.id.listimage);
        }
    }

    public CartAdapter(List<ProductModel> items, Context cntx) {
        this.items = items;
        context = cntx;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.itemBrand.setText(items.get(position).get_brandName());
        holder.productName.setText(items.get(position).get_productName());
        holder.price.setText(items.get(position).get_price());
        Picasso.with(context).load(items.get(position).get_thumbnailImageUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
