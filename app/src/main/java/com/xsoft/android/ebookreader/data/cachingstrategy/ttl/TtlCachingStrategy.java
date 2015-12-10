package com.xsoft.android.ebookreader.data.cachingstrategy.ttl;

import com.xsoft.android.ebookreader.data.cachingstrategy.CachingStrategy;

import java.util.concurrent.TimeUnit;

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
public class TtlCachingStrategy<T extends TtlCachingObject> implements CachingStrategy<T> {

    private final long ttlMillis;

    public TtlCachingStrategy(int ttl, TimeUnit timeUnit) {
        ttlMillis = timeUnit.toMillis(ttl);
    }

    @Override public boolean isValid(T data) {
        return (data.getPersistedTime() + ttlMillis) > System.currentTimeMillis();
    }

}
