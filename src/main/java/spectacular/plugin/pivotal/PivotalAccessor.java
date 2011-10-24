package spectacular.plugin.pivotal;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PivotalAccessor {


    private static Log LOGGER = LogFactory.getLog(PivotalAccessor.class);

    private static final String BASE_URL = "https://www.pivotaltracker.com";
    private static final String GET_TOKEN_SERVICE_URL = "/services/v3/tokens/active";
    private static final String GET_STORIES_SERVICE_URL = "/services/v3/projects";

    private UsernamePasswordCredentials credentials;

    private String pivotalAccessToken;


    public PivotalAccessor(String username, String password) {
        this.credentials = new UsernamePasswordCredentials(username, password);
    }

    public boolean authenticate() throws PivotalException {

        String url = BASE_URL + GET_TOKEN_SERVICE_URL;

        DefaultHttpClient authClient = new DefaultHttpClient();
        List<String> authPrefs = new ArrayList<String>();
        authPrefs.add(AuthPolicy.BASIC);
        authClient.getParams().setParameter(AuthPNames.PROXY_AUTH_PREF, authPrefs);
        authClient.getCredentialsProvider().setCredentials(AuthScope.ANY, this.credentials);

        HttpGet authGet = new HttpGet(url);
        HttpResponse authResponse = null;
        String authBody = null;
        try {

            authResponse = authClient.execute(authGet);
            authBody = EntityUtils.toString(authResponse.getEntity());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(authBody.getBytes()));

            NodeList nodes = doc.getElementsByTagName("guid");
            if (nodes.getLength() == 0) throw new PivotalException("Unable to get token from XML document.");

            this.pivotalAccessToken = nodes.item(0).getTextContent();

        } catch (Exception e) {
            LOGGER.fatal("Unable to issue authentication request to Pivotal Tracker:  " + e);
            throw (new PivotalException(e));
        }

        if (this.pivotalAccessToken != null) return true;
        return (false);

    }

    public List<String> getStoryIdsByTagName(String projectId, String tagName) throws PivotalException {

        String filter = "filter=label:\"use case\"";
        try {
            filter = URLEncoder.encode(filter, "UTF-8");
        } catch (Exception e) {
            LOGGER.fatal("Unable to encode filter", e);
            throw (new PivotalException(e));
        }

        String url = BASE_URL + GET_STORIES_SERVICE_URL + "/" + projectId + "/stories?" + filter;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.addHeader("X-TrackerToken", this.pivotalAccessToken);

        List<String> ids = new LinkedList<String>();
        try {
            HttpResponse response = client.execute(get);
            String body = EntityUtils.toString(response.getEntity());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(body.getBytes()));

            NodeList nodes = doc.getElementsByTagName("id");
            if (nodes.getLength() == 0) throw new PivotalException("Unable to get token from XML document.");
            for(int i = 0 ; i < nodes.getLength() ; i++) {
                String id = nodes.item(i).getTextContent();
                ids.add(id);
            }

        } catch (Exception e) {
            LOGGER.fatal("Unable to get list of stories", e);
            throw (new PivotalException(e));
        }


        return(ids);


    }


}
