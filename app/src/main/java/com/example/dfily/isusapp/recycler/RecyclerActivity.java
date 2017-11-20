package com.example.dfily.isusapp.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dfily.isusapp.R;
import com.example.dfily.isusapp.dialogs.YesNoClicker;
import com.example.dfily.isusapp.dialogs.YesNoDialog;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements RecyclerClicker {

    private List<Worker> workersList;
    private WorkerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        workersList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            workersList.add(new Worker(i, "Worker" + i, String.valueOf(i * 20)));
        }

        mAdapter = new WorkerAdapter(workersList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(Worker worker) {
        YesNoDialog dialog = new YesNoDialog();

        YesNoClicker clicker = new YesNoClicker() {
            @Override
            public void yesClick() {
                workersList.remove(worker);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void noClick() {
            }
        };

        dialog.setClicker(clicker);

        dialog.show(getSupportFragmentManager(), "dialog");
    }

}
