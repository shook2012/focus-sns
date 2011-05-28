<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无答案可显示！</div>
		<#else>
		<ul class="answers-list">
		<#list page.result as answer>
			<li>
				<p>${answer.content}</p>
				<p>
					${answer.entered?string('yyyy/d/m')}
					|
					<a href="${base}/${answer.enteredBy.project.uniqueId}/profile">${answer.enteredBy.nickname}</a>
				</p>
			</li>
		</#list>
		</ul>
		</#if>
	</div>
</div>