package com.melbourneit.utils.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * wrapping castor in the calling bundle 
 */
public class XMLContextWrapper extends org.exolab.castor.xml.XMLContext { 

	private static final Logger LOG = LoggerFactory.getLogger(XMLContextWrapper.class);

	public XMLContextWrapper () {
		super();
		LOG.debug("XMLContextWrapper classLoader: " + getClass().getClassLoader());
		getInternalContext().setClassLoader(getClass().getClassLoader());
	}
}
