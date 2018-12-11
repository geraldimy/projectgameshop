package com.example.geraldmuhammad.g_shop;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.geraldmuhammad.g_shop.Adapter.OrderAdapter;
import com.example.geraldmuhammad.g_shop.Model.Order;
import com.example.geraldmuhammad.g_shop.Retrofit.IGameShop;
import com.example.geraldmuhammad.g_shop.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowOrderActivity extends AppCompatActivity {

    IGameShop mService;
    RecyclerView recycler_orders;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

        if(Common.curentuUser == null)
        {
            Toast.makeText(this,"Please Login First",Toast.LENGTH_SHORT).show();
        }

        mService = Common.getAPI();

        recycler_orders = (RecyclerView)findViewById(R.id.recycler_orders);
        recycler_orders.setLayoutManager(new LinearLayoutManager(this));
        recycler_orders.setHasFixedSize(true);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.order_new)
                {
                    loadOrder("0");
                }
                else if (item.getItemId() == R.id.order_cancel)
                {
                    loadOrder("-1");
                }
                else if (item.getItemId() == R.id.order_processing)
                {
                    loadOrder("1");
                }
                else if (item.getItemId() == R.id.order_shipping)
                {
                    loadOrder("2");
                }
                else if (item.getItemId() == R.id.order_shipped)
                {
                    loadOrder("3");
                }
                return true;
            }
        });

        loadOrder("0");
    }

    private void loadOrder(String statusCode) {
        if(Common.curentuUser !=null) {
            compositeDisposable.add(mService.getOrder(Common.curentuUser.getPhone(), statusCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<List<Order>>() {
                        @Override
                        public void accept(List<Order> orders) throws Exception {
                            displayOrder(orders);
                        }
                    }));
        }
        else
        {
            Toast.makeText(this, "Please Log in Again!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayOrder(List<Order> orders) {
        OrderAdapter adapter = new OrderAdapter(this,orders);
        recycler_orders.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrder("0");
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
