package com.github.tlrx.servicemix.services.impl;

import com.github.tlrx.servicemix.exception.SearchServiceException;
import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of SearchService that always returns the same results list.
 */
public class SearchServiceImpl implements SearchService {

    private String identifier;

    @Override
    public Collection<SearchHit> search(String keywords) throws SearchServiceException {
        if ((keywords == null) || ("".equals(keywords))) {
            throw new SearchServiceException("Unable to search for empty keywords on " + identifier);
        }

        List<SearchHit> hits = new ArrayList<>();

        for (long n = 0; n < 10; n++) {
            SearchHit hit = new SearchHit();
            hit.setId(n);
            hits.add(hit);
        }
        return hits;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
