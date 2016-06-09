package com.usbus.services.administration;

import com.usbus.bll.administration.beans.JourneyBean;
import com.usbus.commons.enums.JourneyStatus;
import com.usbus.commons.enums.Rol;
import com.usbus.dal.model.Journey;
import com.usbus.services.auth.Secured;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Lufasoch on 05/06/2016.
 */
@Path("{tenantId}/journey")
//    @Secured(Rol.ADMINISTRATOR)
public class JourneyService {
    JourneyBean ejb = new JourneyBean();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJourney(Journey journey01){
        ObjectId oid = ejb.persist(journey01);
        if (oid==null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(ejb.getById(oid)).build();
    }

    @PUT
    @Path("{journeyId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response updateJourney(@PathParam("tenantId")Long tenantId, @PathParam("journeyId")Long journeyId, Journey journey){
        Journey journeyAux = ejb.getByLocalId(tenantId, journeyId);
        journey.set_id(journeyAux.get_id());
        ObjectId oid = ejb.persist(journey);
        if (oid==null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(ejb.getById(oid)).build();
    }

    @GET
    @Path("{journeyId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response getJourney(@PathParam("tenantId")Long tenantId, @PathParam("journeyId") Long journeyId){

        Journey journeyAux = ejb.getByLocalId(tenantId, journeyId);
        if (journeyAux == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(journeyAux).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured({Rol.ADMINISTRATOR, Rol.CLIENT})
    public Response getJourneyList(@PathParam("tenantId")Long tenantId, @QueryParam("journeyStatus") JourneyStatus journeyStatus, @QueryParam("offset") int offset, @QueryParam("limit") int limit){

        List<Journey> journeyList = ejb.JourneysByTenantIdAndStatus(tenantId, journeyStatus, offset, limit);
        if (journeyList == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(journeyList).build();
    }

    @DELETE
    @Path("{journeyId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(Rol.ADMINISTRATOR)
    public Response removeJourney(@PathParam("tenantId")Long tenantId, @PathParam("journeyId") Long journeyId){
        try {
            ejb.setInactive(tenantId, journeyId); //POR AHORA SOLO IMPLEMENTAMOS UN BORRADO LÓGICO.
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}