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
        void onAdd(String serverName);
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


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnAddListener) {
            onAddListener = (OnAddListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnAddListener");
        }
    }

    private void onClick() {
        EditText serverName = serverBottomSheetBinding.tilName.getEditText();
        EditText serverUrl = serverBottomSheetBinding.tilUrl.getEditText();
        EditText serverPort = serverBottomSheetBinding.tilPort.getEditText();

        String name = serverName.getText().toString();
        String url = serverUrl.getText().toString();
        String port = serverPort.getText().toString();

        if (name.trim().isEmpty()) {
            serverBottomSheetBinding.tilName.setError("Please fill in the server name");
            return;
        }

        if (url.trim().isEmpty() || port.trim().isEmpty()) {
            serverBottomSheetBinding.tilUrl.setError("Please fill in the URL");
            serverBottomSheetBinding.tilPort.setError("Please fill in the port");
            return;
        }

        // If all fields are filled, add the server
        onAddListener.onAdd(name);
        dismiss();
    }

}
