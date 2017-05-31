package pt.ds.giftApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pt.ds.giftApp.singleton.AppData;

public class PresentationActivity extends AppCompatActivity {

    static final String YEAR_PARAM = "years";

    @BindView(R.id.imageReciclerView)
    RecyclerView imageReciclerView;

    private PresentationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        ButterKnife.bind(this);
        List<String> years = getIntent().getStringArrayListExtra(YEAR_PARAM);
        imageReciclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        imageReciclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new PresentationAdapter(AppData.getInstance().getImagesForYears(years));
        imageReciclerView.setAdapter(adapter);
    }
}
