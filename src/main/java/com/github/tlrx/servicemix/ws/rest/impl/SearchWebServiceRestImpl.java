package com.github.tlrx.servicemix.ws.rest.impl;

import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;
import com.github.tlrx.servicemix.ws.rest.SearchWebServiceRest;

import java.util.Collection;

/**
 * Implementation of the REST web service.
 */
public class SearchWebServiceRestImpl implements SearchWebServiceRest {

    private SearchService service;

    @Override
    public Collection<SearchHit> search(String keywords) {
        return service.search(keywords);
    }

    @Override
    public SearchHit searchFirst(String keywords) {
        return service.search(keywords).iterator().next();
    }

    public void setService(SearchService service) {
        this.service = service;
    }
}
