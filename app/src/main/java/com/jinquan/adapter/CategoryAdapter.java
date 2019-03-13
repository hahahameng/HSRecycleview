package com.jinquan.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinquan.entity.ColumnEntity;
import com.jinquan.hsrecycleview.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    private static final String TAG = "CategoryAdapter";
    private List<ColumnEntity> mLists;
    private Context mContext;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position, String data);
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public CategoryAdapter(Context context, List<ColumnEntity> columnEntityList) {
        this.mContext = context;
        this.mLists = columnEntityList;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        return new VH(v);
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_column);
        }
    }


    @Override
    public void onBindViewHolder(VH holder, final int position) {
        ColumnEntity entity = mLists.get(position);
        if (entity.isCheck()) {
            holder.itemView.setBackgroundColor(mContext.getColor(R.color.colorPrimary));
        } else {
            holder.itemView.setBackgroundColor(mContext.getColor(R.color.colorAccent));
        }

        if (entity.isParent()) {
            holder.title.setText("目录： " + entity.getTitle());
        } else {
            holder.title.setText("文件：" + entity.getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mLists.size(); i++) {
                    mLists.get(i).setCheck(false);
                }
                entity.setCheck(true);
                mOnItemClickListener.onItemClick(v, position, entity.getTitle());
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


}