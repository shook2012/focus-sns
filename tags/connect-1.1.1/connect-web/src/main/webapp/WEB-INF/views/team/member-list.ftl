<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前成员可显示！</div>
		<#else>
		<ul class="members-list">
		<#list page.result as member>
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
				<#if member.user.project.profile.logo??>
				<img class="thumbnail" src="${base}/logo/download/${member.user.project.profile.logo.id}/50x50"/>
				<#else>
				<img class="thumbnail"src="${base}/themes/${theme}/stock/${member.user.project.category.code}.png" with="50" height="50"/>
				</#if>
				</a>
				<div class="desc">
					<div>
					<a target="_blank" href="${base}/${member.user.project.uniqueId}/profile">
						${member.user.project.title}
					</a>
					${member.role.name}
					</div>
					<div>注册日期: ${member.user.entered?string('yyyy/M/d')}</div>
					<div>最近登录: ${member.user.lastLogin?string('yyyy/M/d HH:mm')}</div>
					<div class="clear"></div>
				</div>
			</li>
		</#list>
		</ul>
		<br class="clear"/>
		<div id="pagination${id}" class="right"></div>
		</#if>
	</div>
</div>