package iakupn.iakupn2016.ui;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import iakupn.iakupn2016.R;
import iakupn.iakupn2016.receivers.NetworkChangeReceiver;
import iakupn.iakupn2016.services.MyService;
import iakupn.iakupn2016.ui.fragments.CreateUserFragment;
import iakupn.iakupn2016.ui.fragments.SelectUserFragment;

public class RegistrationActivity extends AppCompatActivity
        implements CreateUserFragment.OnCreateUserInteraction,
GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    public static final String USERNAME = "username";
    public static final String AGE = "age";
    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private BroadcastReceiver networkReceiver = new NetworkChangeReceiver();

    private LinearLayout layoutGesture;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Fragment createUserFragment = new CreateUserFragment();

        Bundle args = new Bundle();
        args.putString(USERNAME, "My username");
        args.putInt(AGE, 40);
        createUserFragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_1, createUserFragment).commit();

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_2, new SelectUserFragment()).commit();

        gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    public void onInteraction() {
        Log.d("TAG", "Interaksi dengan create user fragment");
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0; i<=100; i++) {
//                    Log.d(TAG, "Looping ke " + i);
//                }
//            }
//        });
//        t.start();

        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{100});

        Log.d(TAG, "Finish here");

        launchService();

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                launchService();
            }
        }, 5000);

        registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(networkReceiver, new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED"));


        launchNotification();

        layoutGesture = (LinearLayout) findViewById(R.id.layout_gesture);
        layoutGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "On action up");
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "On action down");
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "On action move");
                        Log.d(TAG, "Moved to " + event.getX() + " - " + event.getY());

                        return true;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d(TAG, "On action outside");
                        return true;
                    default:
                        Log.d(TAG, "On other action");
                        return true;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "On down gesture listener");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "On show press listener");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "On down gesture single tap up");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "On scroll gesture listener");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "On long press gesture listener");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "On fling gesture listener");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, "On single tap confirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "On double tap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, "On double tap event");
        return false;
    }

    private void launchNotification() {
        // create pending intent
        Intent intent = new Intent(this, MyForm.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        // create notification builder to construct our notif
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_assignment_ind_white_24dp)
                .setContentTitle("Notification Title")
                .setContentText("Notification Content Text")
                .addAction(R.drawable.ic_assignment_ind_white_24dp, "Action 1", pendingIntent)
                .addAction(R.drawable.ic_assignment_ind_white_24dp, "Action 2", pendingIntent)
                .addAction(R.drawable.ic_assignment_ind_white_24dp, "Action 3", pendingIntent)
                .setAutoCancel(true);
        // set pending intent
        notificationBuilder.setContentIntent(pendingIntent);

        int notificationId = 1000;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(networkReceiver);
    }

    private void launchService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public class MyTask extends AsyncTask<Integer, String, Boolean> {
        @Override
        protected Boolean doInBackground(Integer... params) {
            try {
                for (int param : params) {
                    for (int i=0; i<=param; i++) {
                        Log.d(TAG, "Looping ke " + i);
                        if (i==50) throw new Exception();
                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "Akan memproses");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Log.d(TAG, "Proses berhasil");
            } else {
                Log.d(TAG, "Proses gagal");
            }
        }
    }
}
