package com.example.dfily.isusapp.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dfily.isusapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Worker> myDataset = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            myDataset.add(new Worker(i, "Worker" + i, String.valueOf(i*20)));
        }

        RecyclerView.Adapter mAdapter = new WorkerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}
