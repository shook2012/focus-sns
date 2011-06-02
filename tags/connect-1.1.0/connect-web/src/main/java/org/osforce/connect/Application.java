package org.osforce.connect;

import org.apache.commons.lang.SystemUtils;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 17, 2011 - 11:39:31 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class Application {

	// application properties
	public static final String PRODUCT_NAME = "Focus SNS";
	public static final String CODE_NAME = "connect";
	public static final String VERSION = "1.1.0";
	public static final String LICENSE = "GNU Affero Genral Public License (AGPL3)";
	public static final String RUNTIME = SystemUtils.getUserHome().getAbsolutePath() + "/.connect"; 
	// operating system properties
	public static final String OS_NAME = SystemUtils.OS_NAME;
	public static final String OS_ARCH = SystemUtils.OS_ARCH;
	public static final String OS_VERVION = SystemUtils.OS_VERSION;
	// 
	public static final String JAVA_HOME = SystemUtils.JAVA_HOME;
	public static final String JAVA_TMPDIR = SystemUtils.JAVA_IO_TMPDIR;
	public static final String JAVA_VENDOR = SystemUtils.JAVA_VENDOR;
	public static final String JAVA_VERSION = SystemUtils.JAVA_VERSION;
	
	private static Application instance = new Application(); 
	
	private Application() {
	}
	
	public static Application getInstance() {
		return instance;
	}
	
	public String getProductName() {
		return PRODUCT_NAME;
	}
	
	public  String getCodeName() {
		return CODE_NAME;
	}
	
	public  String getLicense() {
		return LICENSE;
	}
	
	public  String getVersion() {
		return VERSION;
	}
	
	public String getRuntime() {
		return RUNTIME;
	}
	
	public String getOsArch() {
		return OS_ARCH;
	}
	
	public String getOsName() {
		return OS_NAME;
	}
	
	public String getOsVersion() {
		return OS_VERVION;
	}
	
	public String getJavaHome() {
		return JAVA_HOME;
	}
	
	public String getJavaTmpdir() {
		return JAVA_TMPDIR;
	}
	
	public String getJavaVendor() {
		return JAVA_VENDOR;
	}
	
	public String getJavaVersion() {
		return JAVA_VERSION;
	}
}
