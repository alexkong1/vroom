package com.fizzbuzz.vroom.core.biz;

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

import com.fizzbuzz.vroom.core.domain.IdObject;
import com.fizzbuzz.vroom.core.persist.CollectionPersist;

import java.util.List;

public class PersistentCollectionBiz<PO extends IdObject> implements CollectionBiz<PO>{
    private CollectionPersist<PO> mPersist;

    public PersistentCollectionBiz(CollectionPersist<PO> persist) {
        mPersist = persist;
    }

    @Override
    public List<PO> getElements() {
        return mPersist.getDomainElements();
    }

    CollectionPersist<PO> getPersist() {
        return mPersist;
    }

    @Override
    public PO add(final PO domainObject) {
        domainObject.validate();
        return (PO)getPersist().addElement(domainObject);
    }

    @Override
    public void deleteAll() {
        getPersist().deleteAll();
    }

    @Override
    public void delete(List<PO> domainObjects) {
        getPersist().delete(domainObjects);
    }
}
