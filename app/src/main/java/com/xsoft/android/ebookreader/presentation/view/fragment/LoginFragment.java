package com.xsoft.android.ebookreader.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.dependencies.presentation.components.UserComponent;
import com.xsoft.android.ebookreader.presentation.presenter.LoginUserPresenter;
import com.xsoft.android.ebookreader.presentation.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright (C) 2015 Manuel Luque Ramos.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class LoginFragment extends BaseFragment implements LoginView {

    /* Local variables */
    @Inject LoginUserPresenter mLoginUserPresenter;

    @Bind(R.id.btnSingIn)Button mBtnSingIn;
    @Bind(R.id.rl_progress)RelativeLayout rl_progress;


    public LoginFragment() { super(); }

    public static LoginFragment newInstance() {
        LoginFragment userDetailsFragment = new LoginFragment();
        return userDetailsFragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }


    @OnClick(R.id.btnSingIn) void onBtnSingInClicked(View w) { loginButtonClicked(); }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override public void onResume() {
        super.onResume();
        this.mLoginUserPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.mLoginUserPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        this.mLoginUserPresenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.mLoginUserPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.mLoginUserPresenter.setView(this);
    }

    @Override public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public void showConnectionError() { this.showToastMessage(this.getContext().getString(R.string.connection_error)); }

    @Override public Context getContext() {
        return getActivity().getApplicationContext();
    }

    @Override public void showLoginOk() { this.showToastMessage(this.getContext().getString(R.string.login_ok)); }

    @Override public void showLoginError() { this.showToastMessage(this.getContext().getString(R.string.login_fail)); }

    @Override public void loginButtonClicked() {
            this.mLoginUserPresenter.loadLoginState(getActivity());
    }

}
