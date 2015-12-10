package com.xsoft.android.ebookreader.data.dbmanager.entities;

import com.j256.ormlite.field.DatabaseField;
import com.xsoft.android.ebookreader.data.cachingstrategy.ttl.TtlCachingObject;

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
public class DBLogin implements TtlCachingObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "userName";
    public static final String FIELD_USERPASS = "userPass";


    @DatabaseField(generatedId = true, columnName = FIELD_ID) private int id;
    @DatabaseField (columnName = FIELD_USERNAME) public String userName;
    @DatabaseField (columnName = FIELD_USERPASS) public String userPass;
    @DatabaseField public long persistedTime;

    @Inject DBLogin(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setPersistedTime(long persistedTime) {
        this.persistedTime = persistedTime;
    }

    @Override public long getPersistedTime() {
        return persistedTime;
    }
}
