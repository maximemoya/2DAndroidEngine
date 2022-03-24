package fr.mm.evolutiongamemax.bibliotheques;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class MaximeToolsLayoutV1 {

    public final static String TAG = "MAXTAG";

    public final static String downloadDirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

    public static BroadcastReceiver broadcastReceiver = null;
    public static File fileDownload = null;


    // ---> LinearLayout.LayoutParams :


    public static LinearLayout.LayoutParams setLayoutParams(Context context, int layout_width, int layout_height, int margin_left, int margin_right, int margin_top, int margin_bottom, int gravity, float weight) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layout_width, layout_height);
        params.setMargins(convertDpToPixel(context, margin_left), convertDpToPixel(context, margin_top), convertDpToPixel(context, margin_right), convertDpToPixel(context, margin_bottom));
        params.gravity = gravity;
        params.weight = weight;
        return params;
    }


    // ---> LINEARLAYOUT :


    public static LinearLayout setLinearLayout(Context context, LinearLayout.LayoutParams maximeToolsLayoutParams, int linearLayout_Orientation) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(maximeToolsLayoutParams);
        linearLayout.setOrientation(linearLayout_Orientation);
        return linearLayout;

    }


    // ---> TEXTVIEW :


    public static TextView setTextView(Context context, String text) {

        TextView textView = new TextView(context);
        textView.setLayoutParams(setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, 0, 0));
        textView.setText(text);
        return textView;

    }

    public static TextView setTextView(Context context, LinearLayout.LayoutParams layoutParams, String text) {

        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        return textView;

    }


    // ---> LINEARLAYOUT WITH TWO TEXTVIEW :


    public static LinearLayout setLinearLayoutWithTwoTextView(Context context, String text1, String text2) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        5, 5, 5, 5, 0, 0), LinearLayout.HORIZONTAL);

        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 10, 0, 0, Gravity.CENTER, 1),
                text1);
        textView.setGravity(Gravity.END);
        layout.addView(textView);

        textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                10, 0, 0, 0, Gravity.CENTER, 1),
                text2);
        textView.setGravity(Gravity.START);
        layout.addView(textView);

        return layout;

    }

    public static LinearLayout setLinearLayoutWithTwoTextView(Context context, String text1, int weight1, String text2, int weight2) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        5, 5, 5, 5, 0, 0), LinearLayout.HORIZONTAL);

        // TextView1 :
        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 10, 0, 0, Gravity.CENTER, weight1),
                text1);
        textView.setGravity(Gravity.END);
        layout.addView(textView);

        // TextView2 :
        textView = setTextView(
                context,
                setLayoutParams
                        (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                                10, 0, 0, 0, Gravity.CENTER, weight2),
                text2);
        textView.setGravity(Gravity.START);
        layout.addView(textView);

        return layout;

    }

    public static LinearLayout setLinearLayoutWithTwoTextView(Context context, String text1, int gravity1, int size1, int intColor1, int weight1, String text2, int gravity2, int size2, int intColor2, int weight2) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        5, 5, 5, 5, 0, 0), LinearLayout.HORIZONTAL);

        // TextView1 :
        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 10, 0, 0, Gravity.CENTER, weight1),
                text1);
        textView.setTextColor(context.getColor(intColor1));
        textView.setTextSize(size1);
        textView.setGravity(gravity1);
        layout.addView(textView);

        // TextView2 :
        textView = setTextView(
                context,
                setLayoutParams
                        (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                                10, 0, 0, 0, Gravity.CENTER, weight2),
                text2);
        textView.setTextColor(context.getColor(intColor2));
        textView.setTextSize(size2);
        textView.setGravity(gravity2);
        layout.addView(textView);

        return layout;

    }


    // ---> LINEARLAYOUT WITH IMAGEVIEW AND TEXTVIEW :

    public static LinearLayout setLinearLayoutWithImageViewAndTextView(Context context, int rIntDrawableImage1, int weight1, String text2, int color, int weight2) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, 0, 0), LinearLayout.HORIZONTAL);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setLayoutParams
                (context, 0, 100,
                        0, 0, 0, 0, Gravity.CENTER, weight1));
        imageView.setImageDrawable(ContextCompat.getDrawable(context, rIntDrawableImage1));
        layout.addView(imageView);

        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, 0, LinearLayout.LayoutParams.MATCH_PARENT + 100,
                                0, 0, 0, 0, Gravity.CENTER, weight2),
                text2);
        textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        textView.setTextSize(18);
        textView.setTextColor(context.getColor(color));
        layout.addView(textView);

        return layout;

    }

    public static LinearLayout setLinearLayoutWithImageViewAndMultiTextView(Context context, int rIntDrawableImage1, int weightImgPerCent, String[] textarray, int colorID) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 5, 5, Gravity.CENTER, 0), LinearLayout.HORIZONTAL);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setLayoutParams
                (context, 0, 200,
                        0, 0, 0, 0, Gravity.CENTER, weightImgPerCent));
        imageView.setImageDrawable(ContextCompat.getDrawable(context, rIntDrawableImage1));
        layout.addView(imageView);

        LinearLayout textLinearLayout = setLinearLayout(context,
                setLayoutParams(context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, Gravity.CENTER, 100), LinearLayout.VERTICAL);

        for (int i = 0; i < textarray.length; i++) {

            TextView textView = setTextView(
                    context,
                    setLayoutParams
                            (context, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                                    0, 0, 0, 0, Gravity.CENTER, 1),
                    textarray[i]);
            textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            textView.setTextColor(context.getColor(colorID));
            textLinearLayout.addView(textView);

        }

        layout.addView(textLinearLayout);

        return layout;

    }


    // ---> LINEARLAYOUT WITH IMAGEVIEWUp AND TEXTVIEWBelow :


    public static LinearLayout setLinearLayoutWithImageViewUPAndTextViewBELOW(Context context, int rIntDrawableImage1, String text, int color) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, Gravity.CENTER, 1), LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, 100,
                        0, 0, 0, 0, Gravity.CENTER, 0));
        imageView.setImageDrawable(ContextCompat.getDrawable(context, rIntDrawableImage1));
        layout.addView(imageView);

        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, Gravity.CENTER, 0),
                text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        textView.setTextColor(context.getColor(color));
        layout.addView(textView);

        return layout;

    }

    // RESOURCE DISPLAY :
    public static LinearLayout setLinearLayoutWithImageViewUPAndTextViewBELOWRESSOURCESDISPLAY(Context context, int rIntDrawableImage1, String text, int color, String text2) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, Gravity.CENTER, 1), LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, 100,
                        0, 0, 0, 0, Gravity.CENTER, 0));
        imageView.setImageDrawable(ContextCompat.getDrawable(context, rIntDrawableImage1));
        layout.addView(imageView);

        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, Gravity.CENTER, 0),
                text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        textView.setTextColor(context.getColor(color));
        layout.addView(textView);

        textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, Gravity.CENTER, 0),
                text2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(12);
        textView.setTextColor(context.getColor(color));
        layout.addView(textView);

        return layout;

    }

    // PLANET DISPLAY :
    public static LinearLayout setLinearLayoutWithImageViewUPAndTextViewBELOWVERTICAL(Context context, int rIntDrawableImage1, String text, int color) {

        LinearLayout layout = setLinearLayout(context,
                setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                        0, 0, 20, 20, Gravity.CENTER, 1), LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setLayoutParams
                (context, 150, 150,
                        0, 0, 0, 0, Gravity.CENTER, 0));
        imageView.setImageDrawable(ContextCompat.getDrawable(context, rIntDrawableImage1));
        layout.addView(imageView);

        TextView textView = setTextView(
                context,
                setLayoutParams
                        (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, Gravity.CENTER, 0),
                text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setTextColor(context.getColor(color));
        layout.addView(textView);

        return layout;

    }

    // EDIT TEXT VIEW :

    public static void hideKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }


    // IMAGE :

    public static Bitmap setImageBitmap(Context context, int rDrawablePathImage) {

        return BitmapFactory.decodeResource(context.getResources(), rDrawablePathImage);

    }

    public static Bitmap setResizeBitmap(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    // ANIMATION :

    public static void runAnimationTextIN(Context context, int rAnimPath, TextView textView, String text) {
        Animation a = AnimationUtils.loadAnimation(context, rAnimPath);
        a.reset();
        textView.clearAnimation();
        textView.setText(text);
        textView.startAnimation(a);
    }

    public static void runAnimationTextOUT(Context context, int rAnimPath, TextView textView) {
        Animation a = AnimationUtils.loadAnimation(context, rAnimPath);
        a.reset();
        textView.clearAnimation();
        textView.startAnimation(a);
    }

    public void timeInterfaceThreadToUpdateActivityViews(AppCompatActivity activity, int delayInMs, timerInterface itf) {
        Thread th = new Thread(new Runnable() {

            private long startTime = System.currentTimeMillis(); // time in ms since 1970

            public void run() {

                while (true) {

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            itf.method();
                        }
                    });
                    try {
                        Thread.sleep(delayInMs);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        th.start();
    }


    // MUSIC and SOUND :

    public static MediaPlayer setMusicMediaPlayer(Context context, int rRawPathMusicSound) {

        return MediaPlayer.create(context, rRawPathMusicSound);
    }

    public static void musicMediaPlayerPlay(MediaPlayer mediaPlayer, Boolean isLooping) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(1);
            } else {
                mediaPlayer.start();
            }
            mediaPlayer.setLooping(isLooping);
        }

    }

    public static void musicMediaPlayerPlay(Context context, int rRawPath, Boolean isLooping) {

        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(context, rRawPath);
        mediaPlayer.seekTo(1);
        mediaPlayer.start();
        mediaPlayer.setLooping(isLooping);

    }

    public static void musicMediaPlayerStopResetRelease(MediaPlayer mediaPlayer) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }


    }

    public static void musicMediaPlayerChangeMusicAndPlay(Context context, MediaPlayer mediaPlayer, int rRawPath, Boolean isLooping) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, rRawPath);
        mediaPlayer.seekTo(1);
        mediaPlayer.start();
        mediaPlayer.setLooping(isLooping);


    }


    //FILES AND PERMISSION REQUEST :

    public static final int get_CAMERA_PERMISSION_CODE = 100;
    public static final int get_STORAGE_PERMISSION_CODE = 101;

    public interface storageInterface {
        void whenStorageisGranded();
    }

    /**
     * <p><strong>EXEMPLE UTILISATION :</strong>
     * </p><p>
     * A utiliser dans la fonction  :
     * </p><p>
     * Override
     * </p><p>
     * public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
     * </p><p>
     * {
     * </p><p>
     * super.onRequestPermissionsResult(requestCode,permissions,grantResults);
     * </p><p>
     * VOTRE METHOD permissionRequestStorage(Context, int, int[], storageInterface)
     * </p><p>
     * }
     * </p>
     *
     * @param context          the ContextClass where is called "onRequestPermissionsResult()".
     * @param requestCode      The request code passed in "onRequestPermissionsResult()".
     * @param storageInterface the interface with single Method() to do when request is granted.
     * @param grantResults     The grant results passed in "onRequestPermissionsResult()".
     */
    public static void permissionRequestStorage(Context context, int requestCode, int[] grantResults, storageInterface storageInterface) {

        /*
        MAX EXEMPLE :

        @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        MaximeToolsLayoutV1.permissionRequestStorage(context, requestCode, grantResults, new MaximeToolsLayoutV1.storageInterface() {
            @Override
            public void whenStorageisGranded() {

                # add code here
            }
        });
    }
         */

        if (requestCode == get_CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(context,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == get_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                storageInterface.whenStorageisGranded();


            } else {
                Toast.makeText(context,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

    public static void permissionCHECK(Context context, String permission, int requestCode, storageInterface storageInterface) {

        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{permission},
                    requestCode);
        } else {
            Toast.makeText(context,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
            storageInterface.whenStorageisGranded();
        }

    }


    // FILES METHODS :
    /*
    "https://api.coinbase.com/v2/prices/BTC-USD/spot" @link
    */

    public interface downloadFileInterface {
        void toDoWhenDownloaded();
    }

    public static void downloadInContentFilesDir(Context context, String urlPath, String fileWithExtension, downloadFileInterface downloadFileInterface) {

        for (int i = 0; i < 3; i++) {

            File filetest = fileCreateOverRideMethods("/storage/self/primary" + context.getFilesDir().getPath() + "/" + fileWithExtension);
            if (filetest != null) {
                filetest.delete();
            }

        }

        File filetest = fileCreateOverRideMethods("/storage/self/primary" + context.getFilesDir().getPath() + "/" + fileWithExtension + "-1");
        if (filetest != null) {
            filetest.delete();
        }
        filetest = fileCreateOverRideMethods("/storage/self/primary" + context.getFilesDir().getPath() + "/" + fileWithExtension + "-2");
        if (filetest != null) {
            filetest.delete();
        }


        Uri uri = Uri.parse(urlPath);
        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setTitle("dlFile.json");
        request.setDescription("Downloading file");
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setMimeType(getMimeType(context, uri));
        request.setDestinationInExternalPublicDir(context.getFilesDir().getPath(), "/" + fileWithExtension);
//        request.setDestinationUri(Uri.fromFile(new File(context.getFilesDir().getPath(),"PriceCoin1.json")));

        long myDownloadID = downloadmanager.enqueue(request);

        // MESSAGE CONFIRMATION DownLoad :
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == myDownloadID) {

                    fileDownload = fileCreateOverRideMethods("/storage/self/primary" + context.getFilesDir().getPath() + "/" + fileWithExtension);
//                    Toast.makeText(context, "DownLoad COMPLETE", Toast.LENGTH_SHORT).show();
                    downloadFileInterface.toDoWhenDownloaded();

//                    Sauvegarde sauvegarde = MaximeToolsLayoutV1.deserializeSauvegardeFromFILE(context, MaximeToolsLayoutV1.fileDownload);
//
//                    assert sauvegarde != null;
//                    Data data = sauvegarde.data;
//                    String text = data.getInfoDataToString();
//                    if (textViewTest != null) {
//                        textViewTest.setText(text);
//                    }
//
//                    if(MaximeToolsLayoutV1.fileDownload.delete()){
//                        MaximeToolsLayoutV1.fileDownload = null;
//                    }

                }
            }
        };
        context.registerReceiver(MaximeToolsLayoutV1.broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


    }

    private static String getMimeType(Context context, Uri uri) {

        ContentResolver resolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));

    }

    public static File fileCreateOverRideMethods(String pathOfFile) {

        File myFile;


//        File myFile = new File("/data/data/fr.maximeproduction.boursemaxapp/files/" + nameOfFIleWithExtension); same as below
        myFile = new File(pathOfFile);
        if (myFile.exists()) {
            Log.i(TAG, myFile.getName() + " already exist.");
            Log.i(TAG, myFile.getPath());
            Log.i(TAG, myFile.length() + " Bytes");
            return myFile;
        } else {
            Log.i(TAG, myFile.getName() + " don't exist.");

        }
        fIleINFOLITEMethods(myFile);

        return null;

    }

    public static File fileCreateMethods(String pathOfFile) {

        File myFile = null;

        try {

//        File myFile = new File("/data/data/fr.maximeproduction.boursemaxapp/files/" + nameOfFIleWithExtension); same as below
            myFile = new File(pathOfFile);
            if (myFile.createNewFile()) {
                Log.i(TAG, myFile.getName() + " created.");
            } else {
                Log.i(TAG, myFile.getName() + " already exist.");
            }
            fIleINFOLITEMethods(myFile);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return myFile;

    }

    public static File fileCreateMethodsInFilesDirectory(Context context, String nameOfFileWithExtension) {

        File myFile = null;

        try {

//        File myFile = new File("/data/data/fr.maximeproduction.boursemaxapp/files/" + nameOfFIleWithExtension); same as below
            myFile = new File(context.getFilesDir().getPath() + "/" + nameOfFileWithExtension);
            if (myFile.createNewFile()) {
                Log.i(TAG, myFile.getName() + " created.");
            } else {
                Log.i(TAG, myFile.getName() + " already exist.");
            }
            fIleINFOLITEMethods(myFile);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return myFile;

    }

    public static void fileWriteMethod(File fileInToWrite, String textToWrite) {


        try {

            FileWriter myWriter = new FileWriter(fileInToWrite.getPath());
            myWriter.write(textToWrite);
            myWriter.close();
            Log.i(TAG, "Successfully wrote <" + textToWrite + "> to the file : " + fileInToWrite.getName());
            fIleINFOLITEMethods(fileInToWrite);

        } catch (IOException e) {
            Log.i(TAG, "ERROR to write to the file : " + fileInToWrite.getName());
            e.printStackTrace();
        }


    }

    public static String fileReadMethod(File fileToRead) {

        StringBuilder dataRead = new StringBuilder();

        try {
            Scanner myReader = new Scanner(fileToRead);
            while (myReader.hasNextLine()) {
                dataRead.append(myReader.nextLine());
            }
            myReader.close();


        } catch (FileNotFoundException e) {
            Log.i(TAG, "ERROR to read the file : " + fileToRead.getName());
            e.printStackTrace();
        }

        return dataRead.toString();

    }

    public static void filesMoving(File fileSource, File fileTarget) {

        Log.i(TAG, "\n\n\t --- MOVING file : --- ");
        File sourceLocation = fileSource;
        Log.i(TAG, " Source move file : " + sourceLocation.getName() + " WritePermission : " + sourceLocation.canWrite());
        Log.i(TAG, " Source move file : " + sourceLocation.getName() + " ReadPermission : " + sourceLocation.canRead());
        File targetLocation = fileTarget;
        Log.i(TAG, " Target move file : " + targetLocation.getName() + " WritePermission : " + targetLocation.canWrite());
        Log.i(TAG, " Target move file : " + targetLocation.getName() + " ReadPermission : " + targetLocation.canRead());
        if (targetLocation.renameTo(sourceLocation)) {
            Log.i(TAG, " Succeed moving files :) ");
        } else {
            Log.i(TAG, " Failed moving files :( ");
        }


    }

    private static void fIleINFOLITEMethods(File file) {
        if (file.exists()) {
            Log.i(TAG, ".");
            Log.i(TAG, "---> INFO < " + file.getName() + " >");
            try {
                Log.i(TAG, "    Path        : " + file.getCanonicalPath());
            } catch (IOException e) {
                Log.i(TAG, "exception canonicalPath : " + e.getMessage());
            }
            Log.i(TAG, "    CanRead     : " + file.canRead());
            Log.i(TAG, "    CanWrite    : " + file.canWrite());
            Log.i(TAG, "    Size        : " + file.length() + " Bytes");
            Log.i(TAG, ".");
        } else {

            Log.i(TAG, " File doesn't exist ");
        }


    }

    private static void fIleINFOMethods(File file) {
        if (file.exists()) {
            Log.i(TAG, " --- File name : " + file.getName() + " ---");
            Log.i(TAG, file.getName() + " parent path   : " + file.getParent());
            Log.i(TAG, file.getName() + " absolute path : " + file.getAbsolutePath());
            Log.i(TAG, file.getName() + " path          : " + file.getPath());
            try {
                Log.i(TAG, file.getName() + " canonicalPath : " + file.getCanonicalPath());
            } catch (IOException e) {
                Log.i(TAG, "exception canonicalPath : " + e.getMessage());
            }
            Log.i(TAG, file.getName() + " canRead        : " + file.canRead());
            Log.i(TAG, file.getName() + " canWrite       : " + file.canWrite());
            Log.i(TAG, file.getName() + " size           : " + file.length() + " Bytes");
            Log.i(TAG, file.getName() + " canExecute     : " + file.canExecute());
            Log.i(TAG, file.getName() + " isAbsolute     : " + file.isAbsolute());
            Log.i(TAG, file.getName() + " isDirectory    : " + file.isDirectory());
            Log.i(TAG, file.getName() + " isFile         : " + file.isFile());
            Log.i(TAG, file.getName() + " isHidden       : " + file.isHidden());
            Log.i(TAG, file.getName() + " getFreeSpace   : " + file.getFreeSpace());
            Log.i(TAG, file.getName() + " getTotalSpace  : " + file.getTotalSpace());
            Log.i(TAG, file.getName() + " getUsableSpace : " + file.getUsableSpace());
        } else {

            Log.i(TAG, " File doesn't exist ");
        }


    }


    //SERIALIZATION GSON :

//    public static void serializeSauvegardeFromFILE(File file, Sauvegarde save) {
//
//        // POUR G-SON SERIALIZER : Add in build.gradle(:app) ou (Module: xxx)
//        // dependencies {
//        //    implementation 'com.google.code.gson:gson:2.8.2'
//
//        Gson gson = new Gson();
//        String gsonString = gson.toJson(save);
//
//        FileOutputStream fileOutputStream;
//
//        try {
////            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(gsonString.getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static Sauvegarde deserializeSauvegardeFromFILE(File fileToDeserialize) {
//
//        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
//        // dependencies {
//        //    implementation 'com.google.code.gson:gson:2.8.2'
//
//        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
//        // dependencies {
//        //    implementation 'com.google.code.gson:gson:2.8.2'
//
//        FileInputStream fis = null;
//        try {
//            if (fileToDeserialize != null) {
//                fis = new FileInputStream(fileToDeserialize);
//                InputStreamReader isr = new InputStreamReader(fis);
//                BufferedReader bufferedReader = new BufferedReader(isr);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while (true) {
//                    try {
//                        if ((line = bufferedReader.readLine()) == null) break;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    sb.append(line);
//                }
//
//                String json = sb.toString();
//                Gson gson = new Gson();
//
//                return gson.fromJson(json, Sauvegarde.class);
//            } else {
//                return null;
//            }
//
//
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public static void serializeSauvegardeToAppFileDirectory(Context context, String filename, Sauvegarde sauvegarde) {
//
//        // Add implementation 'com.google.code.gson:gson:2.8.2' in build.gradle(Module:'nom de l'Application'.app) dependencies :
//
//        /*  POUR G-SON SERIALIZER :
//
//        In Gradle Scripts / build.gradle(Module:'nom de l'Application'.app) or write like that in upper tab : build.gradle(:app)
//
//                Add implementation 'com.google.code.gson:gson:2.8.2' in dependencies,
//
//                    dependencies {
//
//                          implementation 'com.google.code.gson:gson:2.8.2'
//
//                                  } M@x1me.M0y4 ;) */
//
//        Gson gson = new Gson();
//        String gsonString = gson.toJson(sauvegarde);
//
//        FileOutputStream fileOutputStream;
//
//        try {
//            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
//            fileOutputStream.write(gsonString.getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static Sauvegarde deserializeSauvegardeFromAppFileDirectory(Context context, String filename) {
//
//        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
//        // dependencies {
//        //    implementation 'com.google.code.gson:gson:2.8.2'
//
//        FileInputStream fis;
//        try {
//            fis = context.openFileInput(filename);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while (true) {
//                try {
//                    if ((line = bufferedReader.readLine()) == null) break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                sb.append(line);
//            }
//
//            String json = sb.toString();
//            Gson gson = new Gson();
//
//            return gson.fromJson(json, Sauvegarde.class);
//
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//            return null;
//        }
//
//    }


    // SERIALIZATION / DESERIALIZATION :

    public static void serialization(String pathAndNameOfSerializedFile, Object saveObject){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathAndNameOfSerializedFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(saveObject);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialization(String pathAndNameOfFileToDeserialized){

        Object objet = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(pathAndNameOfFileToDeserialized);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                objet = objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objet;
    }


    // My METHODS FORMAT numbers TO STRING :

    public static String getNumberToStringFormatUnitKiloMegaGigaWithOneDecimal(double d) {

        String stringToReturn;

        if (d > 999_999_999) {
            double dd = Math.floor(d / 100_000_000) / 10;
            stringToReturn = dd + " G";
        } else if (d > 999_999) {
            double dd = Math.floor(d / 100_000) / 10;
            stringToReturn = dd + " M";
        } else if (d > 999) {
            double dd = Math.floor(d / 100) / 10;
            stringToReturn = dd + " k";
        } else if (d >= 0) {
            double dd = Math.floor(d * 10) / 10;
            stringToReturn = dd + " u";
        } else {
            double dd = Math.floor(d * 10) / 10;
            stringToReturn = dd + " u";
        }

        return stringToReturn;

    }

    public static String getNumberToStringFormatUnitKiloMegaGigaWithOneDecimal(double d, String Unite, String Kilo, String Mega, String Giga) {

        String stringToReturn;

        if (d > 999_999_999) {
            double dd = Math.floor(d / 100_000_000) / 10;
            stringToReturn = dd + " " + Giga;
        } else if (d > 999_999) {
            double dd = Math.floor(d / 100_000) / 10;
            stringToReturn = dd + " " + Mega;
        } else if (d > 999) {
            double dd = Math.floor(d / 100) / 10;
            stringToReturn = dd + " " + Kilo;
        } else if (d >= 0) {
            double dd = Math.floor(d * 10) / 10;
            stringToReturn = dd + " " + Unite;
        } else {
            double dd = Math.floor(d * 10) / 10;
            stringToReturn = dd + " " + Unite;
        }

        return stringToReturn;

    }

    public static String getNumberToStringFormatDayHourMinSecond(int numberInSeconds) {

        int i = numberInSeconds;
        String stringToReturn;
        String jourString = " d ";
        String heureString = " h ";
        String minuteString = " m ";
        String secondeString = " s";

        if (i < 60) {
            stringToReturn = i + " s";
        } else if (i < 3600) {

            int minutes = i / 60;
            int secondes = i % 60;
            stringToReturn = minutes + " m " + secondes + " s";

        } else if (i < 24 * 3600) {

            int heures = i / 3600;
            int resteHeures = i % 3600;
            int minutes = resteHeures / 60;
            int secondes = resteHeures % 60;
            stringToReturn = heures + " h " + minutes + " m " + secondes + " s";

        } else {

            int jour = i / (24 * 3600);
            int resteJour = i % (24 * 3600);
            int heures = resteJour / 3600;
            int resteHeures = resteJour % 3600;
            int minutes = resteHeures / 60;
            int secondes = resteHeures % 60;
            stringToReturn = jour + " d " + heures + " h " + minutes + " m " + secondes + " s";

        }

        return stringToReturn;

    }


    // My METHODS STRING TEXTE LOG :

    public static void printLogInfo(String texteToPrintInConsole) {

        Log.i(TAG, texteToPrintInConsole);

    }


    // My METHODS Handler and Thread timer :

    private static Handler handler = new Handler();
    private static Runnable runnable;

    public interface timerInterface {
        void method();

    }

    public static void startTimerMethod(int delayMilisec, timerInterface[] timerInterface) {

        runnable = new Runnable() {
            @Override
            public void run() {
                long instantTimeStart = Clock.systemUTC().instant().toEpochMilli(); // ms since 1970
                Log.i(TAG, " START : " + instantTimeStart + " ms");
                for (int i = 0; i < timerInterface.length; i++) {
                    timerInterface[i].method();
                }
                long instantTimeEnd = Clock.systemUTC().instant().toEpochMilli();   // ms since 1970
                Log.i(TAG, " END interfaces : " + instantTimeEnd + " ms");

                long timeDelay = instantTimeEnd - instantTimeStart;                 // ms delayed
                Log.i(TAG, " Delay : " + timeDelay + " ms");

                if (delayMilisec - timeDelay > 0) {
                    handler.postDelayed(this, delayMilisec - timeDelay);
                    Log.i(TAG, " handler postDelayed : " + (delayMilisec - timeDelay) + " ms");
                } else {
                    handler.postDelayed(this, 1);
                    Log.i(TAG, " handler postDelayed : 1 ms");
                }

            }
        };
        runnable.run();

    }

    public static void stopTimerMethod() {

        handler.removeCallbacks(runnable);

    }


    // MATH :

    public static double roundNumber(double dbl, int decimal) {

        return Math.round(dbl * Math.pow(10, decimal)) / Math.pow(10, decimal);

    }

    public static double truncateNumber(double dbl, int decimal) {

        return Math.floor(dbl * Math.pow(10, decimal)) / Math.pow(10, decimal);

    }

    private static int convertDpToPixel(Context context, int yourDpMeasure) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                yourDpMeasure,
                r.getDisplayMetrics()
        );
    }


    // DATE :

    /**
     * <p><strong>DATE CALENDAIRE ACTUELLE :</strong>
     * </p><p>
     * Fonction utilisant la classe Calendar() pour lister : <strong>jour</strong> / <strong>mois</strong> / <strong>annee</strong>
     *
     * @return List'int'(Jour/Mois/Annee)
     * @author Maxime Moya
     * @see Calendar
     */
    public static List<Integer> getDateDayMonthYear() {

        List<Integer> listToReturn = new ArrayList<>();

        // peut etre utile a connaitre :
        /*
        Date date = new Date();
        int timeNow = date.getHours();
        Calendar calendrier = Calendar.getInstance(TimeZone.getDefault());
        */

        int dateDay = Calendar.getInstance().get(Calendar.DATE);
        int dateMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dateYear = Calendar.getInstance().get(Calendar.YEAR);

        listToReturn.add(dateDay);
        listToReturn.add(dateMonth);
        listToReturn.add(dateYear);
        return listToReturn;

    }

    public static String getDateDayMonthYearToString() {

        List<Integer> datelist = getDateDayMonthYear();
        String day = "";
        String month = "";
        String year = "";

        if (datelist.get(0) < 10){
            day = "0" + datelist.get(0).toString();
        }
        else {
            day = datelist.get(0).toString();
        }
        if (datelist.get(1) < 10){
            month = "0" + datelist.get(1).toString();
        }
        else {
            month = datelist.get(1).toString();
        }
        if (datelist.get(2) < 10){
            year = "0" + datelist.get(2).toString();
        }
        else {
            year = datelist.get(2).toString();
        }

        return day + " / " + month + " / " + year;

    }

    /**
     * <p><strong>HORAIRE ACTUELLE :</strong>
     * </p><p>
     * Fonction utilisant la classe Calendar() pour lister : <strong>Heure</strong> / <strong>Minute</strong> / <strong>Seconde</strong>
     *
     * @return List'int'(Heure/Minute/Seconde)
     * @author Maxime Moya
     * @see Calendar
     */
    public static List<Integer> getDateTimeHourMinSec() {

        List<Integer> listToReturn = new ArrayList<>();

        // peut etre utile a connaitre :
        /*
        Date date = new Date();
        int timeNow = date.getHours();
        Calendar calendrier = Calendar.getInstance(TimeZone.getDefault());
        */

        int dateHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int dateMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int dateSecond = Calendar.getInstance().get(Calendar.SECOND);

        listToReturn.add(dateHour);
        listToReturn.add(dateMinute);
        listToReturn.add(dateSecond);
        return listToReturn;

    }

    public static String getDateTimeHourMinSecToString() {

        List<Integer> timelist = getDateTimeHourMinSec();
        String hour = "";
        String minute = "";
        String second = "";

        if (timelist.get(0) < 10){
            hour = "0" + timelist.get(0).toString();
        }
        else {
            hour = timelist.get(0).toString();
        }
        if (timelist.get(1) < 10){
            minute = "0" + timelist.get(1).toString();
        }
        else {
            minute = timelist.get(1).toString();
        }
        if (timelist.get(2) < 10){
            second = "0" + timelist.get(2).toString();
        }
        else {
            second = timelist.get(2).toString();
        }

        return hour + " h " + minute + " min " + second + " sec";

    }


    // CREDIT :

    public static List<HashMap<String, String>> dicoKVGen(int quantityListToReturn){

        char c = 0;
        List<String> listeLettre = new ArrayList<>();

        while (c <= 127){

            listeLettre.add(String.valueOf(c));
            c++;

        }

        // -----------------------------------------

        List<String> listeNombre = new ArrayList<>();

        for (int i = 0 ; i < 1000 ; i ++){

            if (i<10){
                listeNombre.add("00" + i);
            }
            else if (i < 100){
                listeNombre.add("0" + i);
            }
            else {
                listeNombre.add(String.valueOf(i));
            }
        }

        // -----------------------------------------

        List<HashMap<String,String>> listeKV = new ArrayList<>();

        for (int j = 0 ; j < quantityListToReturn ; j++){

            Collections.shuffle(listeNombre);

            HashMap<String, String> dico = new HashMap<>();
            int i = 0;

            for (String ltrs : listeLettre){

                dico.put(ltrs, listeNombre.get(i));
                i++;

            }

            listeKV.add(dico);

        }

        return listeKV;

    }

    public static String[] hexOctBinGen(String text) {

        String[] tableau = new String[4];
        StringBuilder hex = new StringBuilder();

        for (int j = 0; j < tableau.length; j++) {

            hex = new StringBuilder();
            switch (j) {
                case 0:
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        hex.append(c);
                    }
                    tableau[j] = hex.toString();
                    break;
                case 1:
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        String result = Integer.toHexString(c);
                        hex.append(result);
                        hex.append(' ');
                    }
                    tableau[j] = hex.toString();
                    break;
                case 2:
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        String result = Integer.toOctalString(c);
                        hex.append(result);
                        hex.append(' ');
                    }
                    tableau[j] = hex.toString();
                    break;
                case 3:
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        String result = Integer.toBinaryString(c);
                        hex.append(result);
                        hex.append(' ');
                    }
                    tableau[j] = hex.toString();
                    break;
                default:
                    break;
            }

        }

        return tableau;

    }

    public static void textTobinFile(Context context, String text, String fileName){

        String textToSave = MaximeToolsLayoutV1.binGen(text);

        File monFichier = MaximeToolsLayoutV1.fileCreateMethodsInFilesDirectory(context, fileName);

        MaximeToolsLayoutV1.fileWriteMethod(monFichier, textToSave);

    }

    public static String decodeBinFileToText(File file){

        String text = MaximeToolsLayoutV1.fileReadMethod(file);

        StringBuilder stringBuilderLettre;
        StringBuilder stringBuilderGlobal = new StringBuilder();

        if(text.length() < Integer.MAX_VALUE){
            for (int i = 0 ; i < text.length() - 7 ; i +=8){

                stringBuilderLettre = new StringBuilder();

                for (int j = 0 ; j < 8 ; j++){

                    int k = i + j;

                    stringBuilderLettre.append(text.charAt(k));

                }

                int binaire = Integer.parseInt(stringBuilderLettre.toString(),2);
                Character c = (char) binaire;
                stringBuilderGlobal.append(c);

            }

            return stringBuilderGlobal.toString();

        }

        return "Too many binaries to decode > 256 Mo";
    }

    public static String binGen(String text) {

        StringBuilder bin = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);
            String result = Integer.toBinaryString(c);

            if (result.length() < 8){

                StringBuilder strbuilder = new StringBuilder();

                if (result.length() == 7){

                    strbuilder.append("0");

                    for (int j = 0 ; j < result.length() ; j++){

                     strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else if (result.length() == 6){

                    strbuilder.append("00");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else if (result.length() == 5){

                    strbuilder.append("000");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else if (result.length() == 4){

                    strbuilder.append("0000");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else if (result.length() == 3){

                    strbuilder.append("00000");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else if (result.length() == 2){

                    strbuilder.append("000000");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }
                else {

                    strbuilder.append("0000000");

                    for (int j = 0 ; j < result.length() ; j++){

                        strbuilder.append(result.charAt(j));
                    }

                    result = strbuilder.toString();

                }

            }
            bin.append(result);
        }

        return bin.toString();

    }

    private static int getDecimalFromBin(int binary){
        int decimal = 0;
        int n = 0;
        while(true){
            if(binary == 0){
                break;
            } else {
                int temp = binary%10;
                decimal += temp*Math.pow(2, n);
                binary = binary/10;
                n++;
            }
        }
        return decimal;
    }

    /*
    My PowerLibrary.
    Cre4ted By : M@xim3 M0y4 ;)
    # 4d.61.78.69.6d.65.20.4d.6f.79.61.20.3b.29.
    # 115.141.170.151.155.145.40.115.157.171.141.40.73.51.
    # 01001101.01100001.01111000.01101001.01101101.01100101.
    # 00100000.
    # 01001101.01101111.01111001.01100001.
    # 00100000.
    # 00111011.00101001.
     */

}
