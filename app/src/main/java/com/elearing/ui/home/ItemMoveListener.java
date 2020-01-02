package com.elearing.ui.home;

public interface ItemMoveListener {
    boolean onItemMove(int fromPosition, int toPosition);
    boolean onItemRemove(int position);
}