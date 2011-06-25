<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="events-list">
			<span class="span-2">
				<div class="calendar">
					<div class="month">${start?string('MMM')}</div>
					<div class="date">${start?string('d')}</div>
					<div class="day">${start?string('E')}</div>
				</div>
				<#if RequestParameters.date?? && RequestParameters.date?contains('~')>
				<div class="calendar">
					<div class="month">${end?string('MMM')}</div>
					<div class="date">${end?string('d')}</div>
					<div class="day">${end?string('E')}</div>
				</div>
				</#if>
			</span>
			<#if events?size == 0>
				<div class="info">当前无活动可显示！</div>
			<#else>
			<ul>
			<#list events as event>
				<li <#if !event_has_next>class="last"</#if> >
					<@security code="event-edit" userRequired="true">
					<span class="right">
						<a href="${base}/${project.uniqueId}/calendar/event/form?eventId=${event.id}">编辑</a>
					</span>
					</@security>
					<h4><a href="${base}/${project.uniqueId}/calendar/event/detail?eventId=${event.id}">${event.title}</a></h4>
					<dl>
						<dt>时间</dt>
						<dd>
							${event.start} 
							~ 
							${event.end}
						</dd>
						<#if event.url?? && event.url?trim!=''>
						<dt>URL</dt>
						<dd><a href="${event.url}">${event.url}</a></dd>
						</#if>
					</dl>
				</li>
			</#list>
			</ul>
		</#if>
		</div>
	</div>
</div>