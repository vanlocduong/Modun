package com.paulduong.iloadmore;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.paulduong.iloadmore.utils.PaginationScrollListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 1
    private static final String TAG = "MainActivity";
    LinearLayoutManager linearLayoutManager;
    PaginationAdapter paginationAdapter;

    RecyclerView rv;
    ProgressBar progressBar;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //2
        rv = (RecyclerView )findViewById(R.id.main_recycler);
        progressBar =(ProgressBar) findViewById(R.id.main_progress);

        paginationAdapter = new PaginationAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(paginationAdapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                //
                isLoading = true;
                currentPage += 1;
                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);


            }
            @Override
            public boolean isLoading() {
                return isLoading;
            }


            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

        });

        // mocking network delay for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        }, 1000);



    }
    public  void loadNextPage(){
        Log.d(TAG, "loadNextPage: " + currentPage);
        List<Movie> movies = Movie.createMovies(paginationAdapter.getItemCount());

        paginationAdapter.removeLoadingFooter();
        isLoading = false;

        paginationAdapter.addAll(movies);

        if (currentPage != TOTAL_PAGES) paginationAdapter.addLoadingFooter();
        else isLastPage = true;

    }
    public  void loadFirstPage(){
        Log.d(TAG, "loadFirstPage: ");
        List<Movie> movies = Movie.createMovies(paginationAdapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        paginationAdapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
        else isLastPage = true;
    }


}
