<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<li><a href="${base}/${project.uniqueId}/admin/project/form">空间设置</a></li>
			<li><a href="${base}/${project.uniqueId}/admin/features/form">模块设置</a></li>
		</ul>
	</div>
</div>