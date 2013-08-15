package com.thomas.test.spring.contactor.services.testlistener;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import com.thomas.test.spring.contactor.services.annotations.DataSets;

public class ServiceTestExecutionListener implements TestExecutionListener {

	private IDatabaseTester databaseTester;

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		// no op
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		// Clear up testing data if exists
		if (databaseTester != null) {
			databaseTester.onTearDown();
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		// no op
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {

		// Check for existence of DataSets annotation for the method under
		// testing
		DataSets dataSetAnnotation = testContext.getTestMethod().getAnnotation(DataSets.class);

		if (dataSetAnnotation == null) {
			return;
		}

		String dataSetName = dataSetAnnotation.setUpDataSet();

		// Populate data set if data set name exists
		if (!dataSetName.equals("")) {
			databaseTester = (IDatabaseTester) testContext.getApplicationContext().getBean(
					"databaseTester");
			XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader) testContext
					.getApplicationContext().getBean("xlsDataFileLoader");
			IDataSet dataSet = xlsDataFileLoader.load(dataSetName);

			databaseTester.setDataSet(dataSet);
			databaseTester.onSetup();
		}
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		// no op
	}

}
