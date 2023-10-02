package com.tungngt.dev.ui.swipehandler;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperListener{
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
}
