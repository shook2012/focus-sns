<@security code="member-add" userRequired="true">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="member-add" userRequired="true">
			<li>
				<a href="${base}/${project.uniqueId}/team/member/invite">邀请</a>
			</li>
			</@security>
		</ul>
	</div>
</div>
</@security>