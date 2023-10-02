package com.tungngt.dev.ui.swipehandler;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.R;
import com.tungngt.dev.ui.adapter.MainRecyclerViewAdapter;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback{
    private ItemTouchHelperListener listener;
    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            listener.onSwiped(viewHolder, direction);
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
//            View foregroundView = ((MainRecyclerViewAdapter.MainItemViewHolder) viewHolder).layoutForeground;
//            getDefaultUIUtil().onSelected(foregroundView);

        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((MainRecyclerViewAdapter.MainItemViewHolder) viewHolder).layoutForeground;
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX/4, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((MainRecyclerViewAdapter.MainItemViewHolder) viewHolder).layoutForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX/4, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foregroundView = ((MainRecyclerViewAdapter.MainItemViewHolder) viewHolder).layoutForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }
}
