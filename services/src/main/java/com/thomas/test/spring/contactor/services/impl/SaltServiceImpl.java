package com.thomas.test.spring.contactor.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.primitives.UnsignedBytes;
import com.thomas.test.spring.contactor.services.SaltService;

@Service("saltService")
@Repository
@Transactional
public class SaltServiceImpl implements SaltService {

	@Autowired
	private Integer saltLength;

	private Random random;

	public SaltServiceImpl() {
		random = new Random();
	}

	public Integer getSaltLength() {
		return saltLength;
	}

	public void setSaltLength(Integer saltLength) {
		this.saltLength = saltLength;
	}

	@Override
	public byte[] getNewSalt() {
		byte[] salt = new byte[saltLength];

		random.nextBytes(salt);

		return salt;
	}

	@Override
	public String getNewSaltAsAString() {
		byte[] rawSalt = getNewSalt();

		return convertBytesToString(rawSalt);
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
