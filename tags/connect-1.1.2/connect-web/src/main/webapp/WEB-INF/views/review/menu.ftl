<@security code="rating-add">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="rating-add">
			<li>
				<a href="${base}/${project.uniqueId}/review/rating/form">添加评论</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>