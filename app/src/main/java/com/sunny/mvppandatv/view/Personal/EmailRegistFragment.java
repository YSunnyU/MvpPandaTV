package com.sunny.mvppandatv.view.Personal;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailRegistFragment extends BaseFragment {


    @Bind(R.id.email_num_edit)
    EditText emailNumEdit;
    @Bind(R.id.email_num_warn)
    TextView emailNumWarn;
    @Bind(R.id.email_pwd_edit)
    EditText emailPwdEdit;
    @Bind(R.id.email_pwd_edit_warn)
    TextView emailPwdEditWarn;
    @Bind(R.id.email_pwd_edit_sure)
    EditText emailPwdEditSure;
    @Bind(R.id.email_pwd_sure_warn)
    TextView emailPwdSureWarn;
    @Bind(R.id.email_yzm_edit)
    EditText emailYzmEdit;
    @Bind(R.id.email_yzm_img_btn)
    ImageView emailYzmImgBtn;
    @Bind(R.id.email_yzm_warn)
    TextView emailYzmWarn;
    @Bind(R.id.email_xieyi_check)
    CheckBox emailXieyiCheck;
    @Bind(R.id.email_reg_xieyi_view)
    TextView emailRegXieyiView;
    @Bind(R.id.email_btn_register)
    TextView emailBtnRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_email_regist;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.email_xieyi_check, R.id.email_reg_xieyi_view, R.id.email_btn_register,R.id.email_num_edit, R.id.email_num_warn, R.id.email_pwd_edit, R.id.email_pwd_edit_warn, R.id.email_pwd_edit_sure, R.id.email_pwd_sure_warn, R.id.email_yzm_edit, R.id.email_yzm_img_btn, R.id.email_yzm_warn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.email_num_edit:
                break;
            case R.id.email_num_warn:
                break;
            case R.id.email_pwd_edit:
                break;
            case R.id.email_pwd_edit_warn:
                break;
            case R.id.email_pwd_edit_sure:
                break;
            case R.id.email_pwd_sure_warn:
                break;
            case R.id.email_yzm_edit:
                break;
            case R.id.email_yzm_img_btn:
                break;
            case R.id.email_yzm_warn:
                break;
            case R.id.email_xieyi_check:
                break;
            case R.id.email_reg_xieyi_view:
                break;
            case R.id.email_btn_register:
                break;
        }
    }




}
