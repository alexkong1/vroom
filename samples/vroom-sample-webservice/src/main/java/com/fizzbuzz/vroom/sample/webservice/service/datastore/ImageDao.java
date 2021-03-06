package com.fizzbuzz.vroom.sample.webservice.service.datastore;

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

import com.fizzbuzz.vroom.core.domain.LongKey;
import com.fizzbuzz.vroom.core.service.datastore.InboundRef;
import com.fizzbuzz.vroom.extension.googlecloudstorage.domain.GcsFile;
import com.fizzbuzz.vroom.extension.googlecloudstorage.service.datastore.GcsDao;
import com.googlecode.objectify.annotation.Entity;

@Entity
@InboundRef(daoClass = UserDao.class, fieldName = "profileImage")
public class ImageDao extends GcsDao<GcsFile> {
    @Override
    public GcsFile toDomainObject() {
        return new GcsFile(new LongKey(getId()), getBucketName(), getFileName());
    }
}
