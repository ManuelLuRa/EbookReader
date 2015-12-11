package com.xsoft.android.ebookreader.dependencies.presentation.modules;

import com.xsoft.android.ebookreader.dependencies.scoped.PerActivity;
import com.xsoft.android.ebookreader.domain.GetEBookList;
import com.xsoft.android.ebookreader.domain.GetLoginUserState;
import com.xsoft.android.ebookreader.domain.interactors.GetEBookListInteractor;
import com.xsoft.android.ebookreader.domain.interactors.GetLoginUserStateInteractor;

import javax.inject.Named;

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
public final class UserModule {

    @PerActivity @Provides @Named("loginUser")
    GetLoginUserState provideGetLoginUserStateInteractor(GetLoginUserStateInteractor interactor) {
        return interactor;
    }

    @PerActivity @Provides @Named("ebookList")
    GetEBookList provideGetEBookListInteractor(GetEBookListInteractor interactor) {
        return interactor;
    }

}
