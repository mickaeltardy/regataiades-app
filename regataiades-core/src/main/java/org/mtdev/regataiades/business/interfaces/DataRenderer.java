package org.mtdev.regataiades.business.interfaces;

import java.io.Writer;
import java.util.Map;

public interface DataRenderer {

	Writer renderData(Map<Object, Object> pContext, String pMTemplatePath);

}
