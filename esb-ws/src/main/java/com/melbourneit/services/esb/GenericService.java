package com.melbourneit.services.esb;

import org.apache.camel.ProducerTemplate;

import com.melbourneit.logger.BaseLogger;

public abstract class GenericService extends BaseLogger{
	
	protected ProducerTemplate producer;
	
}
