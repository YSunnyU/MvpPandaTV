package com.sunny.mvppandatv.view.Personal;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.sunny.mvppandatv.view.CCTVFrag.CCTVFragment;
import com.sunny.mvppandatv.view.HomeFrag.HomeFragment;
import com.sunny.mvppandatv.view.HuDong.HuDongActivity;
import com.sunny.mvppandatv.view.LiveChina.LiveChinaFragment;
import com.sunny.mvppandatv.view.PandaEyeFrag.PandaEyeFragment;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class AboveActivity extends BaseActivity {
    @Bind(R.id.pandaIcon)
    ImageView pandaIcon;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.hudongSign)
    ImageView hudongSign;
    @Bind(R.id.personSign)
    ImageView personSign;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.homeBtn)
    RadioButton homeBtn;
    @Bind(R.id.pandaEyeBtn)
    RadioButton pandaEyeBtn;
    @Bind(R.id.pandaLiveBtn)
    RadioButton pandaLiveBtn;
    @Bind(R.id.liveChinaBtn)
    RadioButton liveChinaBtn;
    @Bind(R.id.cctvBtn)
    RadioButton cctvBtn;
    @Bind(R.id.main_Rgp)
    RadioGroup mainRgp;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_above;
    }

    @Override
    protected void init() {
        setContentViewTwo(R.id.container,HomeFragment.class);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.hudongSign, R.id.personSign, R.id.homeBtn, R.id.pandaEyeBtn, R.id.pandaLiveBtn, R.id.liveChinaBtn, R.id.cctvBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hudongSign:
                startActivity(new Intent(AboveActivity.this, HuDongActivity.class));
                break;
            case R.id.personSign:
                startActivity(new Intent(AboveActivity.this,PersonalActivity.class));
            case R.id.homeBtn:
                title.setText("");
                hudongSign.setVisibility(View.VISIBLE);
                pandaIcon.setVisibility(View.VISIBLE);

                setContentViewTwo(R.id.container, HomeFragment.class);
                break;
            case R.id.pandaEyeBtn:
                setContentViewTwo(R.id.container, PandaEyeFragment.class);
                hudongSign.setVisibility(View.GONE);
                title.setText("熊猫观察");
                pandaIcon.setVisibility(View.GONE);
                break;
            case R.id.pandaLiveBtn:
                setContentViewTwo(R.id.container, PandaLiveFragment.class);
                hudongSign.setVisibility(View.GONE);
                title.setText("熊猫直播");
                pandaIcon.setVisibility(View.GONE);
                break;
            case R.id.liveChinaBtn:
                setContentViewTwo(R.id.container, LiveChinaFragment.class);
                hudongSign.setVisibility(View.GONE);
                pandaIcon.setVisibility(View.GONE);
                title.setText("直播中国");
                break;
            case R.id.cctvBtn:
                setContentViewTwo(R.id.container, CCTVFragment.class);
                hudongSign.setVisibility(View.GONE);
                title.setText("CCTV");
                pandaIcon.setVisibility(View.GONE);
                break;
        }
    }
}
