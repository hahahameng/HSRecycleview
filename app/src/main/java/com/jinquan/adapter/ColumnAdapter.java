package com.jinquan.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinquan.entity.ColumnEntity;
import com.jinquan.hsrecycleview.R;

import java.util.List;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.VH> {


    private List<ColumnEntity> mLists;
    private Context mContext;
    private String TAG = "ColumnAdapter";

    public ColumnAdapter(Context context, List<ColumnEntity> columnEntityList) {
        mContext = context;
        mLists = columnEntityList;
    }


    public static class VH extends RecyclerView.ViewHolder {
        public final RecyclerView rvColumnList;

        public VH(View v) {
            super(v);
            rvColumnList = (RecyclerView) v.findViewById(R.id.rv_column);
        }
    }


    @Override
    public void onBindViewHolder(ColumnAdapter.VH holder, int position) {

        ColumnEntity columnEntity = mLists.get(position);
        List<ColumnEntity> columnList = columnEntity.getSubLists();
        CategoryAdapter mCategoryAdapter = new CategoryAdapter(mContext, columnList);


        holder.rvColumnList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvColumnList.setAdapter(mCategoryAdapter);

        mCategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {

                if (mCategoryAdapter != null) {
                    mCategoryAdapter.notifyDataSetChanged();
                }

                ColumnEntity childEntity = columnList.get(position);
                if (childEntity.getSubLists() == null || childEntity.getSubLists().size() == 0) {
                    Toast.makeText(mContext, "无子目录，做跳转逻辑", Toast.LENGTH_LONG).show();
//                    Log.v("做跳转逻辑：", entity.getTitle());
//                    return;
                }

                int level = childEntity.getLevel();
                int itemCount = 0;
                for (int i = mLists.size(); i > level; i--) {
                    mLists.remove(i - 1);
                    itemCount++;
                }
                if (itemCount != 0) {
                    notifyItemRangeRemoved(level, itemCount);
                }
                if (childEntity.getSubLists() != null && childEntity.getSubLists().size() > 0) {
                    for (ColumnEntity entityTmp : childEntity.getSubLists()) {
                        entityTmp.setCheck(false);
                    }
                    mLists.add(childEntity);
                    notifyItemInserted(level);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = mLists != null ? mLists.size() : 0;
        return size;
    }

    public List<ColumnEntity> getList() {
        return mLists;
    }


    @Override
    public ColumnAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cloumn, null, false);
        return new ColumnAdapter.VH(v);
    }


}
