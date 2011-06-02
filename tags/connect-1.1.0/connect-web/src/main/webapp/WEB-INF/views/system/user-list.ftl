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
					<th>用户名</th>
					<th>昵称</th>
					<th>域名</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<#list page.result as user>
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>
						<a href="${base}/${user.project.uniqueId}/profile" target="_blank">${user.nickname}</a>
					</td>
					<td>${user.project.category.site.domain}</td>
					<td>
						<#if user.enabled>激活<#else>未激活</#if>
					</td>
				</tr>
			</#list>
			</tbody>
		</table>
	</div>
</div>