package org.mtdev.regataiades.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.spring.integration.test.annotation.SpringAnnotationConfiguration;
import org.jboss.arquillian.spring.integration.test.annotation.SpringWebConfiguration;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.mtdev.regataiades.config.AppConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

//@SpringAnnotationConfiguration(classes = { AppConfig.class })
//@SpringWebConfiguration
public class AppTest 
//extends Arquillian 
{
/*
	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	@OverProtocol("Servlet 3.0")
	public static WebArchive createTestArchive() {
		WebArchive lArchive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "org.mtdev.regataiades");

		System.out.println(lArchive.toString(true));

		return lArchive;
	}

	@Test
	@RunAsClient
	public void validator() {
		System.out.println("Checking url from object");
		System.out.println(url);

		Assert.assertNotNull(url);
	}

	@Test
	@RunAsClient
	public void checkOutput() {
		System.out.println("Checking url from object");
		System.out.println(url);
		HttpGet lGet = new HttpGet(url
				+ "/usermgt/create?name=ttt&password=yyy");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse lResponse = httpclient.execute(lGet);
			InputStream in = lResponse.getEntity().getContent();
			StringBuilder sb = new StringBuilder();
			BufferedReader r = new BufferedReader(new InputStreamReader(in),
					1000);
			for (String line = r.readLine(); line != null; line = r.readLine()) {
				sb.append(line);
			}
			in.close();

			System.out.println(sb.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(url);

	}
	*/
}
