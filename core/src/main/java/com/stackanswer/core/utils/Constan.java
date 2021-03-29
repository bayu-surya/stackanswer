package com.stackanswer.core.utils;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;

public class Constan {

    public static boolean compareObjects(Object a, Object b){
        String objectString1 = new Gson().toJson(a);
        String objectString2 = new Gson().toJson(b);
        return objectString1.equals(objectString2);
    }

    public static boolean execute(int[] strings, int searchString) {
        return ArrayUtils.contains(strings, searchString);
    }

    public static String setBahasa(String string){
        String sbahasa;
        if (string.toLowerCase().equals("en")){
            sbahasa="English";
        } else {
            sbahasa=string;
        }
        return sbahasa;
    }
}
