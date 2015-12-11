package com.xsoft.android.ebookreader.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.dependencies.presentation.components.DaggerUserComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.components.UserComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.modules.UserModule;
import com.xsoft.android.ebookreader.dependencies.scoped.HasComponent;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.presentation.view.fragment.EBookListFragment;

/**
 * Copyright (C) 2015 Manuel Luque Ramos.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class EBookListActivity extends BaseActivity implements HasComponent<UserComponent>, EBookListFragment.EBookListListener {

    private UserComponent mUserComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, EBookListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        addFragment(R.id.fragmentContainer, EBookListFragment.newInstance());
        this.initializeInjector();
    }


    private void initializeInjector() {
        this.mUserComponent = DaggerUserComponent.builder()
                .eBookComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build();
    }

    @Override public UserComponent getComponent() { return mUserComponent; }

    @Override
    public void onEBookClicked(EBook eBook) {

    }
}
