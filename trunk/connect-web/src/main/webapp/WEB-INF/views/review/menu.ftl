<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<li>
				<a href="${base}/${project.uniqueId}/review/rating/form">添加评论</a>
			</li>
		</ul>
	</div>
</div>