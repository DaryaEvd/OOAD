package ooad.timewise.cheerup;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

import ooad.timewise.R;

public class CatPictureActivity extends AppCompatActivity {
    private ImageView catImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_picture_layout);

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
        String urlPic = APIDataTaker.getInstance().getUrlCatPic();

        Glide.with(this).load(urlPic).into(catImageView);
    }

    private void clickOnOneMoreBtn(View view)  {
        try {
          showPicture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void clickOnBackBtn(View view) {
        switchToActivity(CheerUpActivity.class, this);
    }

}
