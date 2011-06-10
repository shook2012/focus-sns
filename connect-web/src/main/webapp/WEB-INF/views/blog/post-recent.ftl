<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="posts-recent">
			<#list page.result as post>
			<li>
				<a href="${base}/${post.project.uniqueId}/blog/post/${post.id}">${post.title}</a>
			</li>
			</#list>
		</ul>
	</div>
</div>