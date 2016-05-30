package com.usbus.dal.dao;

import com.usbus.dal.GenericPersistence;
import com.usbus.dal.MongoDB;
import com.usbus.dal.model.Reservation;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * Created by Lufasoch on 30/05/2016.
 */
public class ReservationDAO {
    private final Datastore ds;
    private final GenericPersistence dao;

    public ReservationDAO() {
        ds = MongoDB.instance().getDatabase();
        dao = new GenericPersistence();
    }

    public ObjectId persist(Reservation reservation) {
        return dao.persist(reservation);
    }

    public long countAll() {
        return dao.count(Reservation.class);
    }

    public long countTenant(long tenantId) {
        Query<Reservation> query = ds.createQuery(Reservation.class);
        query.criteria("tenantId").equal(tenantId);
        return query.countAll();
    }

    public Reservation getById(ObjectId id) {
        return dao.get(Reservation.class, id);
    }

    public Reservation getByBranchId(long tenantId, Long id){
        if (!(tenantId > 0) || (id == null)) {
            return null;
        }

        Query<Reservation> query = ds.createQuery(Reservation.class);

        query.and(query.criteria("id").equal(id),
                query.criteria("tenantId").equal(tenantId));

        return query.get();
    }
}
