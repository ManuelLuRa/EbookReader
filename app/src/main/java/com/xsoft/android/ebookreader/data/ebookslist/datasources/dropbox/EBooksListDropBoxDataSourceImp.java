package com.xsoft.android.ebookreader.data.ebookslist.datasources.dropbox;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.xsoft.android.ebookreader.data.dropboxutils.DropBoxApi;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.repository.ebooklist.datasources.EBooksListDropBoxDataSource;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;

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
public class EBooksListDropBoxDataSourceImp implements EBooksListDropBoxDataSource {

    private final DropBoxApi mDropBoxApi;

    private final String DROPBOX_PATH = "/";
    private final String EBOOK_EXTENSION = ".epub";


    @Inject EBook mEBook;

    @Inject
    EBooksListDropBoxDataSourceImp(DropBoxApi dropBoxApi) {
        this.mDropBoxApi = dropBoxApi;
    }

    @Override
    public Collection<EBook> getEBookCollection() throws NetworkException {
        Collection<EBook> eBookCollection = null;
        DropboxAPI.Entry rootDirEnt = null;
        try {
                rootDirEnt = mDropBoxApi.getDBApi().metadata(DROPBOX_PATH, 1000, null,
                        true, null);
            if (!rootDirEnt.isDir || rootDirEnt.contents == null)
                return null;

            for (DropboxAPI.Entry childDirEnt : rootDirEnt.contents) {
                // check if it still exists
                if (childDirEnt.isDir && !childDirEnt.isDeleted
                        && childDirEnt.contents != null) {
                    // childDirEnt contents is already null, even though there are files inside this directory
                    for (DropboxAPI.Entry fileEnt : childDirEnt.contents) {
                        if (fileEnt.fileName().contains(EBOOK_EXTENSION))
                            mEBook.setName(fileEnt.fileName());
                    }
                    eBookCollection.add(mEBook);
                }
            }
            return eBookCollection;
        } catch (DropboxException e) {
        e.printStackTrace();
        }
        return eBookCollection;
    }
}
