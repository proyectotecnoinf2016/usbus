package com.usbus.dal;


import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;


/**
 * Created by JuanPablo on 4/27/2016.
 */
public class GenericPersistence {
    private final Datastore mongoDatastore;

    public GenericPersistence() {
        mongoDatastore = MongoDB.instance().getDatabase();
    }

    public <E extends BaseEntity> String persist(E entity) {
        mongoDatastore.save(entity);
        return entity.get_id();
    }

    public <E extends BaseEntity> long count(Class<E> clazz) {
        if (clazz == null) {
            return 0;
        }

        return mongoDatastore.find(clazz).countAll();
    }

    public <E extends BaseEntity> E get(Class<E> clazz, final String id) {
        if ((clazz == null) || (id == null)) {
            return null;
        }

        return mongoDatastore.find(clazz).field("_id").equal(id).get();
    }

    public <E extends BaseEntity>  void remove(Class<E> clazz, final String id){
        if ((clazz != null) && (id != null)) {
            mongoDatastore.delete(mongoDatastore.find(clazz).field("_id").equal(id));
        }
    }
}
