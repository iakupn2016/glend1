package iakupn.iakupn2016.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import iakupn.iakupn2016.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String DATA_MAIN_ACTIVITY = "data_main_activity";
    public static final String DATA_INT_MAIN_ACTIVITY = "data_integer_main_activity";

    private Button saveBtn;
    private Button removeBtn;
    private ImageView logoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Berada di on create");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Berada di on start");

        saveBtn = (Button) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Button di klik", Toast.LENGTH_SHORT).show();
                launchMyFormActivity("Dipanggil dari button save", 500);
            }
        });

        logoImg = (ImageView) findViewById(R.id.logo_image);
        logoImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Gambar di klik lama", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        removeBtn = (Button) findViewById(R.id.remove_btn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int visibility = logoImg.getVisibility();
//                if (visibility == View.VISIBLE) {
//                    logoImg.setVisibility(View.GONE);
//                    removeBtn.setText("Munculkan Gambar");
//                } else {
//                    logoImg.setVisibility(View.VISIBLE);
//                    removeBtn.setText("Hilangkan Gambar");
//                }
                launchMyFormActivity("Dipanggil dari remove button", 100);
            }
        });
    }

    private void launchMyFormActivity(String txt, int val) {
        Intent intent = new Intent(this, MyForm.class);
        intent.putExtra(DATA_MAIN_ACTIVITY, txt);
        intent.putExtra(DATA_INT_MAIN_ACTIVITY, val);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "Berada di on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "Berada di on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "Berada di on stop");

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Berada di on destroy");
        super.onDestroy();
    }
}
