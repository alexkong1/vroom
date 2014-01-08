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

import com.andydennie.vroom.core.domain.DomainCollection;
import com.andydennie.vroom.core.domain.KeyedObject;
import com.andydennie.vroom.core.domain.LongKey;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;

import static com.andydennie.vroom.core.service.datastore.OfyManager.ofy;

/**
 * Base class for entity collections.
 */
public abstract class EntityCollection<
        KO extends KeyedObject<LongKey>,
        DAO extends VroomDao<KO>>
        implements IEntityCollection<KO> {

    private final Class<KO> mDomainElementClass;
    private final Class<DAO> mElementDaoClass;
    private final VroomEntity<KO, DAO> mElementEntity;

    protected EntityCollection(final Class<KO> domainElementClass,
                               final Class<DAO> elementDaoClass,
                               final VroomEntity<KO, DAO> elementEntity) {
        mDomainElementClass = domainElementClass;
        mElementDaoClass = elementDaoClass;
        mElementEntity = elementEntity;
    }

    @Override
    public DomainCollection<KO> getElements() {
        List<DAO> daos = ofy().load().type(mElementDaoClass).list();
        return toDomainCollection(daos);
    }

    @Override
    public KO addElement(final KO keyedObject) {

        //TODO: addElement() - seriously consider making this method return void.

        mElementEntity.create(keyedObject);
        return keyedObject;
    }

    public Class<KO> getDomainElementClass() {
        return mDomainElementClass;
    }

    @Override
    public void deleteAll() {
        Iterable<Key<DAO>> keys = ofy().load().type(getElementDaoClass()).keys();
        deleteEntitiesByKey(keys);
    }

    @Override
    public void delete(List<KO> keyedObjectCollection) {
        List<Key<DAO>> keys = new ArrayList<>();
        for (KO keyedObject : keyedObjectCollection) {
            keys.add(Key.create(getElementDaoClass(), keyedObject.getKey().toString()));
        }
        deleteEntitiesByKey(keys);
    }

    /**
     * Instantiates a DAO from a PersistentObject
     *
     * @param keyedObject the PersistentObject
     * @return the DAO
     */
    protected DAO createElementDao(KO keyedObject) {
        if (keyedObject.getKey().get() != null)
            throw new IllegalArgumentException("Cannot create an entity for a domain object with an existing ID; the " +
                    "ID will be assigned by the persistence layer on the initial save operation.");

        // instantiate the appropriate kind of DAO, using the constructor that takes a single domain object argument
        DAO result = mElementEntity.createDao(keyedObject);


        return result;
    }

    /**
     * Saves a new VroomEntity in the datastore
     *
     * @param dao
     */
    protected void saveElementEntity(DAO dao) {

    }

    protected DomainCollection<KO> toDomainCollection(List<DAO> daoCollection) {
        DomainCollection<KO> domainCollection = new DomainCollection<>();
        for (DAO dao : daoCollection) {
            domainCollection.add(dao.toDomainObject());
        }
        return domainCollection;
    }

    protected Class<DAO> getElementDaoClass() {
        return mElementDaoClass;
    }

    private void deleteEntitiesByKey(Iterable<Key<DAO>> keys) {
        ofy().delete().keys(keys);
    }
}