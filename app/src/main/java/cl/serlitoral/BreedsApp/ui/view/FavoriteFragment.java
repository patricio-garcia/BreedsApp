package cl.serlitoral.BreedsApp.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.serlitoral.BreedsApp.data.model.Favorite;
import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.usecase.FavoritePresenter;
import cl.serlitoral.BreedsApp.domain.presenter.iFavoritePresenterView;
import cl.serlitoral.BreedsApp.ui.util.FavoriteAdapter;
import cl.serlitoral.breedsapp.databinding.FragmentImageBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements iFavoritePresenterView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FavoritePresenter presenter;
    private FavoriteAdapter adapter;
    private RecyclerView recycler;
    private FragmentImageBinding binding;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater, container,false);
        View view = binding.getRoot();
        adapter = new FavoriteAdapter(new ArrayList<>());
        presenter = new FavoritePresenter(this, new BreedRepository());
        recycler = binding.rvImage;
        recycler.setLayoutManager(new GridLayoutManager(getContext(),1));
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void showFavorites(List<Favorite> favList) {
        adapter.updateFavorites(favList);
    }
}