package org.osforce.connect.web.tag.freemarker;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.util.Assert;

import com.ocpsoft.pretty.time.PrettyTime;

import freemarker.core.Environment;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 2:37:46 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class PrettyTimeTag implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateDateModel dateModel =  (TemplateDateModel) params.get("date");
		Date date = dateModel!=null ? dateModel.getAsDate() : null;
		Assert.notNull(date, "Parameter date can not be null!");
		PrettyTime prettyTime = new PrettyTime(env.getLocale());
		env.getOut().write(prettyTime.format(date));
	}

}
