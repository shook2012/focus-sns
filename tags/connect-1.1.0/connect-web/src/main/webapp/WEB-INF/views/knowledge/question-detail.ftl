<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="question">
			<h4>${question.title}</h4>
			<p>${question.content}</p>
			<p>
			${question.entered?string('yyyy/M/d')}|<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
			</p>
		</div>
	</div>
</div>