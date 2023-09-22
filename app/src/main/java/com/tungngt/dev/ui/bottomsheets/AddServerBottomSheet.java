package com.tungngt.dev.ui.bottomsheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tungngt.dev.databinding.AddServerBottomsheetBinding;

public class AddServerBottomSheet extends BottomSheetDialogFragment {
    public AddServerBottomsheetBinding serverBottomSheetBinding;

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
        serverBottomSheetBinding = AddServerBottomsheetBinding.inflate(inflater);

        serverBottomSheetBinding.btnAddServer.setOnClickListener(view -> {
            onClick();
        });
        return serverBottomSheetBinding.getRoot();
    }

    private void onClick() {
        EditText serverName = serverBottomSheetBinding.tilName.getEditText();
        assert serverName != null;
        String name = serverName.getText().toString();
        onAddListener.onAdd(name);
        dismiss();
    }
}
