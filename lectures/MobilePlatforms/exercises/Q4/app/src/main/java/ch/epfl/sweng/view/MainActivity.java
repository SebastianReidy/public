package ch.epfl.sweng.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.R;
import ch.epfl.sweng.domain.Story;
import ch.epfl.sweng.presentation.StoriesViewModel;

/** The main entry point of the application. */
public final class MainActivity extends AppCompatActivity {

  /** {@inheritDoc} */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.news_list);
    var adapter = new StoriesAdapter();
    recyclerView.setAdapter(adapter);

    var viewModel = new ViewModelProvider(this).get(StoriesViewModel.class);  // view model provider is a class of andriod

    viewModel.getTopStories().observe(this, adapter::submitList);  // needs a this else its ambiguous
  }

  /** Generates a list of stories of size {@code count}. */
  private static List<Story> generateStories(final int count) {
    var result = new ArrayList<Story>();
    for (int i = 0; i < count; i++) {
      result.add(new Story.Builder().id(i).title("title " + i).url("url " + i).build());
    }
    return result;
  }
}
