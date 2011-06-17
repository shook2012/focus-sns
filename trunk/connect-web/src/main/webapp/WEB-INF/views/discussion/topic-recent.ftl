<#if page.result?size!=0>
<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if project??>
		
		<#else>
		<ul class="topics-recent">
		<#list page.result as topic>
			<li>
				<span class="top right">${topic.views} 阅</span>
				<a href="${base}/${topic.forum.project.uniqueId}/discussion/reply/list?topicId=${topic.id}">${topic.subject}</a>
				<#if topic.forum.project.id!=topic.enteredBy.project.id>
				[<a href="${base}/${topic.forum.project.uniqueId}/profile">${topic.forum.project.title}</a>]
				</#if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<@prettyTime date=topic.entered/>
				By
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">${topic.enteredBy.nickname}</a>
			</li>
		</#list>
		</ul>
		</#if>
	</div>
</div>
</#if>