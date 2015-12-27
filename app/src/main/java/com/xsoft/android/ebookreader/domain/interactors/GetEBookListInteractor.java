package com.xsoft.android.ebookreader.domain.interactors;

import com.xsoft.android.ebookreader.domain.GetEBookList;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.domain.repository.EBooksListRepository;
import com.xsoft.android.ebookreader.executor.Executor;
import com.xsoft.android.ebookreader.executor.Interactor;
import com.xsoft.android.ebookreader.executor.MainThread;
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
public class GetEBookListInteractor implements Interactor, GetEBookList {

    private final Executor executor;
    private final MainThread mainThread;

    private Callback callback;

    private EBooksListRepository mRepository;

    @Inject GetEBookListInteractor(Executor executor, MainThread mainThread, EBooksListRepository mRepository) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.mRepository = mRepository;
    }

    @Override
    public void execute(Callback callback) {
        this.callback = callback;
        this.executor.run(this);
    }

    @Override
    public void run() { obtainEBooksList(); }

    private void obtainEBooksList(){
        Collection<EBook> eBookCollection = null;
        try {
            eBookCollection = mRepository.getEBooksCollection();
            if(eBookCollection.isEmpty() || eBookCollection == null)
                notifyConnectionError();
            else
                notifyGetEBooksListOk(eBookCollection);
        }catch (CacheException e){
            e.printStackTrace();
        }catch (NetworkException e){
            notifyConnectionError();
        }catch (PersistException e){
            e.printStackTrace();
        }
    }

    private void notifyConnectionError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void notifyGetEBooksListOk(final Collection<EBook> eBookCollection) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetEBooksList(eBookCollection);
            }
        });
    }
}
