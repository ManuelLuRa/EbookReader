package com.xsoft.android.ebookreader.repository.ebooklist.datasources;

import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.repository.exceptions.NetworkException;

import java.util.Collection;

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
public interface EBooksListDropBoxDataSource {
    Collection<EBook> getEBookCollection() throws NetworkException;
}
