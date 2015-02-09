package org.mtdev.regataiades.business.interfaces;

public interface DataProcessor {

	public <T> T retrieveObject(Object pInputData, String pField,
			Class<T> pClass);

}
