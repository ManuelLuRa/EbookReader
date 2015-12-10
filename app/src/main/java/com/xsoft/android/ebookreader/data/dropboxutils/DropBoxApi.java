package com.xsoft.android.ebookreader.data.dropboxutils;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

import javax.inject.Inject;
import javax.inject.Singleton;

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
@Singleton
public class DropBoxApi {
    //Obtained api keys
    final static private String APP_KEY = "e7eg4fnpj7266f6";
    final static private String APP_SECRET = "lwwciq1chzn1i8n";
    //Api object
    private DropboxAPI<AndroidAuthSession> mDBApi;

    @Inject DropBoxApi(){
        // Constructor initializes auth.
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
    }

    public DropboxAPI<AndroidAuthSession> getDBApi() {
        return mDBApi;
    }
}
