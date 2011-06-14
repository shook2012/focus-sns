<@security code="message-view">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="message-view">
			<li>
				<a href="${base}/${project.uniqueId}/message/inbox">收件箱</a>
			</li>
			</@security>
			<@security code="message-view">
			<li>
				<a href="${base}/${project.uniqueId}/message/sentbox">发件箱</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>