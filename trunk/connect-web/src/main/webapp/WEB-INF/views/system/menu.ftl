<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="actions">
			<div>系统配置</div>
			<ul>
				<li>
					<a href="${base}/system">系统概况</a>
					(<a class="ajaxAction" href="${base}/system/search/index">索引</a>)
				</li>
				<li>
					<a href="${base}/system/site/list">站点管理</a>
					(<a href="${base}/system/site/form">添加</a>)
				</li>
				<#--
				<li>
					<a href="${base}/system/themes">主题管理</a>
					(<a href="${base}/system/theme/form">添加</a>)
				</li>
				-->
				<li>
					<a href="${base}/system/resource/list">资源管理</a>
					(<a href="${base}/system/resource/sync-action">同步</a>)
				</li>
			</ul>
			<#if RequestParameters.siteId??>
			<div>一般管理</div>
			<ul>
				<li>
					<a href="${base}/system/category/list?siteId=${RequestParameters.siteId}">菜单及分类管理</a>
					(<a href="${base}/system/category/form?siteId=${RequestParameters.siteId}">添加</a>)
				</li>
				<li class="false">
					<a href="${base}/system/template/list?siteId=${RequestParameters.siteId}">模板管理</a>
					(<a href="${base}/system/template/form?siteId=${RequestParameters.siteId}">添加</a>)
				</li>
				<#--
				<li class="last">
					<a href="${base}/system/links?siteId=${RequestParameters.siteId}">友情链接管理</a>
					(<a href="${base}/system/link/form?siteId=${RequestParameters.siteId}">添加</a>)
				</li>
				-->
			</ul>
			<div>安全管理</div>
			<ul>
				<li><a href="${base}/system/user/list?siteId=${RequestParameters.siteId}">用户管理</a></li>
				<li>
					<a href="${base}/system/role/list?siteId=${RequestParameters.siteId}">角色管理</a>
					(<a href="${base}/system/role/form?siteId=${RequestParameters.siteId}">添加</a>)
				</li>
				<li>
					<a href="${base}/system/permission/list?siteId=${RequestParameters.siteId}">权限管理</a>
				</li>
			</ul>
			</#if>
		</div>
	</div>
</div>