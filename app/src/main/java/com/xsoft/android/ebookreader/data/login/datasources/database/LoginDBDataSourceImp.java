package com.xsoft.android.ebookreader.data.login.datasources.database;

import com.xsoft.android.ebookreader.data.dbmanager.DBHelper;
import com.xsoft.android.ebookreader.data.dbmanager.entities.DBLogin;
import com.xsoft.android.ebookreader.data.dbmanager.persistors.LoginPersistor;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.PersistException;
import com.xsoft.android.ebookreader.repository.loginuser.datasources.LoginDBDataSource;

import java.sql.SQLException;

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
public class LoginDBDataSourceImp implements LoginDBDataSource {

    private LoginPersistor persistor;
    private DBLogin dblogin;
    private DBHelper dbhelper;

    @Inject LoginDBDataSourceImp(LoginPersistor persistor, DBLogin dbLogin, DBHelper dbHelper){
        this.persistor = persistor;
        this.dblogin = dbLogin;
        this.dbhelper = dbHelper;
    }

    @Override
    public boolean isLoginOK() throws CacheException {
        DBLogin dbl = null;
        try {
            dbl =  dbhelper.getLoginDao().queryBuilder().queryForFirst();
        } catch (SQLException e) {
            throw new CacheException();
        }
        return dbl != null;
    }

    @Override
    public String getPersistedToken() throws CacheException {
        DBLogin dbl = null;
        String persistedToken = null;
        try {
            dbl =  dbhelper.getLoginDao().queryBuilder().queryForFirst();
        } catch (SQLException e) {
            throw new CacheException();
        }

        persistedToken = dbl.getUserToken();

        return persistedToken;
    }

    @Override
    public void persist(String userToken) throws PersistException {
        try{
            DBLogin dbl = dblogin;
            dbl.setUserToken(userToken);
            dbl.setPersistedTime(System.currentTimeMillis());
            persistor.persist(dbl);
        }catch (SQLException e){
            throw new PersistException();
        }
    }

    @Override
    public void delete(String userToken) throws PersistException {
        DBLogin dbl = null;
        try {
            dbl = dbhelper.getLoginDao().queryBuilder().where().
                    eq(DBLogin.FIELD_USERTOKEN, userToken).
                    queryForFirst();
            if (dbl != null)
                dbhelper.getLoginDao().delete(dbl);
        }catch(SQLException e){
            throw new PersistException();
        }
    }

}
