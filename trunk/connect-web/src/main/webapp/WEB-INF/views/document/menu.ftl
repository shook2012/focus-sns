<@security code="file-upload|folder-add">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="file-upload">
			<li>
				<a href="${base}/${project.uniqueId}/document/file/form<#if RequestParameters.folderId??>?folderId=${RequestParameters.folderId}</#if>">上传文档</a>
			</li>
			</@security>
			<@security code="folder-add">
			<li class="last">
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>