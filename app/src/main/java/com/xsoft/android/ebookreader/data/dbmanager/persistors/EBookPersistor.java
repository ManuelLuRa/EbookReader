package com.xsoft.android.ebookreader.data.dbmanager.persistors;

import com.xsoft.android.ebookreader.data.dbmanager.DBHelper;
import com.xsoft.android.ebookreader.data.dbmanager.entities.DBEBook;

import java.sql.SQLException;

import javax.inject.Inject;

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
public class EBookPersistor implements Persistor<DBEBook>  {

    private DBHelper dbhelper;

    @Inject EBookPersistor (DBHelper dbHelper){ this.dbhelper = dbHelper; }

    @Override
    public void persist(DBEBook data) throws SQLException {
        if(data != null) dbhelper.getEBookDao().create(data);
    }
}
