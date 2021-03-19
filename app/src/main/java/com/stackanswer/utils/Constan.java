package com.stackanswer.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;

import java.util.Objects;

public class Constan {

    public static void hideKeyboardFrom(Context context, View view) {
        if (view!=null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean compareObjects(Object a, Object b){
        String objectString1 = new Gson().toJson(a);
        String objectString2 = new Gson().toJson(b);
        return objectString1.equals(objectString2);
    }

    public static boolean execute(int[] strings, int searchString) {
        return ArrayUtils.contains(strings, searchString);
    }
}
