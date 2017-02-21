package com.ymy.suiyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ymy.suiyue.R;
import com.ymy.suiyue.activity.PlayMusicActivity;
import com.ymy.suiyue.adapter.MyAdapter;
import com.ymy.suiyue.bean.Song;
import com.ymy.suiyue.util.MusicUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌曲列表
 */
public class MusicFragment extends Fragment {
    private ListView mListView;
    private List<Song> list  = new ArrayList<>();
    private MyAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_music_fragment,container,false);
        mListView = (ListView) view.findViewById(R.id.main_listview);
        //把扫描到的音乐赋值给list
        list = MusicUtils.getMusicData(getActivity().getApplicationContext());
        adapter = new MyAdapter(getActivity().getApplicationContext(),list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PlayMusicActivity.class);
                ArrayList list1 = new ArrayList();
                list1.add(list);
                intent.putExtra("list",list1);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        mListView.setAdapter(adapter);
        return view;
    }




}
