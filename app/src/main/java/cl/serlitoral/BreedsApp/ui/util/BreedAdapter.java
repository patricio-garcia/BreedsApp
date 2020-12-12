package cl.serlitoral.BreedsApp.ui.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.serlitoral.BreedsApp.ui.view.iOnItemClickListener;
import cl.serlitoral.breedsapp.databinding.ItemListBreedBinding;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.BreedAdapterVH> {
    private List<String> breedsLis;
    private iOnItemClickListener listener;
    private ItemListBreedBinding binding;

    public BreedAdapter(List<String> listOfBreeds, iOnItemClickListener pListener) {
        this.breedsLis = listOfBreeds;
        this.listener=pListener;
    }

    public List<String> getBreedsLis() {
        return breedsLis;
    }

    @NonNull
    @Override
    public BreedAdapter.BreedAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemListBreedBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        return new BreedAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedAdapter.BreedAdapterVH holder, int position) {
            String breed = breedsLis.get(position);
            holder.bind(breed);
    }

    @Override
    public int getItemCount() {
        return breedsLis.size();
    }

    public void updateBreeds(List<String> breeds){
            breedsLis.clear();
            breedsLis.addAll(breeds);
            notifyDataSetChanged();
    }

    public class BreedAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvBreed;

        public BreedAdapterVH(@NonNull View itemView) {
            super(itemView);
            tvBreed = binding.imgBreed;;
            itemView.setOnClickListener(this);
        }

        public void bind(String breed){
           tvBreed.setText(breed);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listener.onClick(position);
        }
    }
}
