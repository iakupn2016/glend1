package iakupn.iakupn2016.ui.fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import iakupn.iakupn2016.R;
import iakupn.iakupn2016.db.DbHelper;
import iakupn.iakupn2016.db.DbSchema;
import iakupn.iakupn2016.ui.RegistrationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateUserFragment extends Fragment {
    private OnCreateUserInteraction listener;
    private EditText personName;
    private EditText personAddress;
    private EditText personAge;
    private EditText personIsMarried;
    private Button saveBtn;

    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        String username = bundle.getString(RegistrationActivity.USERNAME);
        int age = bundle.getInt(RegistrationActivity.AGE);

        Log.d("TAG", "username " + username + ". Age " + age);

        dbHelper = new DbHelper(getActivity());
        db = dbHelper.getWritableDatabase();

        queryToDb();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnCreateUserInteraction) activity;
        } catch (Exception e) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listener.onInteraction();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personName = (EditText) view.findViewById(R.id.person_name);
        personAddress = (EditText) view.findViewById(R.id.person_address);
        personAge = (EditText) view.findViewById(R.id.person_age);
        personIsMarried = (EditText) view.findViewById(R.id.person_is_married);

        saveBtn = (Button) view.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDb();
            }
        });
    }

    private void saveToDb() {
        ContentValues values = new ContentValues();
        values.put(DbSchema.COLUMN_PERSON_NAME, personName.getText().toString());
        values.put(DbSchema.COLUMN_PERSON_ADDRESS, personAddress.getText().toString());
        values.put(DbSchema.COLUMN_PERSON_AGE, personAge.getText().toString());
        values.put(DbSchema.COLUMN_PERSON_IS_MARRIED, personIsMarried.getText().toString());

        db.insert(DbSchema.PERSON_TABLE_NAME, null, values);
    }

    private void queryToDb() {

        String[] projection = new String[] {
                DbSchema.COLUMN_PERSON_NAME,
                DbSchema.COLUMN_PERSON_ADDRESS,
                DbSchema.COLUMN_PERSON_AGE,
                DbSchema.COLUMN_PERSON_IS_MARRIED,
        };

        String selection = DbSchema.COLUMN_PERSON_AGE + ">? AND "
                + DbSchema.COLUMN_PERSON_IS_MARRIED + "=?";
        String[] selectionArgs = new String[] {
                String.valueOf(10),
                String.valueOf(1)
        };

        Cursor cursor = db.query(DbSchema.PERSON_TABLE_NAME, projection, selection,
                selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("TAG", "Nama saya "
                        + cursor.getString(0) + ". Alamat saya "
                        + cursor.getString(1) + ". Umur saya " + cursor.getInt(2));
            }
        }
    }

    public interface OnCreateUserInteraction {
        void onInteraction();
    }

}
