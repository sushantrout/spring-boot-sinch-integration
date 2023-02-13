package com.tech.sinchintegration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinch.xms.ApiConnection;
import com.sinch.xms.SinchSMSApi;
import com.sinch.xms.api.MtBatchTextSmsCreate;
import com.sinch.xms.api.MtBatchTextSmsResult;

@RestController
@RequestMapping(value = "/api/sms")
public class SMSController {

	// https://dashboard.sinch.com/sms/api/
	private static final String SERVICE_PLAN_ID = "";
	private static final String TOKEN = "";
	private static String SENDER = "";
	public static String TO_PHONENUMBER = "";
	private static ApiConnection conn;

	@GetMapping
	public MtBatchTextSmsResult sendSMS() {

		String[] to = { TO_PHONENUMBER };
		MtBatchTextSmsCreate message = SinchSMSApi.batchTextSms().sender(SENDER).addRecipient(to)
				.body("Wellcome sinch :)").build();
		try {
			MtBatchTextSmsResult batch = conn.createBatch(message);
			return batch;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	static {
		conn = ApiConnection.builder().servicePlanId(SERVICE_PLAN_ID).token(TOKEN).start();
	}
}
