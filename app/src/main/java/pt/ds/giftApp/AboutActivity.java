package pt.ds.giftApp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pt.ds.giftApp.constants.AppConstants;
import pt.ds.giftApp.singleton.AppData;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.aboutText)
    TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        aboutText.setTypeface(AppData.getInstance().getFontByName(AppConstants.FONTS.WHERE_STARS.getFontName()));
    }

}
