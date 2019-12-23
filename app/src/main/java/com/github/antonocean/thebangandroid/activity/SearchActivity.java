package com.github.antonocean.thebangandroid.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.Retrofit.ApiClient;
import com.github.antonocean.thebangandroid.Retrofit.ApiInterface;
import com.github.antonocean.thebangandroid.binding.ProductsAdapter;
import com.github.antonocean.thebangandroid.model.Product;
import com.github.antonocean.thebangandroid.model.ProductsResponse;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "a73121520492f88dc3d33daf2103d7574f1a3166";
    @Bind(R.id.tapBarMenu)
    TapBarMenu tapBarMenu;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();





        //get search query from user input in MainActivity
        final String search_term = intent.getStringExtra("search");

        Log.i(TAG, "onCreate: "+search_term);

        //change action bar title to search term
        setTitle(search_term);


        //Display list of products
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//TODO
//        1. firebase(realtime db - nosql), ключ - продукт
//        как в телеге с ссылкой, парсим ссылку, достаем id пользователя
//        ссылка поделиться корзиной, через ссылку и открытие в приложении корзины друга
//        2. авторизация, со свои бекендом
//        Либо 1, Либо 2

//TODO
//        апи интерфейс создается выше
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

//TODO
//        Вынести как отдельный модуль(класс) работы с сетью
        Call<ProductsResponse> call = apiService.getSearch(search_term,API_KEY);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                List<Product> movies = response.body().getResults();
                if (movies.size()==0){
                    Toast.makeText(getApplicationContext(),"Sorry, no result found!", Toast.LENGTH_SHORT).show();
                }
//TODO
//                адаптер создается во вне
                recyclerView.setAdapter(new ProductsAdapter(movies, R.layout.list_item_search));

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());


            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.tapBarMenu) public void onMenuButtonClick() {
        tapBarMenu.toggle();
        Log.i(TAG, "onMenuButtonClick: ");
    }

    //3 button on clicks
    public void cart(View view){
        //send query by intern to Activity2
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    public void aboutMe(View view){
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);

    }

    public void search(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}