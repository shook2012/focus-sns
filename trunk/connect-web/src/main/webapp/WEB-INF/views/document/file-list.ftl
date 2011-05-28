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
		<#if fileItems?size == 0 >
		<div class="notice">当前目录无文档可显示!</div>
		<#else>
		<table width="100%">
			<thead>
				<tr>
					<th>选择(<a href="#">全选</a>)</th>
					<th>文档名</th>
					<th>上传日期</th>
					<th>修改日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<#list fileItems as fileItem>
				<tr>
					<td><input type="checkbox"/></td>
					<td>${fileItem.realFile.fileName}</td>
					<td>${fileItem.entered}</td>
					<td>${fileItem.modified}</td>
					<td>
						<a href="${base}/document/file/download-action?fileId=${fileItem.id}" target="_blank">下载</a>
					</td>
				</tr>
			</#list>
			</tbody>
		</table>
		</#if>
		<br class="clear"/>
	</div>
</div>