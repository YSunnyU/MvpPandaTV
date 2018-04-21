package com.sunny.mvppandatv.view.Personal;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalRegistActivity extends BaseActivity {

    @Bind(R.id.tvphonereg)
    TextView tvphonereg;
    @Bind(R.id.tvphonereg_line)
    TextView tvphoneregLine;
    @Bind(R.id.tvemailreg)
    TextView tvemailreg;
    @Bind(R.id.tvemailreg_line)
    TextView tvemailregLine;
    @Bind(R.id.framelayout_register_content)
    FrameLayout framelayoutRegisterContent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_regist;
    }

    @Override
    protected void init() {
        setContentViewTwo(R.id.framelayout_register_content,MobelRegistFragment.class);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tvphonereg, R.id.tvemailreg, R.id.framelayout_register_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvphonereg:
                tvphoneregLine.setVisibility(View.VISIBLE);
                tvemailregLine.setVisibility(View.GONE);
                setContentViewTwo(R.id.framelayout_register_content,MobelRegistFragment.class);
                break;
            case R.id.tvemailreg:
                tvphoneregLine.setVisibility(View.GONE);
                tvemailregLine.setVisibility(View.VISIBLE);
                setContentViewTwo(R.id.framelayout_register_content,EmailRegistFragment.class);
                break;
        }
    }
}
