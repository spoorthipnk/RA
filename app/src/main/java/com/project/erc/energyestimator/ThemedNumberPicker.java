package com.project.erc.energyestimator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.NumberPicker;

/**
 * Created by spoor on 2/3/2017.
 */

public class ThemedNumberPicker extends NumberPicker {
    public ThemedNumberPicker(Context context) {
        super(context);
    }

    public ThemedNumberPicker(Context context, AttributeSet attrs) {
        super(new ContextThemeWrapper(context,R.style.NumberPickerTextColorStyle),attrs);
    }
}
