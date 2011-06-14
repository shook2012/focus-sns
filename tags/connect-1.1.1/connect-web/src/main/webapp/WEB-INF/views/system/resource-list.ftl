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
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			<#list resources as resource>
				<tr>
					<td><a href="${base}/system/resource/form?resourceId=${resource.id}">${resource.id}</a></td>
					<td>${resource.name}</td>
					<td>${resource.code}</td>
					<td>
						<#if resource.enabled>启用<#else>禁用</#if>
					</td>
				</tr>
			</#list>
			</tbody>
		</table>
	</div>
</div>