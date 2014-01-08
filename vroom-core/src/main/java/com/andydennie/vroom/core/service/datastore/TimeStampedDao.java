package com.andydennie.vroom.core.service.datastore;

/*
 * Copyright (c) 2014 Andy Dennie
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

import com.andydennie.vroom.core.domain.KeyedObject;
import com.andydennie.vroom.core.domain.LongKey;

import java.util.Date;

public abstract class TimeStampedDao<KO extends KeyedObject<LongKey>> extends VroomDao<KO> {
    private Date createdDate;
    private Date modifiedDate;

    // no-arg constructor needed by Objectify
    protected TimeStampedDao() {
    }

    protected TimeStampedDao(final KO keyedObject) {
        super(keyedObject);
    }

    protected Date getCreatedDate() {
        return createdDate;
    }

    protected void setCreatedDate(final Date createdDate) {

        this.createdDate = createdDate;
    }

    protected Date getModifiedDate() {
        return modifiedDate;
    }

    protected void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
