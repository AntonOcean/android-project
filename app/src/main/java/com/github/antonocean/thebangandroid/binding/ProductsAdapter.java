package com.github.antonocean.thebangandroid.binding;


import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.antonocean.thebangandroid.BR;
import com.github.antonocean.thebangandroid.activity.MainActivity3_detail;
import com.github.antonocean.thebangandroid.model.Product;

import java.util.List;

//        import android.widget.ImageView;
//        import android.widget.LinearLayout;
//        import android.widget.TextView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.BindingHolder> {

    static private List<Product> productList;
    private int rowLayout;
    private Context context;


    public static class BindingHolder extends RecyclerView.ViewHolder {


        //data-binding
        private ViewDataBinding binding;



        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);


            //set onclick function on each product
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product item = productList.get(getAdapterPosition());

                    Log.d("Test","num="+ item.getBrandName());
                    //send query by intern to Activity2
                    Intent intent = new Intent(v.getContext(), MainActivity3_detail.class);

                    intent.putExtra("product", item);
                    v.getContext().startActivity(intent);


                }
            });


        }

        public ViewDataBinding getBinding(){
            return binding;
        }




    }

    public ProductsAdapter(List<Product> productList, int rowLayout, Context context) {
        this.productList = productList;
        this.rowLayout = rowLayout;
        this.context = context;
    }




    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent,
                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new BindingHolder(view);
    }


    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {

        Product p = productList.get(position);
        holder.getBinding().setVariable(BR.product, p);
        holder.getBinding().executePendingBindings();




    }




    @Override
    public int getItemCount() {
        return productList.size();
    }
}