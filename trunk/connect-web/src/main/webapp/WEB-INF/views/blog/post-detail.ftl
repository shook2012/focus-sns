<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<span class="right">
			<@security code="post-edit" userRequired="true">
			<a href="${base}/${project.uniqueId}/blog/post/form?postId=${post.id}">编辑</a>
			</@security>
		</span>
		<h1>${post.title}</h1>
		${post.content}
	</div>
</div>