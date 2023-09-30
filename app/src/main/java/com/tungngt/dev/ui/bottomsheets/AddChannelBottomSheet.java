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
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.AddChannelBottomsheetBinding;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;
import com.tungngt.dev.ui.adapter.ChannelAdapter;
import com.tungngt.dev.ui.fragment.ChannelFragment;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

public class AddChannelBottomSheet extends BottomSheetDialogFragment  {
    public AddChannelBottomsheetBinding bottomSheetBinding;

    public static String TAG = "ModelBottomSheet";
    public interface OnAddListener {
        void onAdd(String channelName, String channel_Desc, String channel_custom_text);
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnAddListener) {
            onAddListener = (OnAddListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnAddListener");
        }
    }
    private void onClick() {
        EditText channelName_1 = bottomSheetBinding.tilName.getEditText();
        EditText channelDescription = bottomSheetBinding.tilDescription.getEditText();
        EditText channelCustomText = bottomSheetBinding.tilCustom.getEditText();

        String name = channelName_1.getText().toString();
        String description = channelDescription.getText().toString();
        String custom_text = channelCustomText.getText().toString();

        if (name.trim().isEmpty()) {
            bottomSheetBinding.tilName.setError("Please fill in the channel name");
            return;
        }
        if (description.trim().isEmpty()) {
            bottomSheetBinding.tilDescription.setError("Please fill in the channel description");
            return;
        }
        if( custom_text.trim().isEmpty()){
            bottomSheetBinding.tilCustom.setError("Please fill in the channel custom field");
            return;
        }
        onAddListener.onAdd(name, description, custom_text);

        dismiss();
    }


}
