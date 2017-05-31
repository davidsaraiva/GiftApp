package pt.ds.giftApp.dto;

import android.graphics.drawable.Drawable;

/**
 * Created by DS on 22/05/2017.
 */

public class ImageYear {

    private String year;
    private Drawable imageDrawable;
    private String imageDescription;

    public ImageYear(String year, Drawable imageDrawable, String imageDescription) {
        this.year = year;
        this.imageDrawable = imageDrawable;
        this.imageDescription = imageDescription;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

}
