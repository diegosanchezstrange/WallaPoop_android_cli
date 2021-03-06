package com.example.wallapoop2.product;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallapoop2.R;
import com.example.wallapoop2.app.ListFragment;
import com.example.wallapoop2.app.ProfileFragment;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerUserProductAdapter extends RecyclerView.Adapter<RecyclerUserProductAdapter.ProductsHolder>
{
    private List<Product> products;
    private Context ctx;
    private int resID;
    private ProfileFragment fragment;
    private onClickInterface onClickInterface;

    public RecyclerUserProductAdapter(ProfileFragment fragment, List<Product> products, Context ctx, int resID, onClickInterface onClickInterface)
    {
        this.fragment = fragment;
        this.products = products;
        this.ctx = ctx;
        this.resID = resID;
        this.onClickInterface = onClickInterface;
    }


    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ProductsHolder holder = null;
        View view = null;

        try
        {
            view = ((FragmentActivity) ctx).getLayoutInflater().inflate(this.resID, parent, false);
            holder = new ProductsHolder(view);
        }
        catch (Exception ex)
        {
            Log.i("Informacion", ex.getMessage());
        }

        view.setOnLongClickListener(this.productOnLongClick);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, final int position)
    {
        holder.product = this.products.get(position);

        holder.tvName.setText(holder.product.getpName());
        holder.tvPrice.setText(String.valueOf(holder.product.getpPrice()) + " €");

        Picasso.get().load("http://diegosanstr.ddns.net:5001/img/" + holder.product.getpName().replace(" ", "_") + ".jpg").resize(50,50).centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount()
    {
        return products.size();
    }

    class ProductsHolder extends RecyclerView.ViewHolder
    {
        public View v;
        public ImageView img;
        public TextView tvName, tvPrice;
        public Product product;

        public ProductsHolder(@NonNull View itemView)
        {
            super(itemView);

            this.v = itemView;

            this.img = itemView.findViewById(R.id.imageViewProductList);
            this.tvName = itemView.findViewById(R.id.productListName);
            this.tvPrice = itemView.findViewById(R.id.productListPrice);
        }
    }


    public View.OnClickListener productOnClick;
    private View.OnLongClickListener productOnLongClick;

    public void setProductOnClick(View.OnClickListener productOnClick)
    {
        this.productOnClick = productOnClick;
    }

    public void setProductOnLongClick(View.OnLongClickListener productOnLongClick)
    {
        this.productOnLongClick = productOnLongClick;
    }

    public void changeDataItem(int position, Product model)
    {
        products.set(position, model);
    }

}
