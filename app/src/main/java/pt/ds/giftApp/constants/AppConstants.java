package pt.ds.giftApp.constants;

import java.util.Arrays;
import java.util.List;

import pt.ds.giftApp.R;

/**
 * Created by DS on 24/05/2017.
 */

public class AppConstants {

    public static int DEFAULT_COLOR = 0;
    public static int DEFAULT_IMAGE = 0;
    public static int BUBBLE_SIZE = 18;

    public enum FONTS {
         NUMBERS        ("4_my_lover_numeros.ttf")
        ,WHERE_STARS    ("where_stars.ttf")
        ,KINKIE         ("kinkie.ttf");

        String fontName;

        FONTS(String fontName) {
            this.fontName = fontName;
        }

        public String getFontName() {
            return this.fontName;
        }
    }

    public enum QUESTIONS {
         Q1(R.string.q1, Arrays.asList(R.string.aq11, R.string.aq12, R.string.aq13), 1)
        ,Q2(R.string.q2, Arrays.asList(R.string.aq21, R.string.aq22, R.string.aq23), 1)
        ,Q3(R.string.q3, Arrays.asList(R.string.aq31, R.string.aq32, R.string.aq33), 1)
        ,Q4(R.string.q4, Arrays.asList(R.string.aq41, R.string.aq42, R.string.aq43), 1)
        ,Q5(R.string.q5, Arrays.asList(R.string.aq51, R.string.aq52, R.string.aq53), 1)
        ,Q6(R.string.q6, Arrays.asList(R.string.aq61, R.string.aq62, R.string.aq63), 1)
        ,Q7(R.string.q7, Arrays.asList(R.string.aq71, R.string.aq72, R.string.aq73), 1)
        ,Q8(R.string.q8, Arrays.asList(R.string.aq81, R.string.aq82, R.string.aq83), 1)
        ,Q9(R.string.q9, Arrays.asList(R.string.aq91, R.string.aq92, R.string.aq93), 1);

        int questionStringId;
        List<Integer> answersIdList;
        int correctAnswerPosition;

        QUESTIONS(int questionStringId, List<Integer> answersIdList, int correctAnswerPosition) {
            this.questionStringId = questionStringId;
            this.answersIdList = answersIdList;
            this.correctAnswerPosition = correctAnswerPosition;
        }

        public int getQuestionStringId() {
            return questionStringId;
        }

        public List<Integer> getAnswersIdList() {
            return answersIdList;
        }

        public int getCorrectAnswerPosition() {
            return correctAnswerPosition;
        }
    }

    public enum IMAGES {
         Y2002("2002",
                 Arrays.asList("images/2002/1.png"),
                 R.array.image_desc_2002)
        ,Y2003("2003",
                Arrays.asList("images/2003/1.png","images/2003/2.png"),
                R.array.image_desc_2003)
        ,Y2004("2004",
                Arrays.asList("images/2004/1.png","images/2004/2.png"),
                R.array.image_desc_2004)
        ,Y2005("2005",
                Arrays.asList("images/2005/1.png"),
                R.array.image_desc_2005)
        ,Y2006("2006",
                Arrays.asList("images/2006/1.png","images/2006/2.png"),
                R.array.image_desc_2006)
        ,Y2007("2007",
                Arrays.asList("images/2007/1.png","images/2007/2.png"),
                R.array.image_desc_2007)
        ,Y2008("2008",
                Arrays.asList("images/2008/1.png"),
                R.array.image_desc_2008)
        ,Y2009("2009",
                Arrays.asList("images/2009/1.png"),
                R.array.image_desc_2009)
        ,Y2010("2010",
                Arrays.asList("images/2010/1.png","images/2010/2.png"),
                R.array.image_desc_2010)
        ,Y2011("2011",
                Arrays.asList("images/2011/1.png"),
                R.array.image_desc_2011)
        ,Y2012("2012",
                Arrays.asList("images/2012/1.png","images/2012/2.png"),
                R.array.image_desc_2012)
        ,Y2013("2013",
                Arrays.asList("images/2013/1.png","images/2013/2.png","images/2013/3.png","images/2013/4.png"),
                R.array.image_desc_2013)
        ,Y2014("2014",
                Arrays.asList("images/2014/1.png","images/2014/2.png"),
                R.array.image_desc_2014)
        ,Y2015("2015",
                Arrays.asList("images/2015/1.png","images/2015/2.png"),
                R.array.image_desc_2015)
        ,Y2016("2016",
                Arrays.asList("images/2016/1.png","images/2016/2.png"),
                R.array.image_desc_2016)
        ,Y2017("2017",
                Arrays.asList("images/2017/1.png","images/2017/2.png"),
                R.array.image_desc_2017)
        ,Y2018("2018",
                Arrays.asList("images/2018/1.png"),
                R.array.image_desc_2018);

        String imageYear;
        List<String> imagesPaths;
        int descArrayResId;

        public String getImageYear() {
            return imageYear;
        }
        public List<String> getImagesPaths() {
            return imagesPaths;
        }
        public int getDescArrayResId() {
            return descArrayResId;
        }

        IMAGES(String imageYear, List<String> imagesPaths, int descArrayResId) {
            this.imageYear = imageYear;
            this.imagesPaths = imagesPaths;
            this.descArrayResId = descArrayResId;
        }

        public static IMAGES getImagesByYear(String year) {
            for (IMAGES image : IMAGES.values()) {
                if (image.getImageYear().equals(year)) {
                    return image;
                }
            }
            return null;
        }

    }



}
