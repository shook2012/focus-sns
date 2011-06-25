<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<table class="tableList">
			<thead>
				<tr>
					<th>ID</th>
					<th>角色名</th>
					<th>编码</th>
					<th>分类</th>
					<th>级别</th>
					<th>站点</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<#list page.result as role>
				<tr>
					<td><a href="${base}/system/role/form?roleId=${role.id}&siteId=${RequestParameters.siteId}&categoryId=${RequestParameters.categoryId!''}">${role.id}</a></td>
					<td>${role.name}</td>
					<td>${role.code}</td>
					<td>${role.category.label}</td>
					<td>${role.level}</td>
					<td>${role.category.site.domain}</td>
					<td>
						<#if role.enabled>启用<#else>禁用</#if>
					</td>
				</tr>
			</#list>
			</tbody>
		</table>
	</div>
</div>