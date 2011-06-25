<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "category" />
		<form id="blog-category-form${id}" action="${base}/blog/category/form-action" 
			method="post" class="blog-category-form">
				<div>
					<label for="label">显示名 <span class="required"> * </span></label>
					<br/>
					<@spring.formInput path="category.label" />
				</div>
				<div>
					<label for="level">排序值</label>
					<br/>
					<@spring.formInput path="category.level" />
				</div>
				<div>
					<label for="enabled">启用</label>
					<br/>
					<@spring.formCheckbox path="category.enabled" />
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<@spring.formHiddenInput path="category.id" />
					<@spring.formHiddenInput path="category.projectId" />
					<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
				</div>
		</form>
	</div>
</div>