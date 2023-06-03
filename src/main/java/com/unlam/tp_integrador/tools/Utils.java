package com.unlam.tp_integrador.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Utils {

    public static final String UTF_8 = "UTF-8";
    public static final String PNG = "PNG";

    public static final String SHA_256 = "SHA-256";

    public static ArrayList<Integer> objectToArray(Object obj) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        if (obj instanceof Integer[] intArray) {
            arrayList.addAll(Arrays.asList(intArray));
        } else if (obj instanceof Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Integer) {
                    arrayList.add((Integer) element);
                } else if (element instanceof Double) {
                    arrayList.add(((Double) element).intValue());
                } else {
                    throw new IllegalArgumentException("Elemento no es de tipo Integer o Double: " + element);
                }
            }
        } else {
            throw new IllegalArgumentException("Objeto no es un array o una colección: " + obj);
        }
        return arrayList;
    }
}
