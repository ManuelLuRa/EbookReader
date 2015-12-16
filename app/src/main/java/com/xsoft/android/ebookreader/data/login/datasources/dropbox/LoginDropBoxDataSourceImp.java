package com.xsoft.android.ebookreader.data.login.datasources.dropbox;

import android.app.Activity;

import com.xsoft.android.ebookreader.data.dropboxutils.DropBoxApi;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDropBoxDataSource;

import javax.inject.Inject;

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
public class LoginDropBoxDataSourceImp implements LoginDropBoxDataSource {

    private final DropBoxApi mDropBoxApi;

    @Inject
    LoginDropBoxDataSourceImp(DropBoxApi dropBoxApi) {
        this.mDropBoxApi = dropBoxApi;
    }


    @Override
    public String getUsedToken(Activity activity) throws NetworkException {

        if (!mDropBoxApi.getDBApi().getSession().authenticationSuccessful())
            mDropBoxApi.getDBApi().getSession().startOAuth2Authentication(activity);

        //Retained response while auth finish.
        while (!mDropBoxApi.getDBApi().getSession().authenticationSuccessful()) {
        }

        if (mDropBoxApi.getDBApi().getSession().authenticationSuccessful()){
            mDropBoxApi.getDBApi().getSession().finishAuthentication();
            return mDropBoxApi.getDBApi().getSession().getOAuth2AccessToken();
    }
        else
            return null;
    }

    @Override
    public void restoreSessionWithPreviousToken(String previousToken) throws CacheException {
        mDropBoxApi.getDBApi().getSession().setOAuth2AccessToken(previousToken);
    }

}
