<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="event-add">
			<li>
				<a href="${base}/${project.uniqueId}/calendar/event/form">添加日程</a>
			</li>
			</@security>
		</ul>
	</div>
</div>