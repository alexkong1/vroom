package com.fizzbuzz.vroom.sample.webservice.domain;

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

import com.fizzbuzz.vroom.core.domain.EntityObject;
import com.fizzbuzz.vroom.core.domain.LongKey;

import java.util.Date;

public class User extends EntityObject {
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private LongKey mProfileImageKey;
    private LongKey mHomeKey;

    public User(final LongKey key, final String firstName, final String lastName, final String email,
                final LongKey profileImageKey, final LongKey homeKey) {
        super(key);
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mProfileImageKey = profileImageKey;
        mHomeKey = homeKey;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(final String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(final String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(final String email) {
        mEmail = email;
    }

    public LongKey getProfileImageKey() {
        return mProfileImageKey;
    }

    public void setProfileImageKey(final LongKey profileImageKey) {
        mProfileImageKey = profileImageKey;
    }

    public LongKey getHomeKey() {
        return mHomeKey;
    }

    public void setHomeKey(final LongKey homeKey) {
        mHomeKey = homeKey;
    }

    public static class UserLogEntry extends EntityObject {
        public enum UserEventType {
            USER_CREATED,
            USER_PASSWORD_CHANGED,
            USER_EMAIL_CHANGED
        }

        LongKey mUserKey;
        UserEventType mEventType;
        Date mDateTime;

        public UserLogEntry(final LongKey key, final LongKey userKey, final UserEventType eventType,
                            final Date dateTime) {
            super(key);
            mUserKey = userKey;
            mEventType = eventType;
            mDateTime = dateTime;
        }

        public LongKey getUserKey() {
            return mUserKey;
        }

        public void setUserKey(final LongKey userKey) {
            mUserKey = userKey;
        }

        public UserEventType getEventType() {
            return mEventType;
        }

        public void setEventType(final UserEventType eventType) {
            mEventType = eventType;
        }

        public Date getDateTime() {
            return mDateTime;
        }

        public void setDateTime(final Date dateTime) {
            mDateTime = dateTime;
        }
    }
}
