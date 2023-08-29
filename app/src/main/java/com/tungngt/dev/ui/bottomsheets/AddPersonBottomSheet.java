package com.tungngt.dev.ui.bottomsheets;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tungngt.dev.databinding.AddPersonBottomsheetBinding;

public class AddPersonBottomSheet extends BottomSheetDialog {
    public AddPersonBottomsheetBinding bottomsheetBinding;

    public interface OnAddListener {
        void onAdd(String name, Integer age);
    }

    private OnAddListener onAddListener;

    public AddPersonBottomSheet(@NonNull Context context) {
        super(context);
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomsheetBinding = AddPersonBottomsheetBinding.inflate(getLayoutInflater());
        setContentView(bottomsheetBinding.getRoot());

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
