package pt.ds.giftApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.enums.Animation;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import pt.ds.giftApp.constants.AppConstants;
import pt.ds.giftApp.dto.Question;
import pt.ds.giftApp.singleton.AppData;

public class MainActivity extends AppCompatActivity {

    final float BUBBLE_TEXT_SIZE = 90;

    @BindView(R.id.picker)
    BubblePicker picker;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareTitle();
        preparePicker();
    }

    private void prepareTitle() {
        title.setTypeface(AppData.getInstance().getFontByName(AppConstants.FONTS.KINKIE.getFontName()));
        title.setText(getString(R.string.welcomeMessage));
    }

    private void preparePicker() {
        picker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return AppData.getInstance().getTitlesCount();
            }

            @Override
            public PickerItem getItem(int position) {
                return createPicker(position);
            }
        });
        picker.setBubbleSize(AppConstants.BUBBLE_SIZE);
        picker.setCenterImmediately(true);
    }

    private PickerItem createPicker(int position) {
        PickerItem item = new PickerItem();
        item.setTitle(AppData.getInstance().getTitleFromPosition(position));
        item.setGradient(new BubbleGradient(AppData.getInstance().getGradientColorStart(position),
                AppData.getInstance().getGradientColorEnd(position), BubbleGradient.VERTICAL));
        item.setTypeface(AppData.getInstance().getFontByName(AppConstants.FONTS.NUMBERS.getFontName()));
        item.setTextSize(BUBBLE_TEXT_SIZE);
        item.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        item.setBackgroundImage(ContextCompat.getDrawable(this, AppData.getInstance().getImageFromPosition(position)));
        return item;
    }

    @OnClick(R.id.fabNext)
    public void fabClickListener() {
        List<PickerItem> selectedItems = picker.getSelectedItems();
        if (selectedItems == null || selectedItems.isEmpty()) {
            Toasty.error(getApplicationContext(), getString(R.string.error_no_selection)).show();
        } else {
            showQuestion();
        }
    }

    @OnClick(R.id.fabInfo)
    public void fabInfoClickListener() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    private void showQuestion() {
        final Question question = AppData.getInstance().getRandomQuestion();
        new PanterDialog(this)
                .setHeaderBackground(R.drawable.dialog_header)
                .setDialogType(DialogType.SINGLECHOICE)
                .isCancelable(true)
                .setTitle(question.getQuestionTxt())
                .setNegative(getString(R.string.dialog_exit))
                .setPositive(getString(R.string.dialog_confirm))
                .withAnimation(Animation.POP)
                .items(question.getAnswersTexts(), new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        if (!question.isCorrectAnswer(text)) {
                            Toasty.error(getApplicationContext(), getString(R.string.error_wrong_answer)).show();
                        } else {
                            startPresentationActivity();
                        }
                    }
                })
                .show();
    }

    private void startPresentationActivity() {
        List<PickerItem> selectedItems = picker.getSelectedItems();
        if (selectedItems == null || selectedItems.isEmpty()) {
            return;
        }
        ArrayList<String> selectedYears = new ArrayList<>();
        for (PickerItem item : selectedItems) {
            selectedYears.add(item.getTitle());
        }
        Intent intent = new Intent(this, PresentationActivity.class);
        intent.putStringArrayListExtra(PresentationActivity.YEAR_PARAM, selectedYears);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        picker.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        picker.onResume();
    }


}
