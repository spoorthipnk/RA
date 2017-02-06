package com.project.erc.energyestimator;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import java.lang.reflect.Field;


public class LightingFragment extends Fragment {

    public LightingFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lighting, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NumberPicker numberPickerInc60 = (NumberPicker)view.findViewById(R.id.numberPickerInc60);
        setNumberPickerTextColor(numberPickerInc60,R.color.colorPrimary);
        NumberPicker numberPickerCfl16 = (NumberPicker)view.findViewById(R.id.numberPickerCfl16);
        NumberPicker numberPickerLed10 = (NumberPicker)view.findViewById(R.id.numberPickerLed10);

        //set the max,min and interval updation values for the number picker
        numberPickerCfl16.setMaxValue(10);
        numberPickerCfl16.setMinValue(0);
        numberPickerCfl16.setOnLongPressUpdateInterval(5);
        numberPickerCfl16.setWrapSelectorWheel(true);

        //set the max,min and interval updation values for the number picker
        numberPickerInc60.setMaxValue(10);
        numberPickerInc60.setMinValue(0);
        numberPickerInc60.setOnLongPressUpdateInterval(5);
        numberPickerInc60.setWrapSelectorWheel(true);

        //set the max,min and interval updation values for the number picker
        numberPickerLed10.setMaxValue(10);
        numberPickerLed10.setMinValue(0);
        numberPickerLed10.setOnLongPressUpdateInterval(5);
        numberPickerLed10.setWrapSelectorWheel(true);

    }

    //Trying to change the appearance of the number picker, currently it does not work.
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("msg", e);
                }
                catch(IllegalAccessException e){
                    Log.w("msg", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("msg", e);
                }
            }
        }
        return false;
    }

}
