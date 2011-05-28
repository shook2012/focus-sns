<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "template"/>
		<form class="template-form" action="${base}/system/template/form-action" method="post">
			<div>
				<label for="category">分类</label>
				<br/>
				<@spring.formSingleSelect path="template.categoryId" options=categoryOptions/>
			</div>
			<div>
				<label for="name">名称 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="template.name" />
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="code">编码 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="template.code" />
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="content">内容 <span class="required">*</span></label>
				<br/>
				<@spring.formTextarea path="template.content" />
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="enabled">启用</label>
				<br/>
				<@spring.formCheckbox path="template.enabled" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="template.id" />
				<input type="hidden" name="siteId" value="${RequestParameters.siteId!''}" />
			</div>
		</form>
	</div>
</div>