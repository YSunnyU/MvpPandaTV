package com.sunny.mvppandatv.view.HuDong;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class HuDongBean {
    private List<InteractiveBean> interactive;

    public List<InteractiveBean> getInteractive() {
        return interactive;
    }

    public void setInteractive(List<InteractiveBean> interactive) {
        this.interactive = interactive;
    }

    public static class InteractiveBean {
        /**
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2017/6/5/1496626374961_922.jpg
         * order : 1
         * title : 二十四节气——芒种
         * url : http://webapp.cctv.com/h5/travel/U80531QU7SY7.html
         */

        private String image;
        private String order;
        private String title;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
