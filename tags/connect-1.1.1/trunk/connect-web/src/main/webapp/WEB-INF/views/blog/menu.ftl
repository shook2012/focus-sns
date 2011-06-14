<@security code="post-add|post-category-add">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="post-add" >
			<li>
				<a href="${base}/${project.uniqueId}/blog/post/form">添加博文</a>
			</li>
			</@security>
			<@security code="post-category-add">
			<li>
				<a href="${base}/${project.uniqueId}/blog/category/form">添加分类</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>