<#if page.result?size!=0>
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
				<a href="${base}/${question.enteredBy.project.uniqueId}/profile">
				<#if question.enteredBy.project.profile.logo??>
					<img class="top left thumbnail" src="${base}/logo/download/${question.enteredBy.project.profile.logo.id}/30x30"/>
				<#else>
					<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${question.enteredBy.project.category.code}.png" width="30" height="30"/>
				</#if>
				</a>
				<div class="question-detail">
					<#if question.project.id==question.enteredBy.project.id>
					<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
					<#else>
					<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
					<#if question.project.id!=question.enteredBy.project.id>
					@
					<a href="${base}/${question.project.uniqueId}/profile">${question.project.title}</a>
					</#if>
					</#if>
					${question.title}
					(<a href="${base}/${question.project.uniqueId}/knowledge/question/detail?questionId=${question.id}">浏览</a>)
					<div>
						<span class="right"><@prettyTime date=question.entered/></span>
					</div>
				</div>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>