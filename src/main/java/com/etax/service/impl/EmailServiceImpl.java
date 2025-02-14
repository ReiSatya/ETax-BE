package com.etax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.etax.model.Content;
import com.etax.model.LogPajak;
import com.etax.model.Nasabah;
import com.etax.repo.ContentRepo;
import com.etax.repo.LogPajakRepo;
import com.etax.repo.NasabahRepo;
import com.etax.service.EmailService;
import com.etax.service.ParameterService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContentRepo contentRepo;

    @Autowired
    private NasabahRepo nasabahRepository;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private LogPajakRepo logPajakRepo;

    @Override
    public Map<String, String> sendEmailsWithAttachments(List<String> cifs, YearMonth startYearMonth, YearMonth endYearMonth) throws MessagingException, IOException {
        Content emailContent = contentRepo.findById("CID001")
                .orElseThrow(() -> new RuntimeException("Content not found for ID"));

        String pdfDirectory = parameterService.getPdfPathReport();
        Map<String, String> statusMap = new HashMap<>();

        for (String cif : cifs) {
        	System.out.println("ini cif yang "+cif);
            Nasabah nasabah = getNasabahByCif(cif);
            String nasabahName = nasabah.getNama();
            String email = nasabah.getEmail();

            List<File> pdfFilesToSend = new ArrayList<>();

            for (YearMonth ym = startYearMonth; !ym.isAfter(endYearMonth); ym = ym.plusMonths(1)) {
                String logPeriod = String.format("%02d%04d", ym.getMonthValue(), ym.getYear());
                List<LogPajak> logPajaks = logPajakRepo.findByLogCifAndLogPeriod(cif, logPeriod);

                if (!logPajaks.isEmpty()) {
                    for (LogPajak logPajak : logPajaks) {
                        if ("R".equals(logPajak.getLogStatus())) {
                        	System.out.println("masuk if");
                            List<File> pdfFiles = retrievePdfFiles(pdfDirectory, logPajak, nasabahName);
                            if (!pdfFiles.isEmpty()) {
                                pdfFilesToSend.addAll(pdfFiles);
                                updateLogStatusToY(logPajak);
                            }
                        }
                    }
                }
            }

            if (!pdfFilesToSend.isEmpty()) {
                sendEmailWithAttachments(email, emailContent.getTitle(), emailContent.getContentBody(), pdfFilesToSend);
                statusMap.put(cif, "Email sent successfully");
            } else {
                statusMap.put(cif, "No PDFs with status 'R' to send");
            }
        }
        return statusMap;
    }

    private void updateLogStatusToY(LogPajak logPajak) {
        logPajak.setLogStatus("Y");
        logPajak.setLogMessage("Sent");
        logPajak.setLogSendDate(LocalDate.now());
        logPajakRepo.save(logPajak);
    }

    private Nasabah getNasabahByCif(String cif) {
        return nasabahRepository.findById(cif)
                .orElseThrow(() -> new RuntimeException("Nasabah not found for CIF: " + cif));
    }

    private void sendEmailWithAttachments(String to, String subject, String body, List<File> attachments) throws MessagingException {
        System.out.println("mulai ngirim");
    	MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        for (File attachment : attachments) {
            helper.addAttachment(attachment.getName(), attachment);
        }

        javaMailSender.send(message);
    }

    private List<File> retrievePdfFiles(String pdfDirectory, LogPajak logPajak, String nasabahName) {
    	System.out.println("retrive pdf");
        List<File> pdfFiles = new ArrayList<>();
        String logPathPdf = logPajak.getLogPathPdf();
        System.out.println(logPathPdf);

        if (logPathPdf.contains(nasabahName + ".pdf")) {
        	System.out.println("masuk if 1");
            File file = new File(logPathPdf);
            if (file.exists()) {
                pdfFiles.add(file);
            }
        } else if (logPathPdf.contains("Summary_" + nasabahName + ".pdf")) {
        	System.out.println("masuk if 1");
            File file = new File(logPathPdf);
            if (file.exists()) {
                pdfFiles.add(file);
            }
        } else {
        	System.out.println("gamasuk if");
        }
        return pdfFiles;
    }
}
