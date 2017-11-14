package com.example.dfily.isusapp.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dfily.isusapp.R;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private List<Worker> workerList;

    public WorkerAdapter(List<Worker> workerList) {
        this.workerList = workerList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WorkerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkerAdapter.ViewHolder holder, int position) {
        // Обновляем элемент в зависимости от данных в массиве
        Worker worker = workerList.get(position);
        holder.idTV.setText(String.valueOf(worker.getId()));
        holder.nameTV.setText(worker.getName());
        holder.distanceTV.setText(new StringBuilder(worker.getDistance() + " km"));
        holder.itemView.setOnClickListener(view -> {
            Log.d("mylogs", worker.getName());
        });
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView idTV;
        TextView nameTV;
        TextView distanceTV;

        ViewHolder(View itemView) {
            super(itemView);
            idTV = (TextView) itemView.findViewById(R.id.idTV);
            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            distanceTV = (TextView) itemView.findViewById(R.id.distanceTV);
        }
    }

}
