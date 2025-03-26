package com.example.breadcrumbsettings.breadcrumbs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.breadcrumbsettings.R;
import java.util.Stack;

public class BreadcrumbsAdapter extends RecyclerView.Adapter<BreadcrumbsAdapter.BreadcrumbViewHolder> {
    private Stack<String> breadcrumbs;
    private BreadcrumbClickListener listener;

    public BreadcrumbsAdapter(BreadcrumbClickListener listener) {
        this.breadcrumbs = new Stack<>();
        this.listener = listener;
    }

    public void setBreadcrumbs(Stack<String> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BreadcrumbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breadcrumb_item, parent, false);
        return new BreadcrumbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreadcrumbViewHolder holder, int position) {
        holder.breadcrumbTextView.setText(breadcrumbs.get(position));
        holder.breadcrumbTextView.setOnClickListener(v -> listener.onBreadcrumbClick(position));
    }

    @Override
    public int getItemCount() {
        return breadcrumbs.size();
    }

    static class BreadcrumbViewHolder extends RecyclerView.ViewHolder {
        TextView breadcrumbTextView;

        public BreadcrumbViewHolder(@NonNull View itemView) {
            super(itemView);
            breadcrumbTextView = itemView.findViewById(R.id.breadcrumb_text);
        }
    }

    public interface BreadcrumbClickListener {
        void onBreadcrumbClick(int position);
    }
}