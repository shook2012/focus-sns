<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无话题可显示！</div>
		<#else>
		<ul class="topics-list">
		<#list page.result as topic>
			<li>
				<span class="top right">${topic.views} 阅</span>
				<a href="${base}/${topic.forum.project.uniqueId}/discussion/reply/list?topicId=${topic.id}">${topic.subject}</a>
				作者
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">${topic.enteredBy.nickname}</a>
				发表于
				${topic.entered?string('yyyy-MM-dd HH:mm')}
			</li>
		</#list>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
		</#if>
	</div>
</div>