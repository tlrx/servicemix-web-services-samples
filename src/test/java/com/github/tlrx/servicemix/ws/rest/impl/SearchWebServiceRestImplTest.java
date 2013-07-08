package com.github.tlrx.servicemix.ws.rest.impl;

import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;
import com.github.tlrx.servicemix.ws.rest.SearchWebServiceRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for SearchWebServiceRestImpl.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SearchWebServiceRestImplTest {

    @Autowired
    @Qualifier("searchClient")
    SearchWebServiceRest proxy;

    @Autowired
    SearchWebServiceRestImpl endpoint;

    SearchService mockSearchService;
    List<SearchHit> hits;

    @Before
    public void setUp() {
        mockSearchService = mock(SearchService.class);
        endpoint.setService(mockSearchService);

        hits = new ArrayList<>();
        for (long n = 0; n < 10; n++) {
            SearchHit hit = new SearchHit();
            hit.setId(n);
            hits.add(hit);
        }
    }

    @Test
    public void testSearch() {
        when(mockSearchService.search(anyString())).thenReturn(hits);

        Collection<SearchHit> response = proxy.search("test");
        Assert.assertNotNull(response);
        Assert.assertEquals(10L, response.size());
    }
}
