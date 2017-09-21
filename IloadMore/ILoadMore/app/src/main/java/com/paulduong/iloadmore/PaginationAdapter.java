package com.paulduong.iloadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paulduong.iloadmore.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PaulDuong on 20/09/2017.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 1
    private Context context;
    private List<Movie > movies;
    //
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;


    // 2
    public PaginationAdapter(Context context){
        this.context = context;
        movies = new ArrayList<>();
    }
    //3
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // 4
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            // 8
            case ITEM:
                viewHolder = getViewHolder(parent,layoutInflater);
                break;
            case LOADING:
                View v2 = layoutInflater.inflate(R.layout.item_process,parent,false);
                viewHolder= new LoadingVH(v2);
                break;
        }


        return viewHolder;
    }

    // 6
    private  RecyclerView.ViewHolder getViewHolder(ViewGroup parent,LayoutInflater layoutInflater){
        RecyclerView.ViewHolder viewHolder;
        View v1 = layoutInflater.inflate(R.layout.item_list,parent);

        viewHolder = new MovieVH(v1);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                MovieVH movieVH = (MovieVH) holder;
                movieVH.textView.setText(movie.getTitle());
                break;
            case LOADING:
                break;


        }

    }


    // 9

    @Override
    public int getItemCount() {
        return movies== null ? 0:movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movies.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }
    // 10
    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Movie mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<Movie> mcList) {
        for (Movie mc : mcList) {
            add(mc);
        }
    }

    public void remove(Movie city) {
        int position = movies.indexOf(city);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movies.size() - 1;
        Movie item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    // 5
    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
    // 7
    protected class MovieVH extends RecyclerView.ViewHolder {
        private TextView textView;

        public MovieVH(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

}
