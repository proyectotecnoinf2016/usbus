package com.usbus.bll.administration.interfaces;

import com.usbus.dal.model.BusStop;
import org.bson.types.ObjectId;

import javax.ejb.Remote;

/**
 * Created by jpmartinez on 04/06/16.
 */
@Remote
public interface BusStopRemote {
    long countTenant(long tenantId);
    ObjectId persist(BusStop busStop);
    BusStop getById(ObjectId id);
    BusStop getByLocalId(long tenantId, Long id);
    BusStop getByName(long tenantId, String name);
    void setInactive(long tenantId, Long id);
    void setActive(long tenantId, Long id);

}
