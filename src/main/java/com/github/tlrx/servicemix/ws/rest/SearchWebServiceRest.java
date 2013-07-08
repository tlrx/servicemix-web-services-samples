package com.github.tlrx.servicemix.ws.rest;

import com.github.tlrx.servicemix.services.SearchHit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Implementation of the REST web service.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SearchWebServiceRest {

    @POST
    @Path("/search")
    public Collection<SearchHit> search(String keywords);

    @GET
    @Path("/search/{keywords}")
    public SearchHit searchFirst(@PathParam("keywords") String keywords);
}
