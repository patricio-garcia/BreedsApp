package cl.serlitoral.BreedsApp.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.usecase.BreedPresenter;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;
import cl.serlitoral.BreedsApp.ui.util.BreedAdapter;
import cl.serlitoral.breedsapp.R;
import cl.serlitoral.breedsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements iBreedPresenterView, iOnItemClickListener {

    private ActivityMainBinding binding;
    private BreedPresenter presenter;
    private BreedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adapter = new BreedAdapter(new ArrayList<>(),this);
        presenter = new BreedPresenter(this,new BreedRepository());
        RecyclerView recyclerview = binding.rvBreed;
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        recyclerview.setAdapter(adapter);
        Button viewFavorites = binding.btnFav;
        viewFavorites.setOnClickListener(v -> getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frameRecycler, FavoriteFragment.newInstance("", "")).
                addToBackStack("Detail").
                commit());
    }

    @Override
    public void showBreed(List<String> breeds) {
        adapter.updateBreeds(breeds);
    }


    @Override
    public void onClick(int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameRecycler, ImageFragment.newInstance("", adapter.getBreedsLis().get(position)))
                .addToBackStack("Detail")
                .commit();
    }
}