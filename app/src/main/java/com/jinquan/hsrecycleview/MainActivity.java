package com.jinquan.hsrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jinquan.adapter.ColumnAdapter;
import com.jinquan.entity.ColumnEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    private ColumnAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_main_list);


        List<ColumnEntity> initList = new ArrayList<ColumnEntity>();
        for (int i = 1; i < 12; i++) {
            ColumnEntity columnEntity = getInitCloumnEntityByLevel(i, null);
            initList.add(columnEntity);
        }

        List<ColumnEntity> columnEntityList = new ArrayList<ColumnEntity>();
        ColumnEntity c = new ColumnEntity("0", "基类", 0, initList);
        columnEntityList.add(c);

        mAdapter = new ColumnAdapter(this, columnEntityList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);

    }

    private static int mId = 1;

    /**
     * 用于初始化列表数据，返回有level层级的ColumnEntity对象；
     *
     * @param level            设置对象有多少层
     * @param columnEntityList 该值设置为null
     * @return
     */
    private ColumnEntity getInitCloumnEntityByLevel(int level, List<ColumnEntity> columnEntityList) {

        if (level == 1) {
            ColumnEntity columnEntity = new ColumnEntity((mId++) + "", ";le: " + level, level, columnEntityList);
            return columnEntity;
        }

        if (level > 1) {
            List<ColumnEntity> tmpList = new ArrayList<>();
            for (int i = 1; i <= level; i++) {
                ColumnEntity columnEntity = new ColumnEntity((mId++) + "", "le :" + level + " c :" + i, level, columnEntityList);
                tmpList.add(columnEntity);
            }
            return getInitCloumnEntityByLevel(--level, tmpList);
        }
        return null;
    }
}
