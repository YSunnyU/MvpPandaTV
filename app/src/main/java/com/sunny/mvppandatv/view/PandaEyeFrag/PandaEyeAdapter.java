package com.sunny.mvppandatv.view.PandaEyeFrag;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.view.HomeFrag.HttpFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class PandaEyeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PandaEyeBean pandaEyeBean;
    private Context context;

    public PandaEyeAdapter(PandaEyeBean pandaEyeBean, Context context) {
        this.pandaEyeBean = pandaEyeBean;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
//        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View eye_panda_bigimg = inflater.inflate(R.layout.eye_panda_bigimg, parent, false);
            holder = new HolderBigImage(eye_panda_bigimg);
        } else {
            View eye_panda_list = inflater.inflate(R.layout.eye_panda_list, parent, false);
            holder=new HolderList(eye_panda_list);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderBigImage) {
            PandaEyeBean.DataBean.BigImgBean bigImgBean = pandaEyeBean.getData().getBigImg().get(0);
            Picasso.with(context).load(bigImgBean.getImage()).into(((HolderBigImage) holder).eye_panda_bigLogo);
            ((HolderBigImage) holder).eye_panda_title.setText(bigImgBean.getTitle());
        }if (holder instanceof HolderList){
            PandaEyeBean.DataBean data = pandaEyeBean.getData();
            String listurl = data.getListurl();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            ((HolderList) holder).eye_panda_list_recy.setLayoutManager(linearLayoutManager);
            HttpFactory.homeService().getPandaEyeService()
                    .getPandaEyeListData(listurl)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<PandaEyeListBean>() {
                        @Override
                        public void accept(PandaEyeListBean pandaEyeListBean) throws Exception {
                            List<PandaEyeListBean.ListBean> list = pandaEyeListBean.getList();
                            PandaEyeListAdapter pandaEyeListAdapter = new PandaEyeListAdapter(list, context);
                            ((HolderList) holder).eye_panda_list_recy.setAdapter(pandaEyeListAdapter);

                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class HolderBigImage extends RecyclerView.ViewHolder {
        public ImageView eye_panda_bigLogo;
        public TextView eye_panda_title;

        public HolderBigImage(View itemView) {
            super(itemView);
            this.eye_panda_bigLogo = (ImageView) itemView.findViewById(R.id.eye_panda_bigLogo);
            this.eye_panda_title = (TextView) itemView.findViewById(R.id.eye_panda_title);

        }
    }
    public class HolderList extends RecyclerView.ViewHolder {
        public RecyclerView eye_panda_list_recy;
        public HolderList(View itemView) {
            super(itemView);
            this.eye_panda_list_recy = (RecyclerView) itemView.findViewById(R.id.eye_panda_list_recy);

        }
    }



}
