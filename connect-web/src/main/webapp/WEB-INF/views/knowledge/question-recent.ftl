<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="questions-recent">
			<#list page.result as question>
			<li>
				<a href="${base}/${project.uniqueId}/knowledge/question/${question.id}">${question.title}</a>
			</li>
			</#list>
		</ul>
	</div>
</div>