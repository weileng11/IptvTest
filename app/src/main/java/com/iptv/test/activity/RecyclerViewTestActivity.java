package com.iptv.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iptv.test.R;
import com.iptv.test.recycleview.DividerItemDecoration;

/**
 * classes:com.iptv.test.activity.RecyclerViewTestActivity
 *
 * @author lt
 * @date 2016/6/8
 * @time 16:16
 * @description  RecyclerView 测试
 */
public class RecyclerViewTestActivity extends FragmentActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        String [] myDataset = {"Android","ios","jack","tony","window","mac","1234","hehe","495948"};

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //设置RecycleView的显示方向：（默认为垂直） 水平
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        // Provide a reference to the type of views that you are using
        // (custom viewholder)
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycl_item, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder((TextView) v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
