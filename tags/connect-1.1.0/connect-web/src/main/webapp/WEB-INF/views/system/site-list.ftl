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
					<th>标题</th>
					<th>首页</th>
					<th>主题</th>
					<th>状态</th>
					<!--
					<th class="last">操作</th>
					 -->
				</tr>
			</thead>
			<tbody>
				<#list page.result as site>
					<tr>
						<td><a href="${base}/system/site/form?siteId=${site.id}">${site.id}</a></td>
						<td><a href="${base}/system/site/list?siteId=${site.id}">${site.title}</a></td>
						<td><a href="${site.homeURL}" target="_blank">${site.homeURL}</a></td>
						<td>default</td>
						<td class="last">
						<#if site.enabled>启用<#else>禁用</#if>
						</td>
						<!--
						<td>
							<a href="${base}/process/system/site/backup?siteId=${site.id}">backup</a>
							|
							<a href="${base}/process/system/site/restore?siteId=${site.id}">restore</a>
						</td>
						 -->
					</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>