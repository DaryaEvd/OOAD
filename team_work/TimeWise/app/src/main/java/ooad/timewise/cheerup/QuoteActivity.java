package ooad.timewise.cheerup;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ooad.timewise.R;

public class QuoteActivity extends AppCompatActivity {
    private static final String QUERY = "https://favqs.com/api/qotd";

    String[] data;
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
        String[] dataFromUrl = getUrlText();

        assert dataFromUrl != null;
        String authorFromQuery = dataFromUrl[0];
        String bodyFromQuery = dataFromUrl[1];

        authorTextView.setText(authorFromQuery);
        quotesTextView.setText(bodyFromQuery);
    }

    private String[] getUrlText() throws IOException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(QUERY)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                okhttp3.ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return null;
                }
                return parseTextUrl(responseBody.string());
            }

        }
        return null;
    }

    private String[] parseTextUrl(String response) {
        String[] res = new String[2];
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject quote = jsonResponse.getJSONObject("quote");

            String author = quote.getString("author");
            String body = quote.getString("body");

            res[0] = author;
            res[1] = body;

            return res;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clickOnBackBtn(View view) {
        switchToActivity(CheerUpActivity.class, this);
    }
}
