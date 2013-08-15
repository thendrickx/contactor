package com.thomas.test.spring.contactor.services;

public interface SaltService {

	public byte[] getNewSalt();

	public String getNewSaltAsAString();
}
