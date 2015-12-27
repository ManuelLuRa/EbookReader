package com.xsoft.android.ebookreader.data.ebookslist.datasources.database;

import com.mobandme.android.transformer.Transformer;
import com.xsoft.android.ebookreader.data.dbmanager.DBHelper;
import com.xsoft.android.ebookreader.data.dbmanager.entities.DBEBook;
import com.xsoft.android.ebookreader.data.dbmanager.persistors.EBookPersistor;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.repository.ebooklist.datasources.EBooksListDBDataSource;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.PersistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
public class EBooksListDBDataSourceImp implements EBooksListDBDataSource {

    private EBookPersistor persistor;
    private DBEBook dbebook;
    private DBHelper dbhelper;
    private static final Transformer transformer = new Transformer.Builder().build(DBEBook.class);

    @Inject
    EBooksListDBDataSourceImp(EBookPersistor persistor, DBEBook dbeBook, DBHelper dbHelper){
        this.persistor = persistor;
        this.dbebook = dbeBook;
        this.dbhelper = dbHelper;
    }

    @Override
    public Collection<EBook> getEBookCollection() throws CacheException {
        Collection<EBook> eBookList = new ArrayList<EBook>();
        try {
            Collection<DBEBook> eBookCollection = dbhelper.getEBookDao().queryForAll();
            if (eBookCollection != null){
                eBookList = new ArrayList<>();
                for(DBEBook dbeBook : eBookCollection)
                    eBookList.add(transformer.transform(dbeBook, EBook.class));
            }
        }catch(SQLException e){
            throw new CacheException();
        }
        return eBookList;
    }

    @Override
    public void persist(EBook eBook) throws PersistException {
        try{
            DBEBook dbe = transformer.transform(eBook, DBEBook.class);
            dbe.setPersistedTime(System.currentTimeMillis());
            persistor.persist(dbe);
        }catch (SQLException e){
            throw new PersistException();
        }
    }

    @Override
    public void delete(String eBookName) throws PersistException {
        DBEBook dbe = null;
        try {
            dbe = dbhelper.getEBookDao().queryBuilder().where().
                    eq(DBEBook.FIELD_EBOOK_NAME, eBookName).
                    queryForFirst();
            if (dbe != null)
                dbhelper.getEBookDao().delete(dbe);
        }catch(SQLException e){
            throw new PersistException();
        }
    }
}
