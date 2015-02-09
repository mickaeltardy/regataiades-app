package org.mtdev.regataiades.business;

import java.io.IOException;
import java.util.Map;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataProcessorImpl implements DataProcessor {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T retrieveObject(Object pInputData, String pField,
			Class<T> pClass) {
		ObjectMapper lMapper = new ObjectMapper();

		try {
			T lObject = lMapper.readValue(lMapper
					.writeValueAsString(((Map<Object, Object>) pInputData)
							.get(pField)), pClass);

			return lObject;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
