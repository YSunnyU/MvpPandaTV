package com.sunny.mvppandatv.view.HomeFrag;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recker.flybanner.FlyBanner;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.bean.HomeNewsMess;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/12.
 */

public class HomeFragAboveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private HomeNewsMess.DataBean data_bean;

    public HomeFragAboveAdapter(Context context, HomeNewsMess.DataBean dataBean) {
        this.context = context;
        this.data_bean = dataBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder = null;
        if (viewType == 0) {
            View home_one = from.inflate(R.layout.home_frag_one_item, parent, false);
            holder = new ViewHolderOne(home_one);
        } else if (viewType == 1) {
            View home_two = from.inflate(R.layout.home_frag_two, parent, false);
            holder = new ViewHolderTwo(home_two);
        } else if (viewType == 2) {
            View home_three = from.inflate(R.layout.home_frag_three, parent, false);
            holder = new ViewHolder3(home_three);
        } else if (viewType == 3) {
            View panda_live_list = from.inflate(R.layout.home_panda_live_list, parent, false);
            holder = new PandaLiveViewHolder(panda_live_list);
        } else if (viewType == 4) {
            View home_wall_view = from.inflate(R.layout.home_wall_live, parent, false);
            holder = new WallLiveViewHolder(home_wall_view);
        } else if (viewType == 5) {
            View home_china_live = from.inflate(R.layout.home_china_live, parent, false);
            holder = new ChinaLiveViewHolder(home_china_live);
        } else if (viewType == 6) {
            View home_interactive = from.inflate(R.layout.home_interactive, parent, false);
            holder = new InterActiveViewHolder(home_interactive);
        } else if (viewType == 7) {
            View home_pande_cctv = from.inflate(R.layout.home_panda_cctv_item, parent, false);
            holder = new CctvViewHolder(home_pande_cctv);
        } else if (viewType == 8) {
            View home_panda_last = from.inflate(R.layout.home_panda_last, parent, false);
            holder = new LastViewHolder(home_panda_last);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        } else if (position == 4) {
            return 4;
        } else if (position == 5) {
            return 5;
        } else if (position == 6) {
            return 6;
        } else if (position == 7) {
            return 7;
        } else if (position == 8) {
            return 8;
        }
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderOne) {

            List<String> imgesUrl = new ArrayList<>();
            for (int i = 0; i < data_bean.getBigImg().size(); i++) {
                imgesUrl.add(data_bean.getBigImg().get(i).getImage());
            }
            ((ViewHolderOne) holder).home_fly_banner.setImagesUrl(imgesUrl);

        } else if (holder instanceof ViewHolderTwo) {
            Glide.with(context).load(data_bean.getArea().getImage()).into(((ViewHolderTwo) holder).home_two_icon);
            ((ViewHolderTwo) holder).home_two_title.setText(data_bean.getArea().getTitle());
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolderTwo) holder).home_two_recy_view.setLayoutManager(manager);
            final List<HomeNewsMess.DataBean.AreaBean.ListscrollBean> listscroll = data_bean.getArea().getListscroll();
            HomeFragTwoAdapter homeFragTwoAdapter = new HomeFragTwoAdapter(listscroll, context);
            ((ViewHolderTwo) holder).home_two_recy_view.setAdapter(homeFragTwoAdapter);
            homeFragTwoAdapter.OnShortListener(new HomeFragTwoAdapter.OnShortListener() {
                @Override
                public void setOnShortListener(View view, int position) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("videoPath",listscroll.get(position).getImage());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof ViewHolder3) {
            Glide.with(context).load(data_bean.getPandaeye().getPandaeyelogo()).into(((ViewHolder3) holder).home_three_img);
            ((ViewHolder3) holder).home_frag_title_one.setText(data_bean.getPandaeye().getItems().get(0).getBrief());
            ((ViewHolder3) holder).home_frag_title_two.setText(data_bean.getPandaeye().getItems().get(0).getBrief());
            ((ViewHolder3) holder).home_frag_title_four.setText(data_bean.getPandaeye().getItems().get(1).getTitle());
            ((ViewHolder3) holder).home_frag_title_three.setText(data_bean.getPandaeye().getItems().get(1).getTitle());
            String pandaeyelist = data_bean.getPandaeye().getPandaeyelist();
            HttpFactory.homeService().getHomeService().getHomePandaEyeData(pandaeyelist)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<HomeThreeBean>() {
                        @Override
                        public void accept(HomeThreeBean homeThreeBean) throws Exception {
                            List<HomeThreeBean.ListBean> list = homeThreeBean.getList();
                            HomeFragThreeAdapter homeFragThreeAdapter = new HomeFragThreeAdapter(context, list);
                            ((ViewHolder3) holder).home_frag_three_recy_view.setAdapter(homeFragThreeAdapter);
                        }
                    });
        } else if (holder instanceof PandaLiveViewHolder) {
            ((PandaLiveViewHolder) holder).mPandaLiveTitle.setText(data_bean.getPandalive().getTitle());

            GridLayoutManager manager = new GridLayoutManager(context, 3);
            ((PandaLiveViewHolder) holder).mPandaLiveList.setLayoutManager(manager);
            List<HomeNewsMess.DataBean.PandaliveBean.ListBeanX> list = data_bean.getPandalive().getList();

            HomePandaLiveAdapter homePandaLiveAdapter = new HomePandaLiveAdapter(list, context);
            ((PandaLiveViewHolder) holder).mPandaLiveList.setAdapter(homePandaLiveAdapter);
        } else if (holder instanceof WallLiveViewHolder) {
            ((WallLiveViewHolder) holder).mWallLiveTitle.setText(data_bean.getWalllive().getTitle());
            GridLayoutManager manager1 = new GridLayoutManager(context, 3);
            ((WallLiveViewHolder) holder).mWallLiveList.setLayoutManager(manager1);
            HomeNewsMess.DataBean.WallliveBean walllive = data_bean.getWalllive();
//            List<HomeNewsMess.DataBean.WallliveBean.ListBeanX> list = walllive.getList();
            List<HomeNewsMess.DataBean.WallliveBean.ListBeanXX> list = walllive.getList();

            HomeWallLiveAdapter homePandaLiveAdapter1 = new HomeWallLiveAdapter(list, context);
            ((WallLiveViewHolder) holder).mWallLiveList.setAdapter(homePandaLiveAdapter1);
        } else if (holder instanceof ChinaLiveViewHolder) {
            GridLayoutManager manager2 = new GridLayoutManager(context, 3);
            ((ChinaLiveViewHolder) holder).LiveChinaList.setLayoutManager(manager2);
//            List<HomeNewsMess.DataBean.ChinaliveBean.ListBeanXX> list = data_bean.getChinalive().getList();
            HomeNewsMess.DataBean.ChinaliveBean chinalive = data_bean.getChinalive();
            List<HomeNewsMess.DataBean.ChinaliveBean.ListBean> list = chinalive.getList();
            HomeChinaLiveAdapter homePandaLiveAdapter2 = new HomeChinaLiveAdapter(list, context);
            ((ChinaLiveViewHolder) holder).LiveChinaList.setAdapter(homePandaLiveAdapter2);
        } else if (holder instanceof InterActiveViewHolder) {
//            final PandHome.DataBean.InteractiveBean interactiveBean = (PandHome.DataBean.InteractiveBean) datas.get(position);
            ((InterActiveViewHolder) holder).InterActiveTitle.setText(data_bean.getInteractive().getTitle());
            Glide.with(context).load(data_bean.getInteractive().getInteractiveone().get(0).getImage()).into(((InterActiveViewHolder) holder).InterActiveImage);
            ((InterActiveViewHolder) holder).InterActiveContent.setText(data_bean.getInteractive().getInteractiveone().get(0).getTitle());

        } else if (holder instanceof CctvViewHolder) {
            ((CctvViewHolder) holder).mCctvTitle.setText(data_bean.getCctv().getTitle());
            String listurl = data_bean.getCctv().getListurl();
            HttpFactory.homeService().getHomeService().getHomeCCTVData(listurl)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<HomeCCTVBean>() {
                        @Override
                        public void accept(HomeCCTVBean homeCCTVBean) throws Exception {
                            List<HomeCCTVBean.ListBean> list = homeCCTVBean.getList();
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                            ((CctvViewHolder) holder).mCctvList.setLayoutManager(gridLayoutManager);
                            HomeCctvListAdapter homeCctvListAdapter = new HomeCctvListAdapter(list, context);
                            ((CctvViewHolder) holder).mCctvList.setAdapter(homeCctvListAdapter);
                        }
                    });
        } else if (holder instanceof LastViewHolder) {
            List<HomeNewsMess.DataBean.ListBeanXXX> list = data_bean.getList();
            HomeNewsMess.DataBean.ListBeanXXX listBeanXXX = list.get(0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            ((LastViewHolder) holder).mListList.setLayoutManager(linearLayoutManager);
            String listUrl = data_bean.getList().get(0).getListUrl();
            HttpFactory.homeService().getHomeService().getHomePandaLastBeanData(listUrl)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<HomePandaLastBean>() {
                        @Override
                        public void accept(HomePandaLastBean homePandaLastBean) throws Exception {
                            List<HomePandaLastBean.ListBean> list1 = homePandaLastBean.getList();
                            HomePandaLastAdapter homePandaLastAdapter = new HomePandaLastAdapter(list1, context);
                            ((LastViewHolder) holder).mListList.setAdapter(homePandaLastAdapter);
                        }
                    });


        }


    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public FlyBanner home_fly_banner;

        public ViewHolderOne(View itemView) {
            super(itemView);
            this.home_fly_banner = (FlyBanner) itemView.findViewById(R.id.home_fly_banner);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {

        public RecyclerView home_two_recy_view;
        public RelativeLayout relativeLayout3;
        public ImageView home_two_icon;
        public TextView home_two_title;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            this.home_two_recy_view = itemView.findViewById(R.id.home_two_recy_view);
            this.home_two_icon = itemView.findViewById(R.id.home_two_icon);
            this.home_two_title = itemView.findViewById(R.id.home_two_title);

        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        public ImageView home_three_img;
        public TextView home_frag_title_one;
        public TextView home_frag_title_two;
        public TextView home_frag_title_three;
        public TextView home_frag_title_four;
        public RecyclerView home_frag_three_recy_view;

        public ViewHolder3(View itemView) {
            super(itemView);
            this.home_three_img = (ImageView) itemView.findViewById(R.id.home_three_img);
            this.home_frag_title_one = (TextView) itemView.findViewById(R.id.home_frag_title_one);
            this.home_frag_title_two = (TextView) itemView.findViewById(R.id.home_frag_title_two);
            this.home_frag_title_three = (TextView) itemView.findViewById(R.id.home_frag_title_three);
            this.home_frag_title_four = (TextView) itemView.findViewById(R.id.home_frag_title_four);
            home_frag_three_recy_view = itemView.findViewById(R.id.home_frag_three_recy_view);
        }
    }

    public static class PandaLiveViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView mPandaLiveTitle;
        public RecyclerView mPandaLiveList;

        public PandaLiveViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.mPandaLiveTitle = (TextView) rootView.findViewById(R.id.PandaLiveTitle);
            this.mPandaLiveList = (RecyclerView) rootView.findViewById(R.id.PandaLiveList);
        }

    }

    public static class WallLiveViewHolder extends RecyclerView.ViewHolder {

        private TextView mWallLiveTitle;
        private RecyclerView mWallLiveList;

        public WallLiveViewHolder(View itemView) {
            super(itemView);

            mWallLiveTitle = (TextView) itemView.findViewById(R.id.mWallLiveTitle);
            mWallLiveList = (RecyclerView) itemView.findViewById(R.id.mWallLiveList);
        }
    }

    public static class ChinaLiveViewHolder extends RecyclerView.ViewHolder {

        private TextView LiveChinaTitle;
        private RecyclerView LiveChinaList;

        public ChinaLiveViewHolder(View itemView) {
            super(itemView);

            LiveChinaTitle = (TextView) itemView.findViewById(R.id.LiveChinaTitle);
            LiveChinaList = (RecyclerView) itemView.findViewById(R.id.LiveChinaList);
        }

    }

    public static class InterActiveViewHolder extends RecyclerView.ViewHolder {


        private ImageView InterActiveImage;
        private TextView InterActiveContent;
        private LinearLayout InterActiveContainer;
        private TextView InterActiveTitle;

        public InterActiveViewHolder(View itemView) {
            super(itemView);
            InterActiveImage = (ImageView) itemView.findViewById(R.id.InterActiveImage);
            InterActiveContent = (TextView) itemView.findViewById(R.id.InterActiveContent);
            InterActiveContainer = (LinearLayout) itemView.findViewById(R.id.mInterActiveContainer);
            InterActiveTitle = (TextView) itemView.findViewById(R.id.mInterActiveTitle);
        }

    }

    public static class CctvViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView mCctvTitle;
        public RecyclerView mCctvList;

        public CctvViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.mCctvTitle = (TextView) rootView.findViewById(R.id.mCctvTitle);
            this.mCctvList = (RecyclerView) rootView.findViewById(R.id.mCctvList);
        }

    }

    public static class LastViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView mListTitle;
        public RecyclerView mListList;

        public LastViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.mListTitle = (TextView) rootView.findViewById(R.id.mListTitle);
            this.mListList = (RecyclerView) rootView.findViewById(R.id.mListList);
        }

    }
}
