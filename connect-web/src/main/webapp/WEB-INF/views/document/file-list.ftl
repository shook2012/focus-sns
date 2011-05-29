<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if folder?? && folders?size gt 0>
		<p>
			路径:
			<#list folders as folder>
			/<a id="${folder.id}" href="${base}/${project.uniqueId}/document/file/list?folderId=${folder.id}" class="pathFolder">${folder.name}</a>
			</#list>
		</p>
		</#if>
		<#if files?size == 0 >
		<div class="notice">当前目录无文档可显示!</div>
		<#else>
		<table width="100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>文档名</th>
					<th>上传日期</th>
					<th>修改日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<#list files as file>
				<tr>
					<td>${file.id}</td>
					<td>
						<#if file.name??>
						${file.name}
						<#else>
						${file.realFile.fileName}
						</#if>
					</td>
					<td>${file.entered!''}</td>
					<td>${file.modified}</td>
					<td>
						<@security code="file-edit">
						<a href="${base}/${project.uniqueId}/document/file/form?fileId=${file.id}">编辑</a>
						</@security>
						<@security code="file-edit">  
						|
						<a href="${base}/document/file/delete-action?fileId=${file.id}">删除</a>
						</@security>
						<@security code="file-download">
						|
						<a href="${base}/commons/attachment/download?id=${file.realFileId}" target="_blank">下载</a>
						</@security>
					</td>
				</tr>
			</#list>
			</tbody>
		</table>
		</#if>
		<br class="clear"/>
	</div>
</div>