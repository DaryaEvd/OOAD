package ooad.timewise.cheerup;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import ooad.timewise.R;

public class QuoteActivity extends AppCompatActivity {
    private TextView quotesTextView;
    private TextView authorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_show_page);

        Button backButton = findViewById(R.id.backQuoteButton);
        backButton.setOnClickListener(this::clickOnBackBtn);

        Button oneMoreButton = findViewById(R.id.oneMoreQuoteButton);
        oneMoreButton.setOnClickListener(this::clickOnOneMoreButton);

        quotesTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.quoteAuthorTextView);

        try {
            showQuoteAuthor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clickOnOneMoreButton(View view) {
        try {
            showQuoteAuthor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showQuoteAuthor() throws IOException {
        String[] dataFromUrl = APIDataTaker.getInstance().getUrlQuoteText();

        assert dataFromUrl != null;
        String authorFromQuery = dataFromUrl[0];
        String bodyFromQuery = dataFromUrl[1];

        authorTextView.setText(authorFromQuery);
        quotesTextView.setText(bodyFromQuery);
    }

    private void clickOnBackBtn(View view) {
        switchToActivity(CheerUpActivity.class, this);
    }
}
