package com.tungngt.dev.ui.bottomsheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tungngt.dev.databinding.AddPersonBottomsheetBinding;

public class AddPersonBottomSheet extends BottomSheetDialogFragment {
    public AddPersonBottomsheetBinding bottomsheetBinding;

    public static String TAG = "ModelBottomSheet";

    public interface OnAddListener {
        void onAdd(String name, Integer age);
    }

    private OnAddListener onAddListener;


    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bottomsheetBinding = AddPersonBottomsheetBinding.inflate(inflater);

        bottomsheetBinding.btnAddPerson.setOnClickListener(view -> {
            onClick();
        });

        EditText etAge = bottomsheetBinding.tilAge.getEditText();
        assert etAge != null;
        etAge.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClick();
                return true;
            }
            return false;
        });
        return bottomsheetBinding.getRoot();
    }

    private void onClick() {
        EditText etName = bottomsheetBinding.tilName.getEditText();
        EditText etAge = bottomsheetBinding.tilAge.getEditText();
        assert etName != null;
        assert etAge != null;
        String name = etName.getText().toString();
        Integer age = Integer.parseInt(etAge.getText().toString());
        onAddListener.onAdd(name, age);
        dismiss();
    }
}
