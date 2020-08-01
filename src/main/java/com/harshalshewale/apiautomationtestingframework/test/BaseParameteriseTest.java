package com.harshalshewale.apiautomationtestingframework.test;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public abstract class BaseParameteriseTest {
	
	String testcase_id;
	String testcase_name;
	String testcase_description;
	
	public BaseParameteriseTest(String testcase_id,String testcase_name,String testcase_description){
		
		this.testcase_id=testcase_id;
		this.testcase_name=testcase_name;
		this.testcase_description=testcase_description;
		
	}
	
	public abstract void runTest() throws Exception;
	
	@ParameterizedTest
	public final void runTestMaster() throws Exception {
		System.out.println(testcase_id);
		System.out.println(testcase_name);
		System.out.println(testcase_description);
		runTest();
		System.out.println("runTestMaster is running");
		
	}
	

}
