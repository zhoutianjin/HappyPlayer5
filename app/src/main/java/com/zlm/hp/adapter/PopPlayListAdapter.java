package com.zlm.hp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlm.hp.entity.AudioInfo;
import com.zlm.hp.manager.AudioPlayerManager;
import com.zlm.hp.ui.R;
import com.zlm.hp.widget.IconfontTextView;

import java.util.List;

/**
 * @Description: 歌曲列表弹出窗口
 * @author: zhangliangming
 * @date: 2018-11-28 21:08
 **/
public class PopPlayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_MAIN = 0;
    public static final int TYPE_LRC = 1;
    private Context mContext;
    private List<AudioInfo> mDatas;
    private int mType;

    public PopPlayListAdapter(Context context, List<AudioInfo> datas, int type) {
        this.mContext = context;
        this.mDatas = datas;
        this.mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (mType == TYPE_MAIN) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_lvitem_popsong, null, false);
            PopListViewHolder holder = new PopListViewHolder(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof PopListViewHolder && position < mDatas.size()) {
            AudioInfo audioInfo = mDatas.get(position);
            reshViewHolder(position, (PopListViewHolder) viewHolder, audioInfo);
        }
    }

    /**
     * @param position
     * @param viewHolder
     * @param audioInfo
     */
    private void reshViewHolder(int position, PopListViewHolder viewHolder, final AudioInfo audioInfo) {
        //显示歌曲索引
        viewHolder.getSongIndexTv().setText(((position + 1) < 10 ? "0" + (position + 1) : (position + 1) + ""));
        String singerName = audioInfo.getSingerName();
        String songName = audioInfo.getSongName();
        viewHolder.getSongNameTv().setText(songName);
        viewHolder.getSingerNameTv().setText(singerName);
        //item点击事件
        viewHolder.getListItemRelativeLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayerManager.newInstance(mContext).playSong(audioInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     *
     */
    class PopListViewHolder extends RecyclerView.ViewHolder {
        private View view;
        /**
         * item底部布局
         */
        private RelativeLayout listItemRelativeLayout;

        /**
         * 歌手头像按钮
         */
        private ImageView singPicImg;
        /**
         * 歌曲索引
         */
        private TextView songIndexTv;

        /**
         * 歌曲名称
         */
        private TextView songNameTv;

        /**
         * 歌手名称
         */
        private TextView singerNameTv;
        /**
         * 是否存在本地
         */
        private ImageView islocalImg;
        /**
         * 下载未完成按钮
         */
        private ImageView downloadImg;
        /**
         * 添加喜欢按钮
         */
        private IconfontTextView unlikeTv;

        /**
         * 下载完成按钮
         */
        private ImageView downloadedImg;

        /**
         * 喜欢按钮
         */
        private ImageView likeImg;


        public PopListViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public RelativeLayout getListItemRelativeLayout() {
            if (listItemRelativeLayout == null) {
                listItemRelativeLayout = view.findViewById(R.id.itemBG);
            }
            return listItemRelativeLayout;
        }

        public TextView getSongNameTv() {
            if (songNameTv == null) {
                songNameTv = view.findViewById(R.id.songName);
            }
            return songNameTv;
        }

        public TextView getSingerNameTv() {
            if (singerNameTv == null) {
                singerNameTv = view.findViewById(R.id.singerName);
            }
            return singerNameTv;
        }

        public ImageView getIslocalImg() {
            if (islocalImg == null) {
                islocalImg = view.findViewById(R.id.islocal);
            }
            return islocalImg;
        }

        public ImageView getSingPicImg() {
            if (singPicImg == null) {
                singPicImg = view.findViewById(R.id.singPic);
            }
            return singPicImg;
        }

        public TextView getSongIndexTv() {
            if (songIndexTv == null) {
                songIndexTv = view.findViewById(R.id.songIndex);
            }
            return songIndexTv;
        }

        public ImageView getDownloadImg() {
            if (downloadImg == null) {
                downloadImg = view.findViewById(R.id.download);
            }
            return downloadImg;
        }

        public IconfontTextView getUnLikeTv() {
            if (unlikeTv == null) {
                unlikeTv = view.findViewById(R.id.unlike);
            }
            return unlikeTv;
        }

        public ImageView getDownloadedImg() {
            if (downloadedImg == null) {
                downloadedImg = view.findViewById(R.id.downloaded);
            }
            return downloadedImg;
        }

        public ImageView getLikedImg() {
            if (likeImg == null) {
                likeImg = view.findViewById(R.id.liked);
            }
            return likeImg;
        }

    }
}
