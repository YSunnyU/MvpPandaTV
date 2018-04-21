package com.sunny.mvppandatv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sunny.mvppandatv.app.App;

import butterknife.ButterKnife;

/**
 * Created by 张玗 on 2018/4/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment lastFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        App.context = this;
        ButterKnife.bind(this);
        init();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract void initData();

    //    统一管理fragment
    protected BaseFragment setContentViewTwo(int contaired, Class<? extends BaseFragment> baseFragment) {
//        得到一个Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
//        得到转换器
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        得到标记名字
        String simpleName = baseFragment.getSimpleName();

        BaseFragment fragmentByTag = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        try {
            if (fragmentByTag == null) {
                fragmentByTag = baseFragment.newInstance();
                transaction.add(contaired, fragmentByTag,simpleName);
            }
            transaction.show(fragmentByTag);
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        lastFragment = fragmentByTag;
        transaction.commit();
        return fragmentByTag;


    }
    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.context = null;
    }
}
