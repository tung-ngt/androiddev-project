package com.tungngt.dev.ui.fragment;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import com.tungngt.dev.ui.activity.MainActivity;
import com.tungngt.dev.R;

import com.tungngt.dev.databinding.FragmentPeopleBinding;

public class SearchFragment extends Fragment {

    private MainActivity m;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        m = (MainActivity) getActivity();

        // Allow to go back

        // Disable drawer
        m.disableDrawer();

        // Request search focus
        EditText searchEditText = view.findViewById(R.id.Search);
        searchEditText.requestFocus();
        searchEditText.setFocusableInTouchMode(true);
        m.showKeyboard();



        return view;
    }
}