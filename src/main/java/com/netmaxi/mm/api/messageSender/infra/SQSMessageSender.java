package com.netmaxi.mm.api.messageSender.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.netmaxi.mm.api.messageSender.adapters.SendMessageGateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class SQSMessageSender implements SendMessageGateway {

	@Value("${message.queue.topic}")
	private String messageQueueTopic;

	private final AmazonSQS amazonSQSClient;

	@Override
	public void sendMessage(String message) {
		try {
			GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic);
			log.info("Reading SQS Queue done: URL {}", queueUrl.getQueueUrl());
			amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), message);
		} catch (QueueDoesNotExistException | InvalidMessageContentsException e) {
			log.error("Queue does not exist {}", e.getMessage());
		}
	}

}
