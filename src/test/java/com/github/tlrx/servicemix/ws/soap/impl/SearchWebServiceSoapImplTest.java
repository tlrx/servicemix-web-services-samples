package com.github.tlrx.servicemix.ws.soap.impl;

import com.github.tlrx.servicemix.exception.SearchServiceException;
import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;
import com.github.tlrx.servicemix.ws.soap.SearchResponse;
import com.github.tlrx.servicemix.ws.soap.SearchResponseFault_Exception;
import com.github.tlrx.servicemix.ws.soap.SearchWebServiceSoap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for SearchWebServiceSoapImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SearchWebServiceSoapImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    @Qualifier("searchClient")
    SearchWebServiceSoap client;

    @Autowired
    SearchWebServiceSoapImpl endpoint;

    SearchService mockSearchService;
    Collection<SearchHit> hits;

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

    @Test(expected = SOAPFaultException.class)
    public void testSearchNotValid() throws SearchResponseFault_Exception {
        when(mockSearchService.search(null)).thenThrow(new SearchServiceException());
        client.search(null);
    }

    @Test
    public void testSearchEmptyKeywords() throws SearchResponseFault_Exception {
        when(mockSearchService.search(anyString())).thenThrow(new SearchServiceException("empty keywords"));

        exception.expect(SOAPFaultException.class);
        exception.expectMessage(containsString("empty keywords"));

        client.search("");
    }

    @Test
    public void testSearch() throws SearchResponseFault_Exception {
        when(mockSearchService.search(anyString())).thenReturn(hits);

        SearchResponse response = client.search("test");
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getHits());
        Assert.assertEquals(10L, response.getHits().size());
    }
}
