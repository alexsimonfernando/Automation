package com.olam.PageFunctions;

import com.olam.ObjectRepository.LoginRepo;
import com.olam.Utils.CommonClasses;

public class LoginPageFunction extends CommonClasses {

	public static LoginPageFunction loginPageFunction;

	public static LoginPageFunction getInstance() {
		if (loginPageFunction == null) {
			loginPageFunction = new LoginPageFunction();
		}
		return loginPageFunction;

	}

	public void lauchURL() throws Exception {
		get_CommonActions_Instance().navigateToURL();
		get_CommonActions_Instance().implicitWait(50);

	}

	public void loginwithValidCredentials(String userName, String password) throws InterruptedException {
		get_CommonActions_Instance().pageTitleValidation("AtSource Social Plus Metrics Portal");
		LoginRepo.getInstance();
		get_CommonActions_Instance().sendKeysAction(LoginRepo.userName, userName);
		get_CommonActions_Instance().sendKeysAction(LoginRepo.password, password);
		get_CommonActions_Instance().clickAction(LoginRepo.loginButton);
		Thread.sleep(3000);
	}

}
