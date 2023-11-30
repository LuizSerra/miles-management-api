package com.netmaxi.mm.api.messageSender.application;

import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.messageSender.adapters.SendMessageGateway;
import com.netmaxi.mm.api.messageSender.core.SendMessageUC;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SendMessageService implements SendMessageUC {

	private final SendMessageGateway sendMessageGateway;
	
	@Override
	public void sendMessage(String message) {
		this.sendMessageGateway.sendMessage(message);

	}

}
