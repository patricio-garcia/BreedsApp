package cl.serlitoral.BreedsApp.presenter;

import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.usecase.BreedPresenter;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BreedPresenterTest {

    @Mock
    private iBreedPresenterView presenterView;
    @Mock
    private BreedRepository repository;

    private List<String> breeds;
    private BreedPresenter presenter;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        presenter = new BreedPresenter(presenterView, repository);
    }

    @Test
    public void showBreed_emptyList() {
        //Given
        breeds=new ArrayList<>();

        //When
        presenter.showBreed(breeds);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showBreed(breeds);


    }
    @Test
    public void showBreed_nullList() {
        //Given
        breeds = null;

        //When
        presenter.showBreed(breeds);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showBreed(breeds);


    }
    @Test
    public void showBreed_listWithData() {
        //Given
        breeds = new ArrayList<>();
        breeds.add("bullterrier");
        breeds.add("husky");

        //When
        presenter.showBreed(breeds);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showBreed(breeds);
    }
}