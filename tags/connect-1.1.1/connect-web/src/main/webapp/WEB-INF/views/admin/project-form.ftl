<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "project" />
		<form id="project-form${id}" action="${base}/admin/project/form-action"
			method="post" class="project-form">
			<div>
				<label for="title" class="title">名称: <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="project.title" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="uniqueId" class="title">唯一编码:([a-z] - _) <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="project.uniqueId" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="category" class="title">分类:</label>
				<br/>
				<@spring.formInput path="project.category.label" attributes='readonly="readonly"' />
			</div>
			<div>
				<label>是否公开:</label>
				<br/>
				<@spring.formCheckbox path="project.publish" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="project.id" />
				<@spring.formHiddenInput path="project.enteredId" />
				<@spring.formHiddenInput path="project.modifiedId" />
				<@spring.formHiddenInput path="project.categoryId" />
				<@spring.formHiddenInput path="project.entered" />
			</div>
		</form>
	</div>
</div>