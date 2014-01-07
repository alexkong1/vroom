package com.fizzbuzz.vroom.core.service.datastore;


/*
 * Copyright (c) 2013 Fizz Buzz LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fizzbuzz.vroom.core.domain.LongKey;
import com.fizzbuzz.vroom.core.service.datastore.entity.BaseEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;

public class BaseEntityTest {
    TestKeyedObject mKeyedObject;
    TestDao mTestDao;
    BaseEntity<TestKeyedObject, TestDao> mTestEntity;

    @Rule
    public DataStoreRule dsRule = new DataStoreRule();

    @Rule
    public ObjectifyRule ofyRule = new ObjectifyRule();

    @Before
    public void setup() {
        mKeyedObject = new TestKeyedObject();
        mTestDao = new TestDao();
        OfyManager.getOfyService().factory().register(TestDao.class);
        mTestEntity = new BaseEntity<TestKeyedObject, TestDao>(TestKeyedObject.class, TestDao.class) {};

    }

    @Test
    public void createFailsWhenKeyedObjectAlreadyHasAKey() {
        // given a keyed object with a key
        mKeyedObject.setKey(new LongKey(0L));

        // when attempting to create an entity for that keyed object
        when(mTestEntity).create(mKeyedObject);

        // then an IllegalArgumentException should be thrown
        then(caughtException())
                .isInstanceOf(IllegalArgumentException.class);
    }
}