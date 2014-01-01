package com.fizzbuzz.vroom.sample.webservice.biz;

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

import com.fizzbuzz.vroom.core.biz.EntityCollectionBiz;
import com.fizzbuzz.vroom.sample.webservice.domain.Image;
import com.fizzbuzz.vroom.sample.webservice.service.datastore.entity.ImagesEntityCollection;

public class ImagesBiz
        extends EntityCollectionBiz<Image> {

    public ImagesBiz() {
        super(new ImagesEntityCollection());
    }

    public Image addImage(final Image image, final String fileName) {
        ((ImagesEntityCollection) getEntityCollection()).addImage(image, fileName);
        return image;
    }
}
