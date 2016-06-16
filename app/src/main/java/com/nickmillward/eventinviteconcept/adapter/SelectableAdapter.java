package com.nickmillward.eventinviteconcept.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 6/16/16.
 *
 * Generic Adapter for selected items
 * Provides a list of selected elements, change the selected state of a given element
 * Also clears all selected items
 */
public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    // https://developer.android.com/reference/android/util/SparseBooleanArray.html
    private SparseBooleanArray selectedItems;

    public SelectableAdapter(SparseBooleanArray selectedItems) {
        selectedItems = new SparseBooleanArray();
    }

    /**
     * Indicates if the item at a given position is selected
     * @param position of the item to check
     * @return true if the item is selected
     */
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    /**
     * Toggle the selection status of the item at a given position
     * @param position of the item to toggle the selection
     */
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    /**
     * Clear the selection status of all items
     */
    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    /**
     * Count the selected items
     * @return Selected item count
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * Indicates the list of seleted items
     * @return List of selected ids
     */
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }

        return items;

    }
}
