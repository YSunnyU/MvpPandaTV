package com.sunny.mvppandatv.base;

/**
 * Created by 张玗 on 2018/4/12.
 */

public interface BasePresenter<T> {
    void attachView(T view);
    void detach();
}
