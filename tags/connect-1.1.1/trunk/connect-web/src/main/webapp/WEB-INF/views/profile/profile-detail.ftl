<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="profile-basic">
			<#if profile.logo??>
				<img class="top right thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
			<#else>
				<img class="top right thumbnail" src="${base}/themes/${theme}/stock/${profile.project.category.code}.png"/>
			</#if>
			<h3>${profile.title}</h3>
			<dl>
				<dd>
				${profile.shortDescription!''}
				<@security code="profile-edit">
					(<a href="${base}/${project.uniqueId}/profile/form?profileId=${profile.id}">编辑简介</a>)
				</@security>
				</dd>
				<#if profile.description??>
				<dd>${profile.description}<dd>
				</#if>
			</dl>
		</div>
		<#if profile.attributeList?size gt 0>
		<div class="profile-attributes">
			<dl>
				<#list profile.attributeList as attribute>
				<dd>
				<#if attribute[1]?starts_with("http://")>
					<span class="title">${attribute[0]}</span>
					<a href="${attribute[1]}" title="${attribute[0]}" target="_blank">${attribute[1]}</a>
				<#else>
					<span class="title">${attribute[0]}</span>
					${attribute[1]}
				</#if>
				</dd>
				</#list>
			</dl>
		</div>
		</#if>
		<br class="clear" />
	</div>
</div>