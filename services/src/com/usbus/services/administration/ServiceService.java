package com.usbus.services.administration;

import com.usbus.bll.administration.beans.ServiceBean;
import com.usbus.commons.enums.Rol;
import com.usbus.dal.model.Service;
import com.usbus.services.auth.Secured;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Lufasoch on 08/06/2016.
 */
@Path("{tenantId}/service")
//    @Secured(Rol.ADMINISTRATOR)
public class ServiceService {
    ServiceBean ejb = new ServiceBean();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createService(Service service) {
        ObjectId oid = ejb.persist(service);
        if (oid==null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(ejb.getById(oid)).build();
    }

    @PUT
    @Path("{serviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response updateService(@PathParam("tenantId")Long tenantId, @PathParam("serviceId")Long serviceId, Service service){
        Service serviceAux = ejb.getByLocalId(tenantId, serviceId);
        service.set_id(serviceAux.get_id());
        ObjectId oid = ejb.persist(service);
        if (oid==null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(ejb.getById(oid)).build();
    }

    @GET
    @Path("{serviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response getService(@PathParam("tenantId")Long tenantId, @PathParam("serviceId") Long serviceId){

        Service serviceAux = ejb.getByLocalId(tenantId,serviceId);
        if (serviceAux == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(serviceAux).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response getServiceListByTenant(@PathParam("tenantId")long tenantId, @QueryParam("offset") int offset, @QueryParam("limit") int limit){

        List<Service> serviceList = ejb.getServicesByTenant(tenantId, offset, limit);
        if (serviceList == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(serviceList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response getServiceListByTenantAndDate(@PathParam("tenantId") long tenantId, @QueryParam("busStatus") Date time, @QueryParam("offset") int offset, @QueryParam("limit") int limit){

        List<Service> serviceList = ejb.getServicesByTenantAndDate(tenantId, time, offset, limit);
        if (serviceList == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(serviceList).build();
    }

    @DELETE
    @Path("{serviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response removeService(@PathParam("tenantId")Long tenantId, @PathParam("serviceId") Long serviceId){
        try {
            ejb.setInactive(tenantId,serviceId);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}