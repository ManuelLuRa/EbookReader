package com.xsoft.android.ebookreader.dependencies.presentation.components;

import com.xsoft.android.ebookreader.dependencies.EBookComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.modules.ActivityModule;
import com.xsoft.android.ebookreader.dependencies.presentation.modules.UserModule;
import com.xsoft.android.ebookreader.dependencies.scoped.PerActivity;
import com.xsoft.android.ebookreader.presentation.view.fragment.EBookListFragment;
import com.xsoft.android.ebookreader.presentation.view.fragment.LoginFragment;

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
@PerActivity
@Component(dependencies = EBookComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(LoginFragment loginFragment);
    void inject(EBookListFragment eBookListFragment);
}
