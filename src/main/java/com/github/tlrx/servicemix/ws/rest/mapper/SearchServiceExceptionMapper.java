package com.github.tlrx.servicemix.ws.rest.mapper;

import com.github.tlrx.servicemix.exception.SearchServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * SearchServiceException exception mapper.
 */
@Provider
public class SearchServiceExceptionMapper implements ExceptionMapper<SearchServiceException> {

    @Override
    public Response toResponse(SearchServiceException exception) {
        return Response.noContent().build();
    }
}
