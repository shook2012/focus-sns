<#if page.result?size gt 0>
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="featured-list">
			<#list page.result as file>
			<li>
				<@security code="file-download">
				<span class="right">
				<a href="${base}/commons/attachment/download?id=${file.id}">下载</a>
				</span>
				</@security>
				<a href="${base}/${project.uniqueId}/document/file/list?folderId=${file.folderId}">${file.name}<a>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>