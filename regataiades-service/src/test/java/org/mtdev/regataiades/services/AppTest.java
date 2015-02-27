package org.mtdev.regataiades.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.spring.integration.test.annotation.SpringAnnotationConfiguration;
import org.jboss.arquillian.spring.integration.test.annotation.SpringWebConfiguration;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.mtdev.regataiades.config.AppConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringAnnotationConfiguration(classes = { AppConfig.class })
@SpringWebConfiguration
public class AppTest extends Arquillian {

	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	@OverProtocol("Servlet 3.0")
	public static WebArchive createTestArchive() {
		/*
		WebArchive lArchive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "org.mtdev.regataiades");
		 */
	
		
		//MavenResolverSystem resolver = Maven.resolver();  
        //resolver.loadPomFromFile("pom.xml");
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
        //File[] libs  = resolver.resolve("org.mtdev.regataiades:regataiades-service").withTransitivity().asFile();
        File[] libs = pom.importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();
		//WebArchive lArchive = pom.importCompileAndRuntimeDependencies().resolve().withTransitivity().asSingle(WebArchive.class);
		WebArchive lArchive = ShrinkWrap
		        .create(WebArchive.class, "test.war")
		        .addPackages(true, "org.mtdev.regataiades")
		        .addAsLibraries(libs);
		
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
		HttpPost lPost = new HttpPost(url + "login");

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("username",
					"mishgunn@gmail.com"));
			nameValuePairs.add(new BasicNameValuePair("password", "123456"));
			lPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(lPost);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(url);

	}

}
