package com.nuc.zp.webdav;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.github.sardine.impl.SardineException;
import com.github.sardine.impl.SardineImpl;
import com.github.sardine.impl.handler.MultiStatusResponseHandler;
import com.github.sardine.impl.methods.HttpSearch;
import com.github.sardine.model.Multistatus;
import com.github.sardine.model.Response;
import com.github.sardine.model.SearchRequest;
import com.github.sardine.util.SardineUtil;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestSardine {

    /**
     * @param args
     * @throws SardineException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        SardineImpl sardine = (SardineImpl) SardineFactory.begin("admin", "admin");

//        if (sardine.exists("http://39.107.235.225:8090/remote.php/dav/files/admin")) {
//            System.out.println("/content/dam folder exists");
//        }

//        sardine.createDirectory("http://39.107.235.225:8090/remote.php/dav/files/admin/testfolder/");

//        InputStream fis = new FileInputStream(new File("img12.jpg"));
//        sardine.put("http://192.168.1.71:4502/crx/repository/crx.default/content/dam/img12.jpg", fis);

        List<DavResource> resources = sardine.getResources("http://100.2.2.230:8000/remote.php/dav/files/admin");
        for (DavResource res : resources) {
            System.out.println("--" + res); // calls the .toString() method.
//        }

            String language = "basicsearch";
            String data = "<select>\n" +
                    "             <prop>\n" +
                    "                 <fileid/>\n" +
                    "                 <displayname/>\n" +
                    "                 <getcontenttype/>\n" +
                    "                 <getetag/>\n" +
                    "                 <size/>\n" +
                    "             </prop>\n" +
                    "         </select>\n" +
                    "         <from>\n" +
                    "             <scope>\n" +
                    "                 <href>/files/admin</href>\n" +
                    "                 <depth>infinity</depth>\n" +
                    "             </scope>\n" +
                    "         </from>\n" +
                    "         <where>\n" +
                    "             <like>\n" +
                    "                 <prop>\n" +
                    "                     <getcontenttype/>\n" +
                    "                 </prop>\n" +
                    "                 <literal>text/%</literal>\n" +
                    "             </like>\n" +
                    "         </where>\n" +
                    "         <orderby/>";
            String url = "http://192.168.0.11:8012/remote.php/dav/";
//        List<DavResource> search = sardine.search("http://192.168.0.11:8012/remote.php/dav/", "basicsearch",
//                "<select>\n" +
//                        "             <prop>\n" +
//                        "                 <fileid/>\n" +
//                        "                 <displayname/>\n" +
//                        "                 <getcontenttype/>\n" +
//                        "                 <getetag/>\n" +
//                        "                 <size/>\n" +
//                        "             </prop>\n" +
//                        "         </select>\n" +
//                        "         <from>\n" +
//                        "             <scope>\n" +
//                        "                 <href>/files/admin</href>\n" +
//                        "                 <depth>infinity</depth>\n" +
//                        "             </scope>\n" +
//                        "         </from>\n" +
//                        "         <where>\n" +
//                        "             <like>\n" +
//                        "                 <prop>\n" +
//                        "                     <getcontenttype/>\n" +
//                        "                 </prop>\n" +
//                        "                 <literal>text/%</literal>\n" +
//                        "             </like>\n" +
//                        "         </where>\n" +
//                        "         <orderby/>");
//        System.out.println(search);


//        HttpEntityEnclosingRequestBase search = new HttpSearch(url);
//        SearchRequest searchBody = new SearchRequest(language, data);
//        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                " <searchrequest xmlns=\"DAV:\">\n" +
//                "     <basicsearch>\n"+ data +"</basicsearch>\n" +
//                "</searchrequest>";
//        search.setEntity(new StringEntity(body, UTF_8));
//        Class<? extends SardineImpl> c1 = sardine.getClass();
//        Method dm = null;
//        try {
//            Method[] methods = c1.getDeclaredMethods();
//            dm = c1.getDeclaredMethod("execute", HttpRequestBase.class, ResponseHandler.class );
//            dm.setAccessible(true);
//            HttpRequestBase req = search;
//            ResponseHandler r = new MultiStatusResponseHandler();
//            Multistatus multistatus = (Multistatus)dm.invoke(sardine, req, r);
//            List<Response> responses = multistatus.getResponse();
//            List<DavResource> resources = new ArrayList<DavResource>(responses.size());
//            for (Response response : responses) {
//                try {
//                    resources.add(new DavResource(response));
//                } catch (URISyntaxException e) {
////                log.warning(String.format("Ignore resource with invalid URI %s", response.getHref().get(0)));
//                }
//            }
//            System.out.println(responses);
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }


//        Multistatus multistatus = sardine.execute(search, new MultiStatusResponseHandler());

        }
    }

}

