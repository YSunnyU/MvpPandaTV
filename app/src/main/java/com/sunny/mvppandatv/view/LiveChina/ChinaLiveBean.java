package com.sunny.mvppandatv.view.LiveChina;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/14.
 */

public class ChinaLiveBean {
    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean {
        /**
         * title : 精编直播
         * brief : 精选全国30多个热门景区，24小时实时直播，美丽中国一网打尽。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2017/10/17/1508230595737_57.jpg
         * id : wgw05
         * order : 1
         */

        private String title;
        private String brief;
        private String image;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
