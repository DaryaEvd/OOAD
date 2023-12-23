package ooad.timewise.cheerup;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ooad.timewise.R;

public class CatPictureActivity extends AppCompatActivity {
    private static final String API_KEY = "live_6yLnFUjOhdHHOrKTtdTrvZrlBJymtaL6qYh3xkYE2xDRmLrEgNKY323vteABVEKR";
    private static final String QUERY = "https://api.thecatapi.com/v1/images/search?api_key=" + API_KEY;
    private ImageView catImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_picture_page);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this::clickOnBackBtn);

        catImageView = findViewById(R.id.catImageView);

        Button oneMoreButton = findViewById(R.id.oneMoreButton);
        oneMoreButton.setOnClickListener(this::clickOnOneMoreBtn);

        try {
            showPicture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void showPicture() throws IOException {
        //TODO: обработать null
        String urlPic = getUrlPic();

        Glide.with(this).load(urlPic).into(catImageView);
    }

    private void clickOnOneMoreBtn(View view)  {
        try {
          showPicture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUrlPic() throws IOException {

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
                if (responseBody == null){
                    //TODO обработать
                    return null;
                }
                return parseImageUrl(responseBody.string());
            }

        }
        return null;
    }

    private String parseImageUrl(String jsonResponse) {
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                return jsonObject.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clickOnBackBtn(View view) {
        switchToActivity(CheerUpActivity.class, this);
    }

}
