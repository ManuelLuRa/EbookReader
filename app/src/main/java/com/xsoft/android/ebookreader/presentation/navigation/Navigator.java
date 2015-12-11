package com.xsoft.android.ebookreader.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.xsoft.android.ebookreader.presentation.view.activity.EBookListActivity;

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
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void navigateToEBookList(Context context) {
        if (context != null) {
            Intent intentToLaunch = EBookListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }


}

