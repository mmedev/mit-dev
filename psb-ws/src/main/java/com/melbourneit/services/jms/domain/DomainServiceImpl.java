package com.melbourneit.services.jms.domain;

import com.melbourneit.model.jms.JMSReqRes;
import com.melbourneit.model.jms.JmsResponse;
import com.melbourneit.model.jms.domain.CheckDomainRequest;
import com.melbourneit.services.jms.JMSRequestService;
import com.melbourneit.services.psb.PSBServiceManagement;
import com.melbourneit.utils.UserDetails;
import com.melbourneit.utils.XStreamUtil;
import com.melbourneit.utils.jms.JMSResponseMessage;
import com.melbourneit.utils.jms.JMSResponseStatus;

public class DomainServiceImpl extends JMSRequestService implements DomainService{

    private PSBServiceManagement serviceManagement;
    private String username;
    private String password;
    
    public String checkDomain(String request){	
       CheckDomainRequest jmsReq = (CheckDomainRequest) getJMSRequest(request);
       String resStr = serviceManagement.checkDomainName(jmsReq.getName(), new UserDetails(username, password), "", "");
       return XStreamUtil.toXML(new JMSReqRes(null, new JmsResponse(JMSResponseStatus.PASSED, JMSResponseMessage.OK.toString(), resStr)), JMSReqRes.class);
    }

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServiceManagement(PSBServiceManagement serviceManagement) {
		this.serviceManagement = serviceManagement;
	}
}
