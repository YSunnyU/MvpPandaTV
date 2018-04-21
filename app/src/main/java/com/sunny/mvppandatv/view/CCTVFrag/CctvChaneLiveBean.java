package com.sunny.mvppandatv.view.CCTVFrag;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class CctvChaneLiveBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * url :
         * title : CCTV-4（亚洲）
         * brief :
         * image : http://t.live.cntv.cn/image/cctv4_01.png
         * liveimage : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/1/28/1453968606118_296.png
         * id : cctv4
         * type : 1
         * order : 1
         */

        private String url;
        private String title;
        private String brief;
        private String image;
        private String liveimage;
        private String id;
        private String type;
        private String order;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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

        public String getLiveimage() {
            return liveimage;
        }

        public void setLiveimage(String liveimage) {
            this.liveimage = liveimage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
