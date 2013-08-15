package com.thomas.test.spring.contactor.services;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.primitives.UnsignedBytes;
import com.thomas.test.spring.contactor.services.impl.SaltServiceImpl;

public class SaltServiceSandbox {

	private Logger logger = Logger.getLogger(getClass());

	@Test
	@Ignore
	public void test() {
		SaltServiceImpl ss = new SaltServiceImpl();
		ss.setSaltLength(4);
		byte[] bytes = ss.getNewSalt();
		for (byte b : bytes) {
			int i = b & 0xFF;
			logger.info("dec: " + b + " / " + i);
			logger.info("hex: " + Integer.toHexString(b) + " / " + Integer.toString(b, 16));
			logger.info("bin: " + Integer.toString(b, 2));
			logger.info("uhex: " + UnsignedBytes.toString(b, 16));
			logger.info("--");
		}
	}

	@Test
	public void sign() {
		SaltServiceImpl ss = new SaltServiceImpl();
		ss.setSaltLength(64);
		byte[] bytes = ss.getNewSalt();

		Splitter splitter = Splitter.fixedLength(2);
		System.out.println(Arrays.toString(bytes));
		System.out.println(splitter.split(convertBytesToString(bytes)));
		System.out.println(UnsignedBytes.join(" ", bytes));

	}

	private String convertBytesToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			if (UnsignedBytes.toInt(b) < 16) {
				sb.append(0);
			}
			sb.append(UnsignedBytes.toString(b, 16));

		}
		return sb.toString().toUpperCase();
	}
}
