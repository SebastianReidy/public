package ch.epfl.sweng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        RecyclerView recyclerView = findViewById(R.id.news_list);
        var adapter = new StoriesAdapter();

        recyclerView.setAdapter(adapter);

        // Display a bunch of stories.
        adapter.submitList(generateStories());
    }


    private static List<Story> generateStories() {
        return List.of(
                new Story(0, "title 0", "https://epfl.ch"),
                new Story(1, "title 1", "https://epfl.ch"),
                new Story(2, "title 2", "https://epfl.ch"),
                new Story(3, "title 3", "https://epfl.ch"));
    }
}