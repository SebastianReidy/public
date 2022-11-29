package ch.epfl.sweng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ch.epfl.sweng.NewsActivity;

import androidx.appcompat.app.AppCompatActivity;

/** The main entry point of the application. */
public final class MainActivity extends AppCompatActivity {

    /** {@inheritDoc} */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent startNewsIntent = new Intent(MainActivity.this, NewsActivity.class);
                MainActivity.this.startActivity(startNewsIntent);
            }
        });
    }
}
