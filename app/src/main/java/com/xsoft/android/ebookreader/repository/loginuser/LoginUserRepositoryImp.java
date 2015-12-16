package com.xsoft.android.ebookreader.repository.loginuser;

import android.app.Activity;

import com.xsoft.android.ebookreader.domain.repository.LoginUserRepository;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;
import com.xsoft.android.ebookreader.repository.exceptions.PersistException;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDBDataSource;
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
public class LoginUserRepositoryImp implements LoginUserRepository {

    private LoginDBDataSource mLoginDBDataSource;
    private LoginDropBoxDataSource mDropBoxDataSource;

    @Inject LoginUserRepositoryImp(LoginDropBoxDataSource loginDropBoxDataSource,
                                   LoginDBDataSource sharedPreferencesDataSource){
        this.mLoginDBDataSource = sharedPreferencesDataSource;
        this.mDropBoxDataSource = loginDropBoxDataSource;

    }

    @Override
    public boolean isLoginOk(Activity activity) throws NetworkException, PersistException, CacheException {
        boolean result = false;
        try {
            result = mLoginDBDataSource.isLoginOK();
            if(!result) {
                String token = mDropBoxDataSource.getUsedToken(activity);
                if (token != null){
                    mLoginDBDataSource.persist(token);
                    result = true;
                }
            }else{
                mDropBoxDataSource.restoreSessionWithPreviousToken(mLoginDBDataSource.getPersistedToken());
            }
        }catch (NetworkException e1){
            throw new NetworkException();
        }catch (PersistException e1){
            throw new PersistException();
        }catch (CacheException e1){
            throw new CacheException();
        }

        return result;
    }
}
