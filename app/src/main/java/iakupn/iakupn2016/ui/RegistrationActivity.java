package iakupn.iakupn2016.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import iakupn.iakupn2016.R;
import iakupn.iakupn2016.ui.fragments.CreateUserFragment;
import iakupn.iakupn2016.ui.fragments.SelectUserFragment;

public class RegistrationActivity extends AppCompatActivity implements CreateUserFragment.OnCreateUserInteraction {

    public static final String USERNAME = "username";
    public static final String AGE = "age";

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
    public void onInteraction() {
        Log.d("TAG", "Interaksi dengan create user fragment");
    }
}
