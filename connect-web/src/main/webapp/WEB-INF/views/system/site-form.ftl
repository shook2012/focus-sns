<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "site"/>
		<form class="site-form" action="${base}/system/site/form-action" method="post">
			<div>
				<label for="domain">网站域名 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="site.domain"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="contextPath">上下文 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="site.contextPath"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="port">端口 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="site.port"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="ssl">SSL</label>
				<br/>
				<@spring.formCheckbox path="site.ssl"/>
			</div>
			<div>
				<label for="title">网站标题 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="site.title"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="description">网站描述</label>
				<br/>
				<@spring.formTextarea path="site.description"/>
			</div>
			<div>
				<label for="keywords">网站关键词</label>
				<br/>
				<@spring.formInput path="site.keywords"/>
			</div>
			<div>
				<label for="copyright">网站版权</label>
				<br/>
				<@spring.formTextarea path="site.copyright"/>
			</div>
			<div>
				<label for="theme">网站主题</label>
				<br/>
			</div>
			<div>
				<label for="enabled">启用</label>
				<br/>
				<@spring.formCheckbox path="site.enabled"/>
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="site.id"/>
			</div>
		</form>
	</div>
</div>