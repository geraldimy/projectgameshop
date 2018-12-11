package com.example.geraldmuhammad.g_shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.geraldmuhammad.g_shop.Adapter.GameAdapter;
import com.example.geraldmuhammad.g_shop.Model.Game;
import com.example.geraldmuhammad.g_shop.Retrofit.IGameShop;
import com.example.geraldmuhammad.g_shop.Utils.Common;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    List<String> suggestList = new ArrayList<>();
    List<Game> localDataSource = new ArrayList<>();
    MaterialSearchBar searchBar;

    IGameShop mService;

    RecyclerView recycler_search;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    GameAdapter searchAdapter,adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mService = Common.getAPI();

        recycler_search = (RecyclerView)findViewById(R.id.recycler_search);
        recycler_search.setLayoutManager(new GridLayoutManager(this,2));

        searchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        searchBar.setHint("Enter Your Game");

        loadAllGames();

        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            List<String> suggest = new ArrayList<>();
                for (String search:suggestList)
                {
                if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                    suggest.add(search);
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recycler_search.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                    startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void startSearch(CharSequence text) {
        List<Game> result = new ArrayList<>();
        for(Game game:localDataSource)
            if(game.Name.contains(text))
                result.add(game);
        searchAdapter = new GameAdapter(this,result);
        recycler_search.setAdapter(searchAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void loadAllGames() {
        compositeDisposable.add(mService.getAllGames().observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<Game>>() {
            @Override
            public void accept(List<Game> games) throws Exception {
                displayListGame(games);
                buildSuggestList(games);
            }
        }));

    }

    private void buildSuggestList(List<Game> games) {
        for(Game game:games)
            suggestList.add(game.Name);
            searchBar.setLastSuggestions(suggestList);
    }

    private void displayListGame(List<Game> games) {
        localDataSource = games;
        adapter = new GameAdapter(this,games);
        recycler_search.setAdapter(adapter);
    }
}
