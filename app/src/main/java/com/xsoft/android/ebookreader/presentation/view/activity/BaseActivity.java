package com.xsoft.android.ebookreader.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xsoft.android.ebookreader.EBookApp;
import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.dependencies.EBookComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.modules.ActivityModule;
import com.xsoft.android.ebookreader.presentation.navigation.Navigator;

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
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected EBookComponent getApplicationComponent() { return ((EBookApp)getApplication()).getApplicationComponent(); }

    protected ActivityModule getActivityModule() { return new ActivityModule(this); }
}
