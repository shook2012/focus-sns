package org.osforce.connect.web.module.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.osforce.connect.Application;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.spring4me.commons.image.ImageUtil;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 21, 2011 - 10:01:01 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public final class AttachmentUtil {
	
	private static final String basePath = SystemUtils.getUserHome() + "/." + 
											Application.CODE_NAME + "/attachments";
	
	public static void read(Attachment attachment) throws IOException {
		File targetFile = getTargetFile(attachment);
		if(StringUtils.isNotBlank(attachment.getDimension())) {
			File thumnailFile = getThumnailFile(attachment);
			if(!thumnailFile.exists()) {
				ImageUtil.resize(targetFile, thumnailFile, attachment.getDimension());
			}
			targetFile = thumnailFile;
		}
		attachment.setBytes(FileUtils.readFileToByteArray(targetFile));
	}
	
	public static void write(Attachment attachment) throws IOException {
		File targetFile = getTargetFile(attachment);
		FileUtils.writeByteArrayToFile(targetFile, attachment.getBytes());
	}
	
	private static File getThumnailFile(Attachment attachment) {
		StringBuffer pathBuffer = new StringBuffer(basePath);
		pathBuffer.append("/" + DateFormatUtils.format(attachment.getEntered(), "yyyy"));
		pathBuffer.append("/" + DateFormatUtils.format(attachment.getEntered(), "MMdd"));
		String name = attachment.getName() + "_" + attachment.getId() + "." +attachment.getDimension();
		return new File(pathBuffer.toString(), name);
	}
	
	private static File getTargetFile(Attachment attachment) {
		StringBuffer pathBuffer = new StringBuffer(basePath);
		pathBuffer.append("/" + DateFormatUtils.format(attachment.getEntered(), "yyyy"));
		pathBuffer.append("/" + DateFormatUtils.format(attachment.getEntered(), "MMdd"));
		String name =  attachment.getName() + "_" + attachment.getId() + attachment.getSuffix();
		return new File(pathBuffer.toString(), name);
	}
	
}
