package pt.ds.giftApp.singleton;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import pt.ds.giftApp.App;
import pt.ds.giftApp.R;
import pt.ds.giftApp.constants.AppConstants;
import pt.ds.giftApp.dto.ImageYear;
import pt.ds.giftApp.dto.Question;

/**
 * Created by DS on 24/05/2017.
 */

public class AppData {

    private static final AppData instance = new AppData();

    private String[] pickerTitles;
    private TypedArray pickerColors;
    private TypedArray pickerImages;
    private List<Question> questionsList;
    private HashMap<String, Typeface> fontsMap;
    private HashMap<String, Drawable> imagesByPath = new HashMap<>();

    private AppData() {
        super();
        initializeData();
    }

    private Context getContext() {
        return App.getAppContext();
    }

    public static AppData getInstance() {
        return AppData.instance;
    }

    private void initializeData() {
        pickerTitles = getContext().getResources().getStringArray(R.array.pickerTexts);
        pickerColors = getContext().getResources().obtainTypedArray(R.array.pickerColors);
        pickerImages = getContext().getResources().obtainTypedArray(R.array.pickerImages);
        initializeFonts();
        initializeQuestions();
    }

    private void initializeFonts() {
        fontsMap = new HashMap<>();
        for (AppConstants.FONTS font : AppConstants.FONTS.values()) {
            fontsMap.put(font.getFontName(), Typeface.createFromAsset(getContext().getAssets(), font.getFontName()));
        }
    }

    private void initializeQuestions() {
        questionsList = new ArrayList<>();
        for (AppConstants.QUESTIONS question : AppConstants.QUESTIONS.values()) {
            Question.QuestionBuilder builder = new Question.QuestionBuilder(getString(question.getQuestionStringId()));
            for (int i = 0; i < question.getAnswersIdList().size();) {
                builder.addAnswer(getString(question.getAnswersIdList().get(i)), ++i == question.getCorrectAnswerPosition());
            }
            questionsList.add(builder.build());
        }
    }

    public Question getRandomQuestion() {
        return questionsList.get(getRandomInt(questionsList.size()));
    }

    public int getTitlesCount() {
        return pickerTitles == null ? 0 : pickerTitles.length;
    }

    public String getTitleFromPosition(int position) {
        if (pickerTitles == null || pickerTitles.length <= position) {
            return null;
        }
        return pickerTitles[position];
    }

    public int getGradientColorStart(int position) {
        return pickerColors.getColor((position * 2) % pickerColors.length(), AppConstants.DEFAULT_COLOR);
    }

    public int getGradientColorEnd(int position) {
        return pickerColors.getColor((position * 2) % pickerColors.length() + 1, AppConstants.DEFAULT_COLOR);
    }

    public int getImageFromPosition(int position) {
        return pickerImages.getResourceId(position, AppConstants.DEFAULT_IMAGE);
    }

    public Typeface getFontByName(String fontName) {
        return fontsMap.get(fontName);
    }

    private String getString(int stringResID) {
        return getContext().getString(stringResID);
    }

    public int getRandomInt(int maxRange) {
        return (new Random()).nextInt(maxRange);
    }

    public Drawable getAssetImage(String imagePath)  {
       try {
           if (!imagesByPath.containsKey(imagePath)) {
               AssetManager assets = App.getAppContext().getResources().getAssets();
               InputStream buffer = new BufferedInputStream((assets.open(imagePath)));
               Bitmap bitmap = BitmapFactory.decodeStream(buffer);
               imagesByPath.put(imagePath, new BitmapDrawable(App.getAppContext().getResources(), bitmap));
           }
           return imagesByPath.get(imagePath);
       } catch (IOException ex) {
           Log.e("AppData","getAssetImage - Failed to obtain asset from path" + imagePath, ex);
       }
       return null;
    }

    public List<ImageYear> getImagesForYears(List<String> years) {
        List<ImageYear> images = new ArrayList<>();
        for (String year : years) {
            AppConstants.IMAGES imagesEnum = AppConstants.IMAGES.getImagesByYear(year);
            if (imagesEnum == null) {
                continue;
            }
            String[] imagesDescArr = getContext().getResources().getStringArray(imagesEnum.getDescArrayResId());
            for (int idx = 0; idx < imagesEnum.getImagesPaths().size(); ++idx ) {
                Drawable drawable = getAssetImage(imagesEnum.getImagesPaths().get(idx));
                if (drawable == null) {
                    continue;
                }
                images.add(new ImageYear(year, drawable, imagesDescArr[idx]));
            }
        }
        return images;
    }


}
