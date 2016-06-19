package com.example.liuqun.cardviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Beauty> mDatas;
    private HomeAdapter  mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        if (mRecyclerView != null) {
            //在回收时可以提高性能
            mRecyclerView.setHasFixedSize(true);
        }

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,
//                LinearLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,
//                LinearLayoutManager.HORIZONTAL));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration
                (getApplicationContext(), DividerItemDecoration.HORIZONTAL_LIST));
        mAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);



    }

    private void initData() {
        mDatas = new ArrayList<>();
        VolleyHttp http = new VolleyHttp(MainActivity.this);
        http.getJSONObject("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1",
                listener, errorListener);
    }

    Response.Listener      listener      = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
//            Log.d(TAG, "onResponse: "+response);
            Gson gson = new Gson();
            Bean<Beauty> beauties = gson.fromJson(response, new
                    TypeToken<Bean<Beauty>>() {
                    }.getType());
            mDatas = beauties.getResults();
            mAdapter.appendData(mDatas, false);
            mAdapter.notifyDataSetChanged();
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "失败了", Toast.LENGTH_SHORT).show();
        }
    };

}
