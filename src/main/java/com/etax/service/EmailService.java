package com.etax.service;

import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface EmailService {
	Map<String, String> sendEmailsWithAttachments(List<String> cifs, YearMonth startYearMonth, YearMonth endYearMonth) throws MessagingException, IOException;
}

