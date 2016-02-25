package edu.uw.style;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class MainActivity extends Activity {

    public static final String TAG = "**DEMO**";

    private int mClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSecondActivity(View v) {
        Log.i(TAG, "Launch button pressed");

        //Explicit intent
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("edu.uw.uidemo.message", "Hello number 2!");
        startActivity(intent);
    }

    public void callNumber(View v) {
        Log.i(TAG, "Call button pressed");

        //implicit intent
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:206-685-1622"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private static final int REQUEST_PICTURE_CODE = 1;

    public void takePicture(View v) {
        Log.i(TAG, "Camera button pressed");

        //implicit intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICTURE_CODE);
        }
    }

    public void sendMessage(View v) {
        Log.i(TAG, "Message button pressed");
        //...
    }

    private static final int CLICK_NOTIFICATION_ID = 0;

    public void clickerPressed(View v) {
        Log.i(TAG, "Clicker button pressed");
//        ActionBar actionBar = getSupportActionBar(); //for reference
//        actionBar.hide();

        /** Show notification **/
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name2)
                        .setContentTitle("Click Counting")
                        .setContentText("I work??")
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(CLICK_NOTIFICATION_ID, mBuilder.build());
    }

    /** Menus **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item1 :
                callNumber(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
