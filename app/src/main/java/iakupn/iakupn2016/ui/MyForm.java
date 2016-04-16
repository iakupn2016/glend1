package iakupn.iakupn2016.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import iakupn.iakupn2016.R;

public class MyForm extends AppCompatActivity {

    private static final String TAG = MyForm.class.getSimpleName();
    private String dataMainActivity;
    private int dataIntMainActivity;
    private TextView dataTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);
        Log.d(TAG, "Jalankan On create");

        dataMainActivity = getIntent().getStringExtra(MainActivity.DATA_MAIN_ACTIVITY);
        dataIntMainActivity = getIntent().getIntExtra(MainActivity.DATA_INT_MAIN_ACTIVITY, 1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        dataTxt = (TextView) findViewById(R.id.data_txt);
        dataTxt.setText(dataMainActivity + " - " + dataIntMainActivity);
    }
}
