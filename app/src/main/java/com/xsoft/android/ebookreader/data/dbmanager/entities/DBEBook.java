package com.xsoft.android.ebookreader.data.dbmanager.entities;

import com.j256.ormlite.field.DatabaseField;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import com.xsoft.android.ebookreader.data.cachingstrategy.ttl.TtlCachingObject;
import com.xsoft.android.ebookreader.domain.entities.EBook;

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
@Mappable(with = EBook.class)
public class DBEBook implements TtlCachingObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EBOOK_NAME = "ebookName";


    @DatabaseField(generatedId = true, columnName = FIELD_ID) private int id;
    @DatabaseField (columnName = FIELD_EBOOK_NAME) @Mapped public String name;
    @DatabaseField public long persistedTime;


    @Inject public DBEBook(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public long getPersistedTime() {
        return persistedTime;
    }

    public void setPersistedTime(long persistedTime) {
        this.persistedTime = persistedTime;
    }

}
