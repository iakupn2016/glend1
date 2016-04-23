package iakupn.iakupn2016.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iakupn.iakupn2016.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectUserFragment extends Fragment {


    public SelectUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_user, container, false);
    }


}
