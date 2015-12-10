package com.xsoft.android.ebookreader.dependencies.data.modules;

import com.xsoft.android.ebookreader.BuildConfig;
import com.xsoft.android.ebookreader.data.login.datasources.database.LoginDBDataSourceImp;
import com.xsoft.android.ebookreader.data.login.datasources.dropbox.LoginDropBoxDataSourceImp;
import com.xsoft.android.ebookreader.dependencies.scoped.DataBaseName;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDBDataSource;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDropBoxDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
@Module
public final class DataModule {

    @Provides @Singleton
    LoginDropBoxDataSource provideLoginDropBoxDataSource (LoginDropBoxDataSourceImp loginDropBoxDataSourceImp){
        return loginDropBoxDataSourceImp;
    }
    @Provides @Singleton
    LoginDBDataSource provideLoginDBDataSource (LoginDBDataSourceImp loginDBDataSourceImp){
        return loginDBDataSourceImp;
    }

    @Provides @Singleton @DataBaseName String provideDatabaseName() {
        return "graficaslarambla" + (BuildConfig.DEBUG ? "-dev" : "") + ".db";
    }
}
