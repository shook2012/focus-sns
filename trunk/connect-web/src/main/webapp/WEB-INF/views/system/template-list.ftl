<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>编码</th>
					<th>分类</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
				<#list page.result as template>
					<tr>
						<td><a href="${base}/system/template/form?templateId=${template.id}&siteId=${RequestParameters.siteId}">${template.id}</a></td>
						<td>${template.name}</td>
						<td>${template.code}</td>
						<td>${template.category.label}</td>
						<td>
							<#if template.enabled>启用<#else>禁用</#if>
						</td>
					</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>	