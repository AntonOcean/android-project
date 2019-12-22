package com.github.antonocean.thebangandroid.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.Retrofit.ApiClient;
import com.github.antonocean.thebangandroid.Retrofit.ApiInterface;
import com.github.antonocean.thebangandroid.model.Product;
import com.github.antonocean.thebangandroid.model.ProductsResponse;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "a73121520492f88dc3d33daf2103d7574f1a3166";
    @Bind(R.id.tapBarMenu) TapBarMenu tapBarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        //set actionbar zappos logo
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setLogo(R.drawable.zappos_logo_white);

        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        //make whole search field clickable
        final SearchView searchView = (SearchView)findViewById(R.id.search_field);
        assert searchView != null;
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });


        //butterknife binding
        ButterKnife.bind(this);
    }


    private void requestPermission(String permission) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this,
                new String[]{permission}, 1);
    }


    @OnClick(R.id.tapBarMenu) public void onMenuButtonClick() {
        tapBarMenu.toggle();
        Log.i(TAG, "onMenuButtonClick: ");
    }


    //search button
    public void search(View view){
        Log.i(TAG, "search: click");

        SearchView searchView = (SearchView) findViewById(R.id.search_field);
        assert searchView != null;
        String q = searchView.getQuery().toString();

        //send query by intern to Activity2
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("search", q);
        startActivity(intent);

    }

    //lucky button
    public void searchLucky(View view){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        SearchView searchView = (SearchView) findViewById(R.id.search_field);
        assert searchView != null;
        String q= searchView.getQuery().toString();


        Call<ProductsResponse> call = apiService.getSearch(q,API_KEY);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                List<Product> movies = response.body().getResults();

                if (movies.size()==0){
                    Toast.makeText(getApplicationContext(),"Sorry, no result found!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getBaseContext(), DetailActivity.class);

                    intent.putExtra("product", movies.get(0));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getBaseContext().startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());


            }
        });

    }

    //button on click for cart
    public void cart(View view){
        //send query by intern to Activity2
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    public void aboutMe(View view){
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);

    }





}