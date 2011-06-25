<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul>
			<#list categories as category>
			<li>
				<a href="${base}/${project.uniqueId}/blog/post/list?categoryId=${category.id}">${category.label}</a>
				<@security code="post-category-edit" userRequired="true">
				(<a href="${base}/${project.uniqueId}/blog/category/form?categoryId=${category.id}">编辑</a>)
				</@security>
			</li>	
			</#list>
		</ul>
	</div>
</div>