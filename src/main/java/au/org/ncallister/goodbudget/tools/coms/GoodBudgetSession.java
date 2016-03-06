/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.org.ncallister.goodbudget.tools.coms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author ncallister
 */
public class GoodBudgetSession
{
  private HttpClientContext sessionContext = HttpClientContext.create();
  private CloseableHttpClient client = null;
  
  private final URI BASE_URI;
  
  private static final String PATH_LOGIN = "login_check";
  
  public GoodBudgetSession() throws URISyntaxException
  {
    BASE_URI = new URI("https://goodbudget.com/");
  }
  
  public String get(String path, List<NameValuePair> parameters) 
          throws URISyntaxException, IOException
  {
    URIBuilder builder = new URIBuilder(BASE_URI);
    builder.setPath(path);
    builder.setParameters(parameters);
    HttpGet get = new HttpGet(builder.build());
    
    CloseableHttpResponse response = client.execute(get, sessionContext);
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              response.getEntity().getContent()));
      return reader.lines().collect(Collectors.joining("\n"));
    }
    finally
    {
      response.close();
    }
  }
  
  public void login(String username, String password) throws IOException
  {
    if (client == null)
    {
      client = HttpClients.createDefault();
    }
    
    HttpPost post = new HttpPost(BASE_URI.resolve(PATH_LOGIN));
//      post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    List<NameValuePair> postContent = new ArrayList<>();
    postContent.add(new BasicNameValuePair("_username", username));
    postContent.add(new BasicNameValuePair("_password", password));
    post.setEntity(new UrlEncodedFormEntity(postContent));

    CloseableHttpResponse response = client.execute(post, sessionContext);
    response.close();
  }
  
  public void logout() throws IOException
  {
    // TODO: Work out how to log-out, for now just kill the client
    client.close();
    sessionContext = HttpClientContext.create();
  }
}
