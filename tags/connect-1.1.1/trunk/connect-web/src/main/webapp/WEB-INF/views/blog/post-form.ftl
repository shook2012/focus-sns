<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "post" />
		<form id="post-form${id}" action="${base}/blog/post/form-action" 
			method="post" class="post-form">
			<div>
				<label for="title" class="title">标题 <span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="post.title" />
				<#if categoryOptions?size gt 0>
				<span>归类 (${categoryOptions?size})</span>
				<@spring.formSingleSelect path="post.categoryId" options=categoryOptions/>
				</#if>
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
				</#if>
			</div>
			<div>
				<label for="content" class="title">内容 <span class="required"> * </span></label>
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
				</#if>
				<br/>
				<form:textarea path="content" id="editor${id}" cssClass="text"/>
				<@spring.formTextarea path="post.content" attributes='class="xheditor-simple" style="width:80%"' />
			</div>
			<div>
				<label for="keywords">关键词</label>
				<br/>
				<@spring.formInput path="post.keywords" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="post.id" />
				<@spring.formHiddenInput path="post.enteredId" />
				<@spring.formHiddenInput path="post.entered" />
				<@spring.formHiddenInput path="post.modifiedId" />
				<@spring.formHiddenInput path="post.projectId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#${id} #content').htmlarea(settings.simple);
});
</script>