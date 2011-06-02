<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if parent??>
			<a href="${base}/system/category/list?siteId=${RequestParameters.siteId}&parentId=${parent.parentId!''}">上一级</a>
		</#if>
		<#if page.result?size == 0>
			<div class="notice">当前无菜单项可显示！</div>
		<#else>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>显示名</th>
						<th>编码</th>
						<th>排序值</th>
						<th>站点</th>
						<th>状态</th>
						<th class="last">操作</th>
					</tr>
				</thead>
				<tbody>
					<#list page.result as category>
						<tr>
							<td><a href="${base}/system/category/form?siteId=${RequestParameters.siteId}&categoryId=${category.id}">${category.id}</a></td>
							<td><a href="${base}/system/category/list?siteId=${RequestParameters.siteId}&parentId=${category.id}">${category.label}</a></td>
							<td>${category.code}</td>
							<td>${category.level!'0'}</td>
							<td><a href="${base}/system/categories?siteId=${category.site.id}">${category.site.title}</a></td>
							<td>
							<#if category.enabled>启用<#else>禁用</#if>
							</td>
							<td><a href="${base}/system/category/form?siteId=${RequestParameters.siteId}&parentId=${category.id}">添加子分类</a></td>
						</tr>
					</#list>
				</tbody>
			</table>
		</#if>
	</div>
</div>