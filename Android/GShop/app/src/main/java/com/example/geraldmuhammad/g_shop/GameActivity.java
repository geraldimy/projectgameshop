package com.example.geraldmuhammad.g_shop;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geraldmuhammad.g_shop.Adapter.GameAdapter;
import com.example.geraldmuhammad.g_shop.Model.Game;
import com.example.geraldmuhammad.g_shop.Retrofit.IGameShop;
import com.example.geraldmuhammad.g_shop.Utils.Common;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GameActivity extends AppCompatActivity {

    IGameShop mService;
    RecyclerView lst_game;

    TextView txt_banner_name;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mService = Common.getAPI();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_to_refresh);

        lst_game = (RecyclerView)findViewById(R.id.recycler_games);
        lst_game.setLayoutManager(new GridLayoutManager(this,2));
        lst_game.setHasFixedSize(true);

        txt_banner_name = (TextView)findViewById(R.id.txt_category);
        txt_banner_name.setText(Common.currentCategory.Name);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                loadlistGame(Common.currentCategory.ID);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                loadlistGame(Common.currentCategory.ID);
            }
        });
    }

    private void loadlistGame(String categoryid) {
        compositeDisposable.add(mService.getGame(categoryid)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Game>>() {
            @Override
            public void accept(List<Game> games) throws Exception {
                displayGameList(games);
            }
        }));
    }

    private void displayGameList(List<Game> games) {
        GameAdapter adapter = new GameAdapter(this,games);
        lst_game.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
    }
}
