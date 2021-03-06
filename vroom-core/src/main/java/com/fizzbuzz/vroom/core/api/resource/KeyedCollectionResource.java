package com.fizzbuzz.vroom.core.api.resource;

/*
 * Copyright (c) 2014 Fizz Buzz LLC
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

import com.fizzbuzz.vroom.core.biz.ICollectionBiz;
import com.fizzbuzz.vroom.core.domain.VroomCollection;
import com.fizzbuzz.vroom.core.domain.KeyedObject;
import org.restlet.resource.ResourceException;

public abstract class KeyedCollectionResource<
        DC extends VroomCollection<KO>,
        KO extends KeyedObject,
        CB extends ICollectionBiz<KO>>
        extends VroomCollectionResource<DC, KO, CB> {

    private Class<? extends KeyedResource> mElementResourceClass;

    @Override
    protected String getElementUri(final KO element) {
        return ResourceRegistry.getCanonicalUri(mElementResourceClass, element.getKeyAsString());
    }

    protected void doInit(final Class<DC> collectionClass,
                          final CB collectionBiz,
                          final Class<? extends KeyedResource> elementResourceClass)
            throws ResourceException {
        super.doInit(collectionClass, collectionBiz);
        mElementResourceClass = elementResourceClass;
    }
}
