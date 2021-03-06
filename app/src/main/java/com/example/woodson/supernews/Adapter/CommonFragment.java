package com.example.woodson.supernews.Adapter;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.woodson.supernews.Entity.newsData;
import com.example.woodson.supernews.MainActivity;
import com.example.woodson.supernews.Presenter.controlNews;
import com.example.woodson.supernews.R;
import com.example.woodson.supernews.View.NewsView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommonFragment extends Fragment implements NewsView{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView textView;
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private CommonAdapter<newsData> adapter;
    private RequestQueue queue;
    private controlNews controlNews;

    public CommonFragment() {
        // Required empty public constructor
    }

    public static CommonFragment newInstance(String param1) {
        CommonFragment fragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        controlNews = new controlNews(this);
        controlNews.getNews(queue);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_common, container, false);
        TextView textView = view.findViewById(R.id.news_content);
        textView.setText(mParam1);
        recyclerView = view.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void setNews(List<newsData> datas) {
        adapter = new CommonAdapter<newsData>(getContext(),R.layout.newslistitem,datas) {
            @Override
            public void setView(CommonAdapterViewHolder holder, newsData newsData) {
                TextView category = (TextView) holder.getView(R.id.category);
                category.setText(newsData.getCategory());
                TextView title = (TextView) holder.getView(R.id.title);
                title.setText(newsData.getTitle());
                ImageView imageView1 = (ImageView) holder.getView(R.id.imageview1);
                Glide.with(getContext()).load(newsData.getThumbnail_pic_s()).into(imageView1);
                ImageView imageView2 = (ImageView) holder.getView(R.id.imageview2);
                Glide.with(getContext()).load(newsData.getThumbnail_pic_s()).into(imageView2);
                ImageView imageView3 = (ImageView) holder.getView(R.id.imageview3);
                Glide.with(getContext()).load(newsData.getThumbnail_pic_s()).into(imageView3);
            }



        };
recyclerView.setAdapter(adapter);
    }
}
