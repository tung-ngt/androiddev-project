package com.tungngt.dev.ui.bottomsheets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.AddServerBottomsheetBinding;

public class AddServerBottomSheet extends BottomSheetDialogFragment {
    public AddServerBottomsheetBinding serverBottomSheetBinding;

    public static String TAG = "ModelBottomSheet";

    public interface OnAddListener {
        void onAdd( String url, Integer port, String serverName, Integer color);
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

        serverBottomSheetBinding.colorPicker.setOnClickListener(this::pickColor);

        serverBottomSheetBinding.tilPort.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                pickColor(serverBottomSheetBinding.colorPicker);
            }
            return true;
        });
        return serverBottomSheetBinding.getRoot();
    }

    private void pickColor(View colorView) {
        ColorPickerDialog.Builder builder =  new ColorPickerDialog.Builder(
                getContext()
        );

        ColorPickerView colorPickerView = builder.getColorPickerView();
        colorPickerView.setInitialColor(colorView.getBackgroundTintList().getDefaultColor());

        builder.setTitle(getString(R.string.choose_color))
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
                    colorView.setBackgroundTintList(
                            ColorStateList.valueOf(colorPickerView.getColor())
                    );
                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                    dialog.dismiss();

                })
                .attachAlphaSlideBar(false)
                .setIcon(R.drawable.baseline_color_lens_24)
                .attachBrightnessSlideBar(true).show();

    }

    private void onClick() {
        EditText serverName = serverBottomSheetBinding.tilName.getEditText();
        EditText serverUrl = serverBottomSheetBinding.tilUrl.getEditText();
        EditText serverPort = serverBottomSheetBinding.tilPort.getEditText();
        Integer color = serverBottomSheetBinding.colorPicker.getBackgroundTintList().getDefaultColor();

        String name = serverName.getText().toString();
        String url = serverUrl.getText().toString();

        if (name.trim().isEmpty()) {
            serverBottomSheetBinding.tilName.setError("Please fill in the server name");
            return;
        }

        if (url.trim().isEmpty()) {
            serverBottomSheetBinding.tilUrl.setError("Please fill in the URL");
            return;
        }

        if (serverPort.getText().toString().trim().isEmpty()) {
            serverBottomSheetBinding.tilPort.setError("Please fill in the port");
            return;
        }

        Integer port = Integer.valueOf(serverPort.getText().toString());

        // If all fields are filled, add the server
        onAddListener.onAdd(url, port, name, color);
        dismiss();
    }
}
