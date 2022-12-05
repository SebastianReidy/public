package ch.epfl.sweng.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import ch.epfl.sweng.R;
import ch.epfl.sweng.presentation.StoriesViewModel;
import ch.epfl.sweng.view.stories.StoriesAdapter;
import ch.epfl.sweng.view.stories.StoriesFragment;

/** The main entry point of the application. */
public final class MainActivity extends AppCompatActivity {

  /** {@inheritDoc} */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    var viewModel = new ViewModelProvider(this).get(StoriesViewModel.class);
    var adapter = new StoriesAdapter();
    @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.news_list);

    recyclerView.setAdapter(adapter);

    viewModel.getTopStories().observe(this, adapter::submitList);

    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.container, StoriesFragment.getInstance())
          .commit();
    }
  }
}
