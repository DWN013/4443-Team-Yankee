package com.example.breadcrumbsettings;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.breadcrumbsettings.model.SettingsItem;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_BUTTON = 1;

    private List<SettingsItem> settingsList;
    private OnItemClickListener onItemClickListener;
    private boolean isBlack = false;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public SettingsAdapter(List<SettingsItem> settingsList) {
        this.settingsList = settingsList;
    }

    @Override
    public int getItemViewType(int position) {
        // If position is equal to the size of the list, it means it's the last item (button)
        if (position == settingsList.size()) {
            return VIEW_TYPE_BUTTON;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings, parent, false);
            return new ViewHolder(view, onItemClickListener);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_button, parent, false);
            return new ButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_ITEM) {
            SettingsItem currentItem = settingsList.get(position);
            ((ViewHolder) holder).icon.setImageResource(currentItem.getIconResId());
            ((ViewHolder) holder).title.setText(currentItem.getTitle());
            ((ViewHolder) holder).subtitle.setText(currentItem.getSubtitle());
        } else {
            ((ButtonViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the list plus one for the button
        return settingsList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title, subtitle;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder {
        Button changeColorButton;

        ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            changeColorButton = itemView.findViewById(R.id.change_breadcrumb_color_button);

            changeColorButton.setOnClickListener(v -> {
                Drawable breadcrumbBackground = ContextCompat.getDrawable(itemView.getContext(), R.drawable.breadcrumb_background);
                if (breadcrumbBackground instanceof GradientDrawable) {
                    if (isBlack) {
                        ((GradientDrawable) breadcrumbBackground).setColor(0xFF6200EE); // Use the color code directly
                        changeColorButton.setText("Change Breadcrumb Color to Black");
                    } else {
                        ((GradientDrawable) breadcrumbBackground).setColor(0xFF000000); // Use black color code
                        changeColorButton.setText("Change Breadcrumb Color to Purple");
                    }
                    isBlack = !isBlack;
                }
                // Reload the breadcrumbs fragment to update the color
                if (itemView.getContext() instanceof MainSettingsActivity) {
                    ((MainSettingsActivity) itemView.getContext()).showBreadcrumbsFragment();
                }
            });
        }

        void bind() {
            // No binding needed for the button
        }
    }
}