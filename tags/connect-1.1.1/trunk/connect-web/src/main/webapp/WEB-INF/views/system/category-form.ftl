<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "category"/>
		<form class="category-form" action="${base}/system/category/form-action" method="post">
			<div>
				<label for="label">显示名 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="category.label"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="code">编码 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="category.code"/>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="sensitive">仅登录可见</label>
				<br/>
				<@spring.formCheckbox path="category.sensitive"/>
			</div>
			<div>
				<label for="level">排序值 </label>
				<@spring.formInput path="category.level"/>
			</div>
			<#if categories?? && categories?size gt 0>
			<div>
				<label for="parentId">父分类</label>
				<@spring.formSingleSelect path="category.parentId" options=categoryOptions />
			</div>
			</#if>
			<div>
				<label for="enabled">启用</label>
				<form:checkbox path="enabled" value="true"/>
				<@spring.formCheckbox path="category.enabled"/>
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<form:hidden path="id"/>
				<@spring.formHiddenInput path="category.id"/>
				<@spring.formHiddenInput path="category.siteId"/>
			</div>
		</form>
	</div>
</div>