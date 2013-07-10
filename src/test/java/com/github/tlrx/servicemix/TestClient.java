package com.github.tlrx.servicemix;

import com.github.tlrx.servicemix.ws.soap.SearchResponse;
import com.github.tlrx.servicemix.ws.soap.SearchWebServiceSoap;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.*;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple test client for the SOAP web service.
 */
public class TestClient {

    public static void main(String[] args) throws Exception {

        Bus bus = BusFactory.getThreadDefaultBus();
        DestinationFactoryManager dfm = bus.getExtension(DestinationFactoryManager.class);
        CustomTransportFactory customTransport = new CustomTransportFactory();
        dfm.registerDestinationFactory("http://cxf.apache.org/transports/feuvert", customTransport);
        dfm.registerDestinationFactory("http://cxf.apache.org/transports/feuvert/configuration", customTransport);

        ConduitInitiatorManager extension = bus.getExtension(ConduitInitiatorManager.class);
        extension.registerConduitInitiator("http://cxf.apache.org/transports/feuvert", customTransport);
        extension.registerConduitInitiator("http://cxf.apache.org/transports/feuvert/configuration", customTransport);

        ClientProxyFactoryBean cf = new ClientProxyFactoryBean();
        cf.setAddress("feuvert://service");
        cf.setServiceClass(SearchWebServiceSoap.class);
        SearchWebServiceSoap soapService = (SearchWebServiceSoap) cf.create();
        SearchResponse response = soapService.search("hello world");

        System.out.printf("Response contains %d search hits.",
                response.getHits() != null ? response.getHits().size() : 0);
    }

    public static class CustomTransportFactory extends LocalTransportFactory implements DestinationFactory, ConduitInitiator {
        private static final Set<String> URI_PREFIXES = new HashSet<String>();

        static {
            URI_PREFIXES.add("feuvert://");
        }

        private Set<String> uriPrefixes = new HashSet<String>(URI_PREFIXES);

        public Set<String> getUriPrefixes() {
            return uriPrefixes;
        }

        @Override
        protected Destination getDestination(EndpointInfo ei, EndpointReferenceType reference) throws IOException {
            Destination destination = super.getDestination(ei, reference);
            destination.setMessageObserver(new MessageObserver() {
                @Override
                public void onMessage(Message message) {

                    InputStream inputStream = message.getContent(InputStream.class);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    try {
                        IOUtils.copyAndCloseInput(inputStream, outputStream);
                        String xml = null;
                        String encoding = (String) message.getContextualProperty(Message.ENCODING);
                        if (!Charset.isSupported(encoding)) {
                            xml = outputStream.toString();
                        } else {
                            xml = outputStream.toString(encoding);
                        }

                        System.out.println("XML: " + xml);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return destination;
        }

        public Destination getDestination(EndpointInfo ei) throws IOException {
            Destination destination = super.getDestination(ei);
            return destination;
        }
    }


}
