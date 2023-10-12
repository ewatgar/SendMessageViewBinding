package com.example.sendmessageviewbinding;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class AboutActivity extends MaterialAboutActivity {
    @Override
    @NonNull
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        setTheme(R.style.AppTheme_MaterialAboutActivity);
        MaterialAboutCard cardAuthor = new MaterialAboutCard.Builder()
                .title(R.string.cardAuthorTitle)
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.cardAuthorItemNameText)
                        .subText(R.string.cardAuthorItemNameSubtext)
                        .icon(R.drawable.ic_person)
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.cardAuthorItemGithubText)
                        .icon(R.drawable.ic_share)
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/ewatgar")))
                        .build())
                .cardColor(getColor(R.color.colorPrimaryLight))
                .build();

        MaterialAboutCard cardVersion = new MaterialAboutCard.Builder()
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.cardVersionItemText)
                        .subText(R.string.cardVersionItemSubtext)
                        .icon(R.drawable.ic_info)
                        .build())
                .cardColor(getColor(R.color.colorPrimaryLight))
                .build();
        return new MaterialAboutList.Builder()
                .addCard(cardAuthor)
                .addCard(cardVersion)
                .build();
    }
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.menu_aboutas);
    }

}
