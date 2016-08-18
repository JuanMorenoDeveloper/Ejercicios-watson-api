package com.ibm.watson.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WatsonKeysService {
	@Value("${key.alchemy-service}")
	private String alchemyServiceKey;
	@Value("${key.visual-recognition-service}")
	private String visualRecognitionServiceKey;
	@Value("${conversation.username}")
	private String conversationServiceUsername;
	@Value("${conversation.password}")
	private String conversationServicePassword;
	@Value("${conversation.name}")
	private String conversationServiceName;

	public String getAlchemyServiceKey() {
		return alchemyServiceKey;
	}

	public void setAlchemyServiceKey(String alchemyServiceKey) {
		this.alchemyServiceKey = alchemyServiceKey;
	}

	public String getVisualRecognitionServiceKey() {
		return visualRecognitionServiceKey;
	}

	public void setVisualRecognitionServiceKey(String visualRecognitionServiceKey) {
		this.visualRecognitionServiceKey = visualRecognitionServiceKey;
	}

	public String getConversationServiceUsername() {
		return conversationServiceUsername;
	}

	public void setConversationServiceUsername(String conversationServiceUsername) {
		this.conversationServiceUsername = conversationServiceUsername;
	}

	public String getConversationServicePassword() {
		return conversationServicePassword;
	}

	public void setConversationServicePassword(String conversationServicePassword) {
		this.conversationServicePassword = conversationServicePassword;
	}

	public String getConversationServiceName() {
		return conversationServiceName;
	}

	public void setConversationServiceName(String conversationServiceName) {
		this.conversationServiceName = conversationServiceName;
	}

}
