package com.dineshredditsample.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsDefault {

    public static final boolean SHOULD_PRINT_LOG = false;
    public static final BuildType sBuildType = BuildType.PROD;
    private static final String SHARED_PREFERENCE_UTILS = "Shopping";
    private static final String SHARED_PREFERENCE_FCM_UTILS = "fcm";
    static Pattern letter = Pattern.compile("[a-zA-z]");
    static Pattern digit = Pattern.compile("[0-9]");
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences sharedPreferencesFcm;

    public static void debugLog(String tag, String message) {
        if (SHOULD_PRINT_LOG) {
            Log.d(tag, message);
        }
    }

    public static void errorLog(String tag, String message) {
        if (SHOULD_PRINT_LOG) {
        }
        Log.e(tag, message);
    }

    public static void infoLog(String tag, String message) {
        if (SHOULD_PRINT_LOG) {
            Log.i(tag, message);
        }
    }

    public static String checkNull(String data) {
        if (data == null) {
            return "";
        } else {
            return data;
        }
    }
    public static String  getCurrentdateandTime() {
        Date date = new Date();
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate= outputformat.format(date);
        return formattedDate;
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
    }
    public static String get24HoursTimeFormat(String inputTime){
        String outPut="";
        StringTokenizer tk = new StringTokenizer(inputTime);
        //  String date = tk.nextToken();
        String time = tk.nextToken();

      //  SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("kk:mm");
        Date dt;
        try {
            dt = sdfs.parse(time);
            outPut=sdfs.format(dt);
            System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outPut;
    }

    public static String timeFormat(String inputTime){
        String outPut="";
        StringTokenizer tk = new StringTokenizer(inputTime);
      //  String date = tk.nextToken();
        String time = tk.nextToken();

       // SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("kk:mm");
       // SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date dt;
        try {
            dt = sdfs.parse(time);
            outPut=sdfs.format(dt);
            System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outPut;

    }
    public static String differentBetweenTime(String startTime,String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        int hours=0;
        int mins=0;

        try {
            Date startDate =sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            long mills = startDate.getTime() - endDate.getTime();
             hours = (int)mills/(1000 * 60 * 60);
             mins = (int)(mills/(1000*60)) % 60;

            String diff = hours + ":" + mins;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs(hours)+"h"+Math.abs(mins)+"m";
    }

    public static String dateFormat(String dateString){
       // 2019-04-03 05:41:13

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDate = null;
        String date="";
        try {
            newDate = format.parse(dateString);
            format = new SimpleDateFormat("MMM dd,yyyy");
             date = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return  date;
    }

    public static String date(String data) {
        if (data == null) {
            return "";
        } else {
            return data;
        }
    }

    private static void initializeSharedPreference() {

        sharedPreferences = ShoppingApplication.getGlobalContext()
                .getSharedPreferences(SHARED_PREFERENCE_UTILS,
                        Context.MODE_PRIVATE);
    }
    private static void initializeFcmSharedPreference() {

        sharedPreferencesFcm = ShoppingApplication.getGlobalContext()
                .getSharedPreferences(SHARED_PREFERENCE_FCM_UTILS,
                        Context.MODE_PRIVATE);
    }

    public static String getHeadername() {
        return "APIAUTHORIZATION";

    }

    public static String getHeadervalue() {
        return "U2VjcmV0OjdHejY1Q1FaOCtoJl9zWkd4WHJARnRNM2tqRGNGUmVhfFBhc3N3b3JkOlNTRkFQSSM=";
    }

    public static void updateSharedPreference(String key, String value) {
        if (sharedPreferences == null) {
            initializeSharedPreference();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void updateFcmSharedPreference(String key, String value) {
        if (sharedPreferencesFcm == null) {
            initializeFcmSharedPreference();
        }

        SharedPreferences.Editor editor = sharedPreferencesFcm.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void updateSharedPreference(String key, boolean value) {
        if (sharedPreferences == null) {
            initializeSharedPreference();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, Boolean.toString(value));
        editor.commit();
    }
    public static String getFcmSharedPreferenceValue(String key) {
        if (sharedPreferencesFcm == null) {
            initializeFcmSharedPreference();
        }

        if (key != null) {
            return sharedPreferencesFcm.getString(key, null);
        } else {
            return null;
        }
    }

    public static String getSharedPreferenceValue(String key) {
        if (sharedPreferences == null) {
            initializeSharedPreference();
        }

        if (key != null) {
            return sharedPreferences.getString(key, null);
        } else {
            return "";
        }
    }

    public static boolean ok(String password) {
        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        return hasLetter.find() && hasDigit.find();
    }

    public static void printException(Exception exception) {
        exception.printStackTrace();
    }

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) ShoppingApplication
                .getGlobalContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isEmailValid(String email) {

        boolean flag;
        String expression = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" + "(" + "\\."
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" + ")+";

        CharSequence inputStr = email.trim();
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            flag = true;
        else {

            flag = false;
        }
        return flag;

    }

    public static void clearSesson() {

        sharedPreferences.edit().clear().commit();
    }

    public static boolean validate(String password) {
        return password
                .matches("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\[\\]~-])");
    }

    public static boolean isEmailPassword(String email) {

        boolean flag;
        String expression = "(?=.*[a-z])(?=.*\\d)";

        CharSequence inputStr = email.trim();
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            flag = true;
        else {

            flag = false;
        }
        return flag;

    }

    public static int checkScreensize(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        double finalVal = Math.round(screenInches);
        int screenSize = (int) finalVal;

        return screenSize;
    }

    public static void hideKeyboardForFocusedView(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static List<ResolveInfo> showAllShareApp() {
        List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.setType("text/plain");
        PackageManager pManager = ShoppingApplication.getGlobalContext().getPackageManager();
        mApps = pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }

    @SuppressLint("SimpleDateFormat")
    public static String currentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }


    public static enum BuildType {
        QA, PROD
    }


}
