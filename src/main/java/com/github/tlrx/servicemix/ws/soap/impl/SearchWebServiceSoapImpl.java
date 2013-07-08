package com.github.tlrx.servicemix.ws.soap.impl;

import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;
import com.github.tlrx.servicemix.ws.soap.Hit;
import com.github.tlrx.servicemix.ws.soap.SearchResponse;
import com.github.tlrx.servicemix.ws.soap.SearchResponseFault_Exception;
import com.github.tlrx.servicemix.ws.soap.SearchWebServiceSoap;

import javax.jws.WebParam;
import java.util.Collection;

/**
 * Implementation of the SOAP web service.
 */
public class SearchWebServiceSoapImpl implements SearchWebServiceSoap {

    private SearchService service;

    @Override
    public SearchResponse search(@WebParam(partName = "parameters", name = "searchRequest",
            targetNamespace = "http://tlrx.github.com/services/search") String keywords) throws
            SearchResponseFault_Exception {

        Collection<SearchHit> results = service.search(keywords);

        SearchResponse response = new SearchResponse();
        for (SearchHit result : results) {
            Hit hit = new Hit();
            hit.setId(result.getId());
            response.getHits().add(hit);
        }

        return response;
    }

    public void setService(SearchService service) {
        this.service = service;
    }
}
