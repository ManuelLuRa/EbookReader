package com.xsoft.android.ebookreader.repository.ebooklist;

import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.domain.repository.EBooksListRepository;
import com.xsoft.android.ebookreader.repository.ebooklist.datasources.EBooksListDBDataSource;
import com.xsoft.android.ebookreader.repository.ebooklist.datasources.EBooksListDropBoxDataSource;
import com.xsoft.android.ebookreader.repository.exceptions.CacheException;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;
import com.xsoft.android.ebookreader.repository.exceptions.PersistException;

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
public class EBooksListRepositoryImp implements EBooksListRepository {

    private EBooksListDBDataSource mEBooksListDBDataSource;
    private EBooksListDropBoxDataSource mEBooksListDropBoxDataSource;

    @Inject EBooksListRepositoryImp (EBooksListDBDataSource eBooksListDBDataSource,
                                     EBooksListDropBoxDataSource eBooksListDropBoxDataSource){
        this.mEBooksListDBDataSource = eBooksListDBDataSource;
        this.mEBooksListDropBoxDataSource = eBooksListDropBoxDataSource;
    }

    @Override
    public Collection<EBook> getEBooksCollection() throws NetworkException, PersistException, CacheException {
        Collection<EBook> eBookCollection = null;
        try {
            eBookCollection = mEBooksListDBDataSource.getEBookCollection();
            if(eBookCollection == null || eBookCollection.size() == 0 ){
                eBookCollection = mEBooksListDropBoxDataSource.getEBookCollection();
                if(eBookCollection != null && eBookCollection.size() > 0)
                    for(EBook eBook : eBookCollection)
                        mEBooksListDBDataSource.persist(eBook);
            }
        }catch (NetworkException e1){
            throw new NetworkException();
        }catch (PersistException e1){
            throw new PersistException();
        }catch (CacheException e1){
            throw new CacheException();
        }

        return eBookCollection;
    }
}
