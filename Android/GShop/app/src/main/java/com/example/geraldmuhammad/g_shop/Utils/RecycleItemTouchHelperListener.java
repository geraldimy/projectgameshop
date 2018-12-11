package com.example.geraldmuhammad.g_shop.Utils;

import android.support.v7.widget.RecyclerView;

public interface RecycleItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
