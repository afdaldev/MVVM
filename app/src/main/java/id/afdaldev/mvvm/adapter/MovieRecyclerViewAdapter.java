package id.afdaldev.mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.afdaldev.mvvm.DetailActivity;
import id.afdaldev.mvvm.R;
import id.afdaldev.mvvm.data.model.Movie;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movieList){
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String base = "https://image.tmdb.org/t/p/w185";

        holder.mMovieTitle.setText(movieList.get(position).getTitle());
        Picasso.get()
                .load(base + movieList
                        .get(position)
                        .getPosterPath())
                .into(holder.mImageView);
        Log.d("TAG", movieList.get(position).getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mMovieTitle;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mImageView = itemView.findViewById(R.id.img_movie);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = movieList.get(position);

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        }
    }
}