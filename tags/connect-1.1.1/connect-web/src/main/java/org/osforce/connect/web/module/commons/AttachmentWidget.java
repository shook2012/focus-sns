package org.osforce.connect.web.module.commons;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.web.module.util.AttachmentUtil;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Widget
@RequestMapping("/commons/attachment")
public class AttachmentWidget {

	private AttachmentService attachmentService;
	
	public AttachmentWidget() {
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping({"/download/{targetId}/{dimension}"})
	public void doDownloadAction(@PathVariable Long targetId,
			@PathVariable String dimension, HttpServletResponse response) throws IOException {
		download(targetId, dimension, response);
	}
	
	@RequestMapping({"/download/{targetId}"})
	public void doDownloadAction1(@PathVariable Long targetId,
			HttpServletResponse response) throws IOException {
		download(targetId, null, response);
	}
	
	@RequestMapping({"/download"})
	public void doDownloadAction2(@RequestParam Long id,
			HttpServletResponse response) throws IOException {
		download(id, null, response);
	}
	
	public void download(Long targetId, String dimension,HttpServletResponse response) throws IOException {
		Attachment attachment = attachmentService.getAttachment(targetId);
		attachment.setDimension(dimension);
		// read attachment content from local file
		AttachmentUtil.read(attachment);
		// prepare download
		if(!attachment.getContentType().matches("image/.*")) {
			response.setContentType(attachment.getContentType());
			response.setContentLength(attachment.getSize().intValue());
			response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
		}
		response.getOutputStream().write(attachment.getBytes());
	}
	
}
