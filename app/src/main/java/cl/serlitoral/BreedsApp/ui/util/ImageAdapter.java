package cl.serlitoral.BreedsApp.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.serlitoral.BreedsApp.ui.view.iOnItemLongClickListener;
import cl.serlitoral.breedsapp.databinding.ItemListImageBinding;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PicturesAdapterVH> {
    private List<String> imgList;
    private iOnItemLongClickListener listener;
    private ItemListImageBinding binding;

    public List<String> getImgList() {
        return imgList;
    }


    public ImageAdapter(List<String> imgList, iOnItemLongClickListener listener) {
        this.imgList = imgList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageAdapter.PicturesAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemListImageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view= binding.getRoot();
        return new PicturesAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.PicturesAdapterVH holder, int position) {
        String picture= imgList.get(position);
        holder.bind(picture);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public void updatePictures(List<String> image){
        imgList.clear();
        imgList.addAll(image);
        notifyDataSetChanged();
    }

    public class PicturesAdapterVH extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private ImageView imgPictures;
        private Context context;

        public PicturesAdapterVH(@NonNull View itemView) {
            super(itemView);
            imgPictures=binding.imgBreed;
            context=itemView.getContext();
            itemView.setOnLongClickListener(this);
        }

        public void bind(String picture) {
            Glide.with(context).
                    load(picture).
                    override(500,500).
                    into(imgPictures);
        }

        @Override
        public boolean onLongClick(View v) {
            int position=getAdapterPosition();
            listener.onLongClick(position);
            return true;
        }
    }
}

