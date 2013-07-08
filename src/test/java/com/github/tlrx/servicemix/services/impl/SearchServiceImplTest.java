package com.github.tlrx.servicemix.services.impl;

import com.github.tlrx.servicemix.exception.SearchServiceException;
import com.github.tlrx.servicemix.services.SearchHit;
import com.github.tlrx.servicemix.services.SearchService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

import static org.hamcrest.core.StringContains.containsString;

/**
 * Test class of SearchServiceImpl
 */

public class SearchServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    SearchService service = new SearchServiceImpl();

    @Test
    public void testSearch() {
        Collection<SearchHit> hits = service.search("*");
        Assert.assertNotNull(hits);
        Assert.assertNotNull(hits);
    }

    @Test
    public void testEmptySearch() {
        exception.expect(SearchServiceException.class);
        exception.expectMessage(containsString("empty keywords"));
        Collection<SearchHit> hits = service.search("");
    }
}
