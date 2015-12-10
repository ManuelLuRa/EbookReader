package com.xsoft.android.ebookreader.data.dbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xsoft.android.ebookreader.data.dbmanager.entities.DBLogin;
import com.xsoft.android.ebookreader.dependencies.scoped.DataBaseName;

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
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    @Inject DBHelper(Context appContext, @DataBaseName String databaseName) {
        super(appContext, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DBLogin.class);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to create tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DBLogin.class, true);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to drop tables", e);
        }
    }

    public Dao<DBLogin, Integer> getLoginDao(){
        try {
            return getDao(DBLogin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
