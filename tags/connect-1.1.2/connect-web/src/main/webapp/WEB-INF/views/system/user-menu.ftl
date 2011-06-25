<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul>
		<#if user?? && user.project??>
			<li>
				<a href="${base}/${user.project.uniqueId}/profile">${user.nickname}</a>
			</li>
			<li class="last">
				<a href="${base}/logout">注销</a>
			</li>		
		<#else>
			<li>
				<a href="${base}/login">登录</a>
			</li>
			<li class="last">
				<a href="${base}/register">注册</a>
			</li>
		</#if>
		</ul>
	</div>
</div>