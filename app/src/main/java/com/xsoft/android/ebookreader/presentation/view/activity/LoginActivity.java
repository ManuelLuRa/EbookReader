package com.xsoft.android.ebookreader.presentation.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.data.dropboxutils.DropBoxApi;
import com.xsoft.android.ebookreader.dependencies.presentation.components.DaggerUserComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.components.UserComponent;
import com.xsoft.android.ebookreader.dependencies.presentation.modules.UserModule;
import com.xsoft.android.ebookreader.dependencies.scoped.HasComponent;
import com.xsoft.android.ebookreader.presentation.view.fragment.LoginFragment;

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
public class LoginActivity extends BaseActivity implements HasComponent<UserComponent>{

    private UserComponent mUserComponent;
    @Inject DropBoxApi dropBoxApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fragmentContainer, LoginFragment.newInstance());
        this.initializeInjector();
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (dropBoxApi != null && dropBoxApi.getDBApi().getSession().authenticationSuccessful()) {
            try {
                // Required to complete auth, sets the access token on the session
                dropBoxApi.getDBApi().getSession().finishAuthentication();

                String accessToken = dropBoxApi.getDBApi().getSession().getOAuth2AccessToken();
            } catch (IllegalStateException e) {

            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override protected void onSaveInstanceState(Bundle outState) { super.onSaveInstanceState(outState); }

    private void initializeInjector() {
        this.mUserComponent = DaggerUserComponent.builder()
                .eBookComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build();
    }

    @Override public UserComponent getComponent() { return mUserComponent; }
}
