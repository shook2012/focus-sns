<#if page.result?size!=0>
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
				<span class="top right">${post.views} 阅</span>
				<a href="${base}/${post.project.uniqueId}/blog/post/detail?postId=${post.id}">${post.title}</a>
				<#if post.project.id!=post.enteredBy.project.id>
				[<a href="${base}/${post.project.uniqueId}/profile">${post.project.title}</a>]
				</#if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<@prettyTime date=post.entered/>
				By
				<a href="${base}/${post.enteredBy.project.uniqueId}/profile">${post.enteredBy.nickname}</a>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>