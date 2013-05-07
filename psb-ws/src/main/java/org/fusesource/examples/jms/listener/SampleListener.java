package org.fusesource.examples.jms.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleListener implements MessageListener{

	protected static final Logger logger = LoggerFactory.getLogger(SampleListener.class);
	
	@Override
	public void onMessage(Message msg) {
		
		long timeTillExpiry = 0;
        String correlationId = "not-set";
        try{
            timeTillExpiry = msg.getJMSExpiration() - System.currentTimeMillis();
            correlationId = msg.getJMSCorrelationID();
            logger.info(correlationId," Time to expire: " + (timeTillExpiry / 1000.0F) + " seconds");
        }
        catch (JMSException e){
        	logger.info(correlationId," Error getting jms expiry and/or correlationId: " + e);
        } 
        
      //before we attempt to go any further we attempt to check if the message is already expired
        if (timeTillExpiry < 0){
        	logger.info(correlationId, " MessageAlready expired for correlationID: " + correlationId + " expired: " + (timeTillExpiry / -1000.0F) + 
                    " seconds ago, not going ahead with handling the request");
            return;
        }
        
        //we can only handle MapMessages for now
        if (!(msg instanceof MapMessage)){
        	logger.info(correlationId, " Received non-map message, not handling it. Received type is: " + msg.getClass().getName());
            return;
        }
        
        MapMessage requestMessage = (MapMessage)msg;
        
        Map<String,String> requestMessageMap = new HashMap<String,String>();

        //well for now we just reply that we handled the message
        try{
            Enumeration<String> mapNames = requestMessage.getMapNames();
            
            while (mapNames.hasMoreElements()){
                String key = mapNames.nextElement();
                requestMessageMap.put(key, (String)requestMessage.getObject(key));
            }

            Map<String,String> replyResponseMessageMap = null;
            long startTime = System.currentTimeMillis();
            try{
                //replyResponseMessageMap = asyncRequestMessageHandler.handleRequest(correlationId, inRequestMessage, requestMessageMap);
            }
            finally{
            	logger.info(correlationId, "It took: " + ((System.currentTimeMillis() - startTime) / 1000.0F) + " seconds to handleRequest");
            }
             
//            jmsTemplate.convertAndSend(requestMessage.getJMSReplyTo(), replyResponseMessageMap, 
//                    new SetCorrelationMessageResponsePostProcessor(correlationId));
        }
        catch (JMSException e){
        	logger.info(" Ooops error to reply to message: " + requestMessage);
        }
	}

}
