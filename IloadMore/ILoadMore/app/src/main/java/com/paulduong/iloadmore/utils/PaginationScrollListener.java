package com.paulduong.iloadmore.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by PaulDuong on 20/09/2017.
 */

public abstract  class PaginationScrollListener extends RecyclerView.OnScrollListener {
    // 1
    LinearLayoutManager linearLayoutManager;

    public PaginationScrollListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 2
        int visibleItemAcount= linearLayoutManager.getChildCount();
        int totalItemAcount = linearLayoutManager.getItemCount();
        int firrVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if( !isLoading() && !isLastPage()) {
            //4
            if (visibleItemAcount + firrVisibleItemPosition >= totalItemAcount &&
                    firrVisibleItemPosition >= 0
                    && totalItemAcount >= getTotalPageCount() ){

                  loadMoreItems();
            }
        }

    }
    // 3
    public  abstract  boolean isLoading();
    public abstract  boolean isLastPage();

    //5
    public  abstract  int getTotalPageCount();
    public  abstract  void loadMoreItems();

}
