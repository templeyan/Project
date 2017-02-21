package com.ymy.suiyue.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 播放音乐的详情页
 */
public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private TextView txt_song, txt_artist;//显示歌名和歌手的文本框
    private ImageView img_album, img_back;//显示专辑图片,返回键
    private MediaPlayer player = new MediaPlayer();//音乐播放器
    private static int tag = 0;//播放状态
    private static int flag = 0;//上一首和下一首跳转时是否正在播放歌曲的状态
    private Button btnPlay, btnLast, btnNext;//播放按钮,上一首,下一首
    private List<Song> list;//存放歌曲信息集合
    private int position;//点击歌曲的序号
    private int size;//手机里歌曲的总数量
    private static Intent intent;//获得上个界面传过来的信息
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        txt_song = (TextView) findViewById(R.id.song);
        txt_artist = (TextView) findViewById(R.id.artist);
        img_album = (ImageView) findViewById(R.id.img);
        img_back = (ImageView) findViewById(R.id.back);
        btnPlay = (Button) findViewById(R.id.button);
        btnLast = (Button) findViewById(R.id.last);
        btnNext = (Button) findViewById(R.id.next);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);


        img_back.setOnClickListener(this);
        player.setOnCompletionListener(this);
        intent = getIntent();
        ArrayList<Parcelable> list1 = (ArrayList<Parcelable>) intent.getSerializableExtra("list");
        list = (List<Song>) list1.get(0);
        size = list.size();
        position = intent.getIntExtra("position", 0);
        txt_song.setText(list.get(position).getSong());
        txt_artist.setText(list.get(position).getSinger());
        if (createAlbumImg(list.get(position).getPath()) != null) {
            img_album.setImageBitmap(createAlbumImg(list.get(position).getPath()));
        }else {
            img_album.setImageBitmap(null);
        }
    }

    /**
     * 根据歌曲路径获得专辑封面
     *
     * @param filePath 文件路径，like XXX/XXX/XX.mp3
     * @return 专辑封面bitmap
     * @Description 获取专辑封面
     */
    public static Bitmap createAlbumImg(final String filePath) {
        Bitmap bitmap = null;
        //能够获取多媒体文件元数据的类
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath); //设置数据源
            byte[] embedPic = retriever.getEmbeddedPicture(); //得到字节型数据
            bitmap = BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length); //转换为图片
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    //播放按钮
    public void play(View v) {
        if (btnPlay.getId() == v.getId()) {
            try {
                if (tag == 0) {//第一次播放
                    player.setDataSource(list.get(position).getPath());
                    player.prepare();
                    player.start();
                    btnPlay.setBackgroundResource(R.drawable.pause);
                    tag = 2;
                    flag = 1;
                } else if (tag == 1) {//除了第一次的播放
                    btnPlay.setBackgroundResource(R.drawable.pause);
                    player.start();
                    tag = 2;
                    flag=1;
                } else if (tag == 2 && player.isPlaying()) {//暂停
                    btnPlay.setBackgroundResource(R.drawable.play);
                    player.pause();
                    tag = 1;
                    flag=0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (btnLast.getId() == v.getId()) {//上一首
            if (position == 0) {
                position = size - 1;
            } else {
                position--;
            }
            txt_song.setText(list.get(position).getSong());
            txt_artist.setText(list.get(position).getSinger());
            if (createAlbumImg(list.get(position).getPath()) != null) {
                img_album.setImageBitmap(createAlbumImg(list.get(position).getPath()));
            }else {
                img_album.setImageBitmap(null);
            }
            btnPlay.setBackgroundResource(R.drawable.play);
            try {
                if (flag == 1) {
                    player.release();
                    player = new MediaPlayer();
                    player.setDataSource(list.get(position).getPath());
                    player.prepare();
                    player.start();
                    tag = 2;
                    btnPlay.setBackgroundResource(R.drawable.pause);
                }else{
                    player.release();
                    player = new MediaPlayer();
                    btnPlay.setBackgroundResource(R.drawable.play);
                    tag=0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (btnNext.getId() == v.getId()) {//下一首
            if (position == size - 1) {
                position = 0;
            } else {
                position++;
            }
            txt_song.setText(list.get(position).getSong());
            txt_artist.setText(list.get(position).getSinger());
            if (createAlbumImg(list.get(position).getPath()) != null) {
                img_album.setImageBitmap(createAlbumImg(list.get(position).getPath()));
            } else {
                img_album.setImageBitmap(null);
            }
            btnPlay.setBackgroundResource(R.drawable.play);
            try {
                if (flag == 1) {
                    player.release();
                    player = new MediaPlayer();
                    player.setDataSource(list.get(position).getPath());
                    player.prepare();
                    player.start();
                    tag = 2;
                    btnPlay.setBackgroundResource(R.drawable.pause);
                }else {
                    player.release();
                    player = new MediaPlayer();
                    btnPlay.setBackgroundResource(R.drawable.play);
                    tag=0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //返回键
    @Override
    public void onClick(View v) {
        if (img_back.getId() == v.getId()) {
            player.release();
            tag = 0;
            finish();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btnPlay.setBackgroundResource(R.drawable.play);
        tag = 1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.release();
        tag = 0;
    }


}
