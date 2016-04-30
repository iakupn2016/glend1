package iakupn.iakupn2016.ui;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import iakupn.iakupn2016.R;
import iakupn.iakupn2016.receivers.NetworkChangeReceiver;
import iakupn.iakupn2016.services.MyService;
import iakupn.iakupn2016.ui.fragments.CreateUserFragment;
import iakupn.iakupn2016.ui.fragments.SelectUserFragment;

public class RegistrationActivity extends AppCompatActivity implements CreateUserFragment.OnCreateUserInteraction {

    public static final String USERNAME = "username";
    public static final String AGE = "age";

    private BroadcastReceiver networkReceiver = new NetworkChangeReceiver();

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
    }

    @Override
    protected void onStart() {

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0; i<100; i++) {
//                    Log.d("LOOP", "Looping ke " + i);
//                }
//            }
//        });
//        t.start();

        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{100});

        Log.d("MAIN", "Landing here");

        launchService();

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                launchService();
            }
        }, 5000);

        registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkReceiver);
        super.onStop();
    }

    private void launchService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    @Override
    public void onInteraction() {
        Log.d("TAG", "Interaksi dengan create user fragment");
    }

    public class MyTask extends AsyncTask<Integer, String, Boolean> {
        @Override
        protected Boolean doInBackground(Integer... params) {
            try {
                int until = params[0];

                for (int i=0; i<until; i++) {
                    Log.d("LOOP", "Looping ke " + i);

//                    if (i==50) throw new Exception();
                }

                return true;

            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            Log.d("READY", "Akan diproses");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Log.d("RESULT", "Proses berhasil");
            } else {
                Log.d("RESULT", "Proses gagal");
            }
        }
    }
}
