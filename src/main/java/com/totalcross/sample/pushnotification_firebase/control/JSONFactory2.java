package com.totalcross.sample.pushnotification_firebase.control;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import totalcross.json.JSONArray;
import totalcross.json.JSONException;
import totalcross.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Enum.valueOf;

public class JSONFactory2 {
    public JSONFactory2() {
    }

    public static <T> List<T> asList(String json, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, ArrayIndexOutOfBoundsException, NoSuchMethodException, SecurityException {
        ArrayList var2 = new ArrayList();

        try {
            JSONArray var3 = new JSONArray(json);

            for(int var4 = 0; var4 < var3.length(); ++var4) {
                try {
                    var2.add(parse(var3.getJSONObject(var4), classOfT));
                } catch (JSONException var6) {
                    var2.add(parse(var3.getJSONArray(var4), classOfT));
                }
            }
        } catch (JSONException var7) {
            var2.add(parse(json, classOfT));
        }

        return var2;
    }

    public static <T> T parse(String json, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, NoSuchMethodException, SecurityException {
        if (classOfT.isArray()) {
            T var2;
            try {
                JSONArray var3 = new JSONArray(json);
                var2 = classOfT.cast(Array.newInstance(classOfT.getComponentType(), var3.length()));

                for(int var4 = var3.length() - 1; var4 >= 0; --var4) {
                    Array.set(var2, var4, parse(var3.getJSONObject(var4), classOfT.getComponentType()));
                }
            } catch (JSONException var5) {
                var2 = classOfT.cast(Array.newInstance(classOfT.getComponentType(), 1));
                Array.set(var2, 0, parse(new JSONObject(json), classOfT.getComponentType()));
            }

            return var2;
        } else {
            return parse(new JSONObject(json), classOfT);
        }
    }

    public static <T> T parse(JSONArray jsonArray, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, ArrayIndexOutOfBoundsException, NoSuchMethodException, SecurityException {
        return parse((Object)null, (JSONArray)jsonArray, classOfT);
    }

    private static <T> T parse(Object outerObject, JSONArray jsonArray, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, ArrayIndexOutOfBoundsException, NoSuchMethodException, SecurityException {
        if (classOfT.isArray()) {
            T var3;
            try {
                var3 = classOfT.cast(Array.newInstance(classOfT.getComponentType(), jsonArray.length()));

                for(int var4 = jsonArray.length() - 1; var4 >= 0; --var4) {
                    Array.set(var3, var4, parse(outerObject, jsonArray.getJSONObject(var4), classOfT.getComponentType()));
                }
            } catch (JSONException var5) {
                var3 = classOfT.cast(Array.newInstance(classOfT.getComponentType(), 1));
                Array.set(var3, 0, parse(outerObject, jsonArray, classOfT.getComponentType()));
            }

            return var3;
        } else {
            return parse(outerObject, jsonArray, classOfT);
        }
    }

    public static <T> T parse(JSONObject jsonObject, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, NoSuchMethodException, SecurityException {
        return parse((Object)null, (JSONObject)jsonObject, classOfT);
    }

    private static <T, E extends Enum<E>> T parse(Object outerObject, JSONObject jsonObject, Class<T> classOfT) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, NoSuchMethodException, SecurityException {
        if (classOfT.isArray()) {
            throw new IllegalArgumentException();
        } else {
            T var3 = null;

            try {
                var3 = classOfT.newInstance();
            } catch (InstantiationException var17) {
                if (outerObject != null && classOfT.getName().indexOf(outerObject.getClass().getName()) != -1) {
                    Constructor var5 = classOfT.getDeclaredConstructor(outerObject.getClass());
                    if (var5 != null) {
                        var3 = (T) var5.newInstance(outerObject);
                    }
                }

                if (var3 == null) {
                    var3 = (T) valueOf((Class<E>) classOfT, jsonObject.getString("value"));
                    if (var3 != null) {
                        return var3;
                    }
                    throw var17;
                }
            }

            Method[] var4 = classOfT.getMethods();
            Method[] var18 = var4;
            int var6 = var4.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Method var8 = var18[var7];
                String var9 = var8.getName();
                Class[] var10 = var8.getParameterTypes();
                if (var10 != null && var10.length == 1 && var9.length() > 3 && var9.startsWith("set")) {
                    String var11 = Character.toLowerCase(var9.charAt(3)) + var9.substring(4);
                    String var12 = null;
                    if (jsonObject.has(var11)) {
                        var12 = var11;
                    } else if (!jsonObject.has(var12 = var11.toLowerCase())) {
                        StringBuilder var13 = new StringBuilder();
                        boolean var14 = false;

                        for(int var15 = 0; var15 < var11.length(); ++var15) {
                            char var16 = var11.charAt(var15);
                            if (!var14 && Character.isUpperCase(var16)) {
                                var14 = true;
                                var13.append('_');
                            } else {
                                var14 = false;
                            }

                            var13.append(Character.toLowerCase(var16));
                        }

                        String var20 = var13.toString();
                        if (jsonObject.has(var20)) {
                            var12 = var20;
                        }
                    }

                    if (!jsonObject.isNull(var12)) {
                        Class var19 = var8.getParameterTypes()[0];
                        if (var19.isPrimitive()) {
                            if (var19.isAssignableFrom(Boolean.TYPE)) {
                                var8.invoke(var3, jsonObject.getBoolean(var12));
                            } else if (var19.isAssignableFrom(Integer.TYPE)) {
                                var8.invoke(var3, jsonObject.getInt(var12));
                            } else if (var19.isAssignableFrom(Long.TYPE)) {
                                var8.invoke(var3, jsonObject.getLong(var12));
                            } else if (var19.isAssignableFrom(Double.TYPE)) {
                                var8.invoke(var3, jsonObject.getDouble(var12));
                            }
                        } else if (var19.isAssignableFrom(String.class)) {
                            var8.invoke(var3, jsonObject.getString(var12));
                        } else if (var19.isAssignableFrom(Double.class)) {
                            var8.invoke(var3, jsonObject.getDouble(var12));
                        } else if (var19.isAssignableFrom(Integer.class)) {
                            var8.invoke(var3, jsonObject.getInt(var12));
                        } else if (var19.isAssignableFrom(Long.class)) {
                            var8.invoke(var3, jsonObject.getLong(var12));
                        } else if (var19.isAssignableFrom(Boolean.class)) {
                            var8.invoke(var3, jsonObject.getBoolean(var12));
                        } else if (var19.isArray()) {
                            var8.invoke(var3, parse(var3, jsonObject.getJSONArray(var12), var19));
                        } else {
                            var8.invoke(var3, parse(var3, jsonObject.getJSONObject(var12), var19));
                        }
                    }
                }
            }

            return var3;
        }
    }
}
