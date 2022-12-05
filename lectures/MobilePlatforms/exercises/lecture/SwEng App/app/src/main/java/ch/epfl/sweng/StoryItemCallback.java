package ch.epfl.sweng;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public final class StoryItemCallback extends DiffUtil.ItemCallback<Story> {

    @Override
    public boolean areItemsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
        return oldItem.equals(newItem);
    }
}
