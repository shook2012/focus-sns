<#if page.result?size!=0>
<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="events-upcoming">
			<#list page.result as event>
			<li>
				<a href="${base}/${event.project.uniqueId}/profile">
					<#if event.project.profile.logo??>
					<img class="top left thumbnail" src="${base}/logo/download/${event.project.profile.logo.id}/35x35"/>
					<#else>
					<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${event.project.category.code}.png" with="35" height="35"/>
					</#if>
				</a>
				<div class="event-detail">
					<div>
					<a href="${base}/${event.project.uniqueId}/calendar/event/detail?eventId=${event.id}">${event.title}</a>
					[<a href="${base}/${event.project.uniqueId}/profile">${event.project.title}</a>]
					</div>
					<div>
						活动时间: ${event.start?string('yyyy/MM/dd HH:mm')} ~ ${event.end?string('yyyy/MM/dd HH:mm')}
					</div>
				</div>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>