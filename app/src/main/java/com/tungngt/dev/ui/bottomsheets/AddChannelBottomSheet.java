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
import com.tungngt.dev.databinding.AddChannelBottomsheetBinding;

public class AddChannelBottomSheet extends BottomSheetDialogFragment {
    public AddChannelBottomsheetBinding bottomSheetBinding;

    public static String TAG = "ModelBottomSheet";

    public interface OnAddListener {
        void onAdd(String channelName);
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
        bottomSheetBinding = AddChannelBottomsheetBinding.inflate(inflater);

        bottomSheetBinding.btnAddChannel.setOnClickListener(view -> {
            onClick();
        });
        return bottomSheetBinding.getRoot();
    }

    private void onClick() {
        EditText channelName = bottomSheetBinding.tilName.getEditText();
        assert channelName != null;
        String name = channelName.getText().toString();
        onAddListener.onAdd(name);
        dismiss();
    }
}
