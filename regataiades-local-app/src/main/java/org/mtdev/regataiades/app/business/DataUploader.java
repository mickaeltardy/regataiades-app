package org.mtdev.regataiades.app.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataUploader {

	public boolean sendData(Object pData, String pServiceUrl) {

		if (pData != null && !StringUtils.isEmpty(pServiceUrl)) {
			try {
				HttpClient httpclient = HttpClientBuilder.create().build();
				HttpPost httpPost = new HttpPost(pServiceUrl);

				ObjectMapper lMapper = new ObjectMapper();
				String lStringedData = null;
				try {
					lStringedData = lMapper.writeValueAsString(pData);

				} catch (IOException e) {
					e.printStackTrace();
				}

				StringEntity lEntity = null;
				lEntity = new StringEntity(lStringedData, "UTF-8");
				lEntity.setContentType("application/json");
				httpPost.setEntity(lEntity);

				HttpResponse response = httpclient.execute(httpPost);
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					System.out.println(line);

				}
			} catch (Exception lE) {

			}
		}

		return false;

	}

}
