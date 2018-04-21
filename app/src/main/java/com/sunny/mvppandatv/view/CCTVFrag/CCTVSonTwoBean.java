package com.sunny.mvppandatv.view.CCTVFrag;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/15.
 */

public class CCTVSonTwoBean {
    private List<?> bigImg;
    private List<ListBean> list;

    public List<?> getBigImg() {
        return bigImg;
    }

    public void setBigImg(List<?> bigImg) {
        this.bigImg = bigImg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * url : http://tv.cctv.com/2016/06/19/VIDEsaZXJ11CSNrqLtNPN9Bn160619.shtml
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/6/23/1466675699501_60.jpg
         * title : 《中国舆论场》
         * brief : 越南飞机失事，中国海军派舰艇全力搜救；血浓于水！
         * type :
         * videoLength : 57:29
         * id : VSET100266299740
         * order : 1
         */

        private String url;
        private String image;
        private String title;
        private String brief;
        private String type;
        private String videoLength;
        private String id;
        private String order;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
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
