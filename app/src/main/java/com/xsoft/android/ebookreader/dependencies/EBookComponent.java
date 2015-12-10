package com.xsoft.android.ebookreader.dependencies;

import android.content.Context;

import com.xsoft.android.ebookreader.dependencies.data.modules.DataModule;
import com.xsoft.android.ebookreader.dependencies.executor.modules.ExecutorModule;
import com.xsoft.android.ebookreader.dependencies.repository.modules.RepositoryModule;
import com.xsoft.android.ebookreader.domain.repository.LoginUserRepository;
import com.xsoft.android.ebookreader.executor.Executor;
import com.xsoft.android.ebookreader.executor.MainThread;
import com.xsoft.android.ebookreader.presentation.view.activity.BaseActivity;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDropBoxDataSource;

import javax.inject.Singleton;

import dagger.Component;

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
@Component(
    modules = {
            EBookModule.class,
            ExecutorModule.class,
            RepositoryModule.class,
            DataModule.class,
    }
)
public interface EBookComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    Executor executor();
    MainThread mainthread();
    LoginUserRepository loginRepository();
    LoginDropBoxDataSource loginParseDataSource();

}

