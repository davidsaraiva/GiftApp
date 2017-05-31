package pt.ds.giftApp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pt.ds.giftApp.constants.AppConstants;
import pt.ds.giftApp.dto.ImageYear;
import pt.ds.giftApp.singleton.AppData;

/**
 * Created by DS on 25/05/2017.
 */

public class PresentationAdapter extends RecyclerView.Adapter<PresentationAdapter.CardViewHolder> {

    private List<ImageYear> imageList;

    public PresentationAdapter(List<ImageYear> imageList) {
        this.imageList = imageList;
    }

    @Override
    public PresentationAdapter.CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.year_image_card, viewGroup, false);
        return new CardViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder viewHolder, int position) {
        final ImageYear image = imageList.get(position);

        viewHolder.imageYear.setImageDrawable(image.getImageDrawable());
        viewHolder.footerNote.setText(image.getYear());
        viewHolder.footerNote.setTypeface(AppData.getInstance().getFontByName(AppConstants.FONTS.WHERE_STARS.getFontName()));
        viewHolder.description.setText(image.getImageDescription());
        viewHolder.description.setTypeface(AppData.getInstance().getFontByName(AppConstants.FONTS.WHERE_STARS.getFontName()));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageYear;
        public TextView description;
        public TextView footerNote;

        public CardViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageYear = (ImageView) itemLayoutView.findViewById(R.id.yearPhoto);
            description = (TextView) itemLayoutView.findViewById(R.id.description);
            footerNote = (TextView) itemLayoutView.findViewById(R.id.footerNote);
        }
    }
}