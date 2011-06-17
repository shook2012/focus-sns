<@security code="question-add" userRequired="true">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="question-add" userRequired="true">
			<li class="last"><a href="${base}/${project.uniqueId}/knowledge/question/form">我有问题</a></li>
			</@security>
		</ul>
	</div>
</div>
</@security>