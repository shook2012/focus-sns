<@security code="forum-add|topic-add">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="forum-add">
			<li>
				<a href="${base}/${project.uniqueId}/discussion/forum/form">添加版块</a>
			</li>
			</@security>
			<@security code="topic-add">
			<li> 
				<a href="${base}/${project.uniqueId}/discussion/topic/form">我有话题</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>