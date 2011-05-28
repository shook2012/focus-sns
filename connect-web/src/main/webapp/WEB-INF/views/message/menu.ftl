<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<li>
				<a href="${base}/${project.uniqueId}/message/inbox">收件箱</a>
			</li>
			<li>
				<a href="${base}/${project.uniqueId}/message/sentbox">发件箱</a>
			</li>
		</ul>
	</div>
</div>