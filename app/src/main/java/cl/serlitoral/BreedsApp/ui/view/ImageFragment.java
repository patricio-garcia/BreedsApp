package cl.serlitoral.BreedsApp.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;
import cl.serlitoral.BreedsApp.domain.usecase.ImagePresenter;
import cl.serlitoral.BreedsApp.ui.util.ImageAdapter;
import cl.serlitoral.breedsapp.databinding.FragmentImageBinding;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment implements iBreedPresenterView, iOnItemLongClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView tvBreedTitle;
    private ImagePresenter presenter;
    private ImageAdapter adapter;
    private RecyclerView recycler;
    private FragmentImageBinding binding;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
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
        binding = FragmentImageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        tvBreedTitle = binding.tvImgTitle;
        tvBreedTitle.setText("Imagenes para la raza: " + mParam2);
        adapter = new ImageAdapter(new ArrayList<>(),this);
        presenter = new ImagePresenter(this,new BreedRepository(),mParam2);
        recycler = binding.rvImage;
        recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void showBreed(List<String> breeds) {
            adapter.updatePictures(breeds);
    }

    @Override
    public void onLongClick(int position) {
        Toast.makeText(getContext(),"Se agrega imagen " + adapter.getImgList().get(position) +" a Lsita de Favoritos", Toast.LENGTH_LONG).show();
        presenter.addFavorite(adapter.getImgList().get(position), mParam2);
    }
}