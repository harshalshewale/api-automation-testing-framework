package com.harshalshewale.apiautomationtestingframework.test;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;

public interface BaseTestSuite {

	@AfterAll
	static void sendEmail() throws IOException {
		
		System.out.println("Send Email executed..");
		
	}
}
