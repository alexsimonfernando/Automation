package com.olam.Utils;

import com.olam.ObjectRepository.AdminUserRepo;
import com.olam.ObjectRepository.DashboardRepo;
import com.olam.ObjectRepository.LoginRepo;
import com.olam.PageFunctions.*;
import com.olam.Runner.DriverClass;
import com.olam.Runner.RunnerClass;

public class CommonClasses {

	// ---------------------Object Repository-------------------------

	protected static LoginRepo get_PageLogin_Instance() {
		return LoginRepo.getInstance();

	}

	protected static DashboardRepo get_DashboardRepo_Instance() {
		return DashboardRepo.getInstance();

	}

	protected static AdminUserRepo get_AdminRepo_Instance() {
		return AdminUserRepo.getInstance();

	}
	// ---------------------PageFunctions-------------------------

	public static LoginPageFunction get_LoginPageFunction_Instance() {
		return LoginPageFunction.getInstance();

	}

	public static AdminUserFunction get_AdminUserFunction_Instance() {
		return AdminUserFunction.getInstance();

	}

	// ----------------------CommonClasses-----------------------

	protected static CommonActions get_CommonActions_Instance() {
		return CommonActions.getInstance();

	}

	protected static DriverClass get_driver_instance() {
		return DriverClass.getInstance();
	}

}