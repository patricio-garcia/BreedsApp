package cl.serlitoral.BreedsApp.ui.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cl.serlitoral.BreedsApp.data.model.Favorite;
import cl.serlitoral.breedsapp.databinding.ItemListFavoriteBinding;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoritesAdapterVH>{
    private List<Favorite> favList;
    private ItemListFavoriteBinding binding;
    public FavoriteAdapter(List<Favorite> favoritesList) {
        this.favList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();

        return new FavoritesAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapterVH holder, int position) {
            Favorite favorite = favList.get(position);
            holder.bind(favorite);
    }

    public void updateFavorites (List<Favorite> favList){
        this.favList.clear();
        this.favList.addAll(favList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public class FavoritesAdapterVH extends RecyclerView.ViewHolder{
        private ImageView imgFavorite;
        private TextView breedFavorite;
        private TextView timeStamp;
        private Context context;

        public FavoritesAdapterVH(@NonNull View itemView) {
            super(itemView);
            breedFavorite = binding.tvBreedFav;
            timeStamp = binding.tvTimeStamp;
            imgFavorite = binding.imgBreed;
            context = itemView.getContext();
        }

        public void bind(Favorite favorite) {
            breedFavorite.setText(favorite.getBreed().toUpperCase());
            timeStamp.setText(favorite.getTimeStamp());
            Glide.with(context).
                    load(favorite.getUrlImage()).
                    override(500,500).
                    into(imgFavorite);
        }
    }
}
