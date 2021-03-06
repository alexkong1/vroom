package com.fizzbuzz.vroom.extension.googlecloudstorage.domain;

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

import com.fizzbuzz.vroom.core.domain.File;
import com.fizzbuzz.vroom.core.domain.LongKey;

public class GcsFile extends File implements IGcsFile {

    private String mBucketName;
    private String mGcsFileName;

    public GcsFile(final LongKey key, final String bucketName, final String fileName) {
        super(key, fileName);
        mBucketName = bucketName;
    }

    @Override
    public String getGcsFileName() {
        return mGcsFileName;
    }

    @Override
    public void setGcsFileName(final String gcsFileName) {
        mGcsFileName = gcsFileName;
    }

    @Override
    public String getBucketName() {
        return mBucketName;
    }

    @Override
    public void setBucketName(final String bucketName) {
        mBucketName = bucketName;
    }
}
