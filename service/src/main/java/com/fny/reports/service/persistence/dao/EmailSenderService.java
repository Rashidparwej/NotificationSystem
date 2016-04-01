package com.fny.reports.service.persistence.dao;

public interface EmailSenderService {

	public void send(final String to, final String subject, final String fromEmailId, final String body)
			throws Exception;
}
