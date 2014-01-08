package com.andydennie.vroom.sample.webservice.domain;

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

import com.andydennie.vroom.core.domain.KeyedObject;
import com.andydennie.vroom.core.domain.LongKey;

public class Place extends KeyedObject<LongKey> {
    private String mName;
    private Location mLocation;


    public Place(final Long key, final String name, final Location location) {
        super(new LongKey(key));
        mName = name;
        mLocation = location;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(final Location location) {
        mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }
}