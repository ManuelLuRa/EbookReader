package com.xsoft.android.ebookreader.presentation.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.xsoft.android.ebookreader.domain.GetLoginUserState;
import com.xsoft.android.ebookreader.domain.exception.ErrorBundle;
import com.xsoft.android.ebookreader.presentation.exception.ErrorMessageFactory;
import com.xsoft.android.ebookreader.presentation.view.LoginView;

import javax.inject.Inject;
import javax.inject.Named;

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
public class LoginUserPresenter implements Presenter{

    private LoginView mLoginView;
    private GetLoginUserState mGetLoginUserState;

    @Inject
    public LoginUserPresenter(@Named("loginUser") GetLoginUserState mGetLoginUserState) {
        this.mGetLoginUserState = mGetLoginUserState;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {}

    public void setView(@NonNull LoginView view) { this.mLoginView = view; }

    public void loadLoginState(Activity activity) {
        this.showViewLoading();
        this.getLoginState(activity);
    }

    public void getLoginState(Activity activity){
        this.showViewLoading();
        mGetLoginUserState.execute(activity, new GetLoginUserState.Callback() {
            @Override public void onGetLoginStateOk() {
                hideViewLoading();
                showLoginOk();
            }

            @Override public void onGetLoginStateError() {
                hideViewLoading();
                showLoginError();
            }

            @Override public void onConnectionError() {
                hideViewLoading();
                showConnectionError();
            }
        });

    }


    private void showViewLoading() {
        this.mLoginView.showLoading();
    }

    private void hideViewLoading() {
        this.mLoginView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.mLoginView.getContext(),
                errorBundle.getException());
        this.mLoginView.showError(errorMessage);
    }

    private void showConnectionError(){this.mLoginView.showConnectionError();}

    private void showLoginError(){this.mLoginView.showLoginError();}

    private void showLoginOk (){this.mLoginView.showLoginOk();}

}
