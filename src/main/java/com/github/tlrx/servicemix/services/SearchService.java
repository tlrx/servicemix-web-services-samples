package com.github.tlrx.servicemix.services;

import com.github.tlrx.servicemix.exception.SearchServiceException;

import java.util.Collection;
import java.util.List;

/**
 * Search service interface.
 */
public interface SearchService {

    /**
     * Search for keywords and return search results.
     *
     * @param keywords the keywords to search for.
     * @return a list of search hits.
     * @throws SearchServiceException
     */
    Collection<SearchHit> search(String keywords) throws SearchServiceException;
}
