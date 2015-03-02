package eu.isawsm.accelerate.ax.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.isawsm.accelerate.Model.Car;

public class AxPreferences {

    public static Gson gson;
    private static final String AX_SERVER_ADDRESS="AxServerAddress";
    private static final String DRIVER_NAME = "DriverName";
    private static final String CARS = "cars";

    static {
        gson = new Gson();
    }

    public static void putSharedPreferencesInt(Context context, String key, int value){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static void putSharedPreferencesBoolean(Context context, String key, boolean val){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putBoolean(key, val);
        edit.apply();
    }

    public static void putSharedPreferencesString(Context context, String key, String val){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putString(key, val);
        edit.apply();
    }

    public static void putSharedPreferencesFloat(Context context, String key, float val){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putFloat(key, val);
        edit.apply();
    }

    public static void putSharedPreferencesLong(Context context, String key, long val){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putLong(key, val);
        edit.apply();
    }

    public static long getSharedPreferencesLong(Context context, String key, long _default){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getLong(key, _default);
    }

    public static float getSharedPreferencesFloat(Context context, String key, float _default){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getFloat(key, _default);
    }

    public static String getSharedPreferencesString(Context context, String key, String _default){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, _default);
    }

    public static int getSharedPreferencesInt(Context context, String key, int _default){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, _default);
    }

    public static boolean getSharedPreferencesBoolean(Context context, String key, boolean _default){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, _default);
    }

    public static void putSharedPreferencesCar(Context context, Car car) {

        ArrayList<Car> carsList = getSharedPreferencesCars(context);
        carsList.add(car);

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putString(CARS, gson.toJson(carsList));
        edit.apply();
    }

    public static ArrayList<Car> getSharedPreferencesCars(Context context){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(CARS, null);
        if(json == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(gson.fromJson(preferences.getString(CARS, ""), Car[].class)));


    }

    public static void setDriverName(Context context, String trim) {
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit=preferences.edit();
        edit.putString(DRIVER_NAME, trim);
        edit.apply();
    }

    public static String getDriverName(Context context) {
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(DRIVER_NAME, null);
    }

    public static void putServerAddress(Context context, String address){
        putSharedPreferencesString(context, AxPreferences.AX_SERVER_ADDRESS, address);
    }

    public static String getServerAddress(Context context) {
        return getSharedPreferencesString(context, AxPreferences.AX_SERVER_ADDRESS, "");
    }
}