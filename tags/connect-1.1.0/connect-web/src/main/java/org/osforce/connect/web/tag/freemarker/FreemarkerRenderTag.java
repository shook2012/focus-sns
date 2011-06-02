package org.osforce.connect.web.tag.freemarker;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 31, 2011 - 1:22:56 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class FreemarkerRenderTag implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateScalarModel templateModel = (TemplateScalarModel) params.get("template");
		Assert.notNull(templateModel, "Parameter template can not be null!");
		Template template = new Template("", 
				new StringReader(templateModel.getAsString()), env.getConfiguration());
		String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, env.getDataModel());
		env.getOut().write(result);
	}

}
