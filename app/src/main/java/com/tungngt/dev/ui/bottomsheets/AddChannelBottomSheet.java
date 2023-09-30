package com.tungngt.dev.ui.bottomsheets;

import android.content.res.ColorStateList;
import android.os.Bundle;
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
import com.tungngt.dev.databinding.AddChannelBottomsheetBinding;

public class AddChannelBottomSheet extends BottomSheetDialogFragment  {
    public AddChannelBottomsheetBinding bottomSheetBinding;

    public static String TAG = "ModelBottomSheet";
    public interface OnAddListener {
        void onAdd(String handle, String name, Integer color, String description);
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

        bottomSheetBinding.btnAddChannel.setOnClickListener(view -> onClick());

        bottomSheetBinding.colorPicker.setOnClickListener(this::pickColor);

        bottomSheetBinding.tilDescription.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                pickColor(bottomSheetBinding.colorPicker);
            }
            return true;
        });

        return bottomSheetBinding.getRoot();
    }

    private void pickColor(View colorView) {
        ColorPickerDialog.Builder builder =  new ColorPickerDialog.Builder(
                getContext()
        );

        ColorPickerView colorPickerView = builder.getColorPickerView();
        colorPickerView.setInitialColor(colorView.getBackgroundTintList().getDefaultColor());

        builder.setTitle(getString(R.string.choose_color))
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> colorView.setBackgroundTintList(
                        ColorStateList.valueOf(colorPickerView.getColor())
                ))
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .attachAlphaSlideBar(false)
                .setIcon(R.drawable.baseline_color_lens_24)
                .attachBrightnessSlideBar(true).show();

    }

    private void onClick() {
        EditText channelName_1 = bottomSheetBinding.tilName.getEditText();
        EditText channelDescription = bottomSheetBinding.tilDescription.getEditText();
        EditText channelCustomText = bottomSheetBinding.tilCustom.getEditText();
        Integer color = bottomSheetBinding.colorPicker.getBackgroundTintList().getDefaultColor();


        String handle = channelName_1.getText().toString();
        String description = channelDescription.getText().toString();
        String name = channelCustomText.getText().toString();

        if (handle.trim().isEmpty()) {
            bottomSheetBinding.tilName.setError("Please fill in the channel name");
            return;
        }
        if (description.trim().isEmpty()) {
            bottomSheetBinding.tilDescription.setError("Please fill in the channel description");
            return;
        }
        if(name.trim().isEmpty()){
            bottomSheetBinding.tilCustom.setError("Please fill in the channel custom field");
            return;
        }

        onAddListener.onAdd(handle, name, color, description);
        dismiss();
    }


}
