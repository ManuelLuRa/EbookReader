package com.xsoft.android.ebookreader.domain.interactors;


import android.app.Activity;

import com.xsoft.android.ebookreader.domain.GetLoginUserState;
import com.xsoft.android.ebookreader.domain.repository.LoginUserRepository;
import com.xsoft.android.ebookreader.executor.Executor;
import com.xsoft.android.ebookreader.executor.Interactor;
import com.xsoft.android.ebookreader.executor.MainThread;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;
import com.xsoft.android.ebookreader.repository.exceptions.PersistException;

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
public class GetLoginUserStateInteractor implements Interactor, GetLoginUserState {

    private final Executor executor;
    private final MainThread mainThread;
    private Activity activity;

    private Callback callback;

    private LoginUserRepository mRepository;

    @Inject
    GetLoginUserStateInteractor(Executor executor, MainThread mainThread, LoginUserRepository mRepository) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.mRepository = mRepository;
    }

    @Override public void execute(Activity activity, Callback callback) {
        this.callback = callback;
        this.activity = activity;
        this.executor.run(this);
    }

    @Override
    public void run() {
        notifyLoginState(activity);
    }

    private void notifyLoginState(Activity activity){
        try {
            if (mRepository.isLoginOk(activity))
                notifyLoginStateOk();
            else
                notifyLoginStateError();
        }catch (CacheException e){
           e.printStackTrace();
        }catch (NetworkException e){
            notifyConnectionError();
        }catch (PersistException e){
            e.printStackTrace();
        }
    }

    private void notifyConnectionError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void notifyLoginStateOk() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetLoginStateOk();           }
        });
    }

    private void notifyLoginStateError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetLoginStateError();
            }
        });
    }

}
