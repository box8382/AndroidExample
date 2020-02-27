package com.pmkproject.ex87fonttest;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MyFontTextView extends AppCompatTextView {

    //생성자1 : 자바에서 new를 할때
    public MyFontTextView(Context context) {
        super(context);

        //폰트설정을 생성자에서!!!
        Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/CookieRun Black.otf");
        setTypeface(typeface);
        setTextSize(40);
        setTextColor(Color.WHITE);
    }

    //생성자2 : XML로 만들 때
    public MyFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/CookieRun Black.otf");
        setTypeface(typeface);
        setTextSize(40);
        setTextColor(Color.WHITE);
    }
}
