package com.thomas.test.spring.contactor.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thomas.test.spring.contactor.services.config.ServiceTestConfig;
import com.thomas.test.spring.contactor.services.testlistener.ServiceTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceTestConfig.class })
@TestExecutionListeners({ ServiceTestExecutionListener.class })
@ActiveProfiles("test")
public abstract class AbstractServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@PersistenceContext
	protected EntityManager em;

	protected Logger logger = Logger.getLogger(getClass());
}
