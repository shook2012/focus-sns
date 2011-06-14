<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<h4><a href="${base}/${project.uniqueId}/calendar/event/detail?eventId=${event.id}">${event.title}</a></h4>
		<dl>
			<dt>时间</dt>
			<dd>
				${event.start} 
				~ 
				${event.end}
			</dd>
			<dt>描述</dt>
			<dd>${event.description}</dd>
			<#if event.url?? && event.url?trim!=''>
			<dt>URL</dt>
			<dd><a href="${event.url}">${event.url}</a></dd>
			</#if>
		</dl>
	</div>
</div>