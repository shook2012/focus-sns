<div id="${widgetConfig.id}" class="widget">
	<div class="head">
		<h3>${widgetConfig.title}</h3>
		<div class="tabs">
			<a <#if !Session.answerOrder?? || Session.answerOrder=='date'>class="current"</#if> href="${base}/${project.uniqueId}/knowledge/question/detail?questionId=${RequestParameters.questionId}&answerOrder=date"> 最近</a>
			<a <#if Session.answerOrder?? && Session.answerOrder=='vote'>class="current"</#if> href="${base}/${project.uniqueId}/knowledge/question/detail?questionId=${RequestParameters.questionId}&answerOrder=vote"> 投票</a>
		</div>
	</div>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无答案可显示！</div>	
		<#else>
		<ul class="answers-list">
		<#list page.result as answer>
			<li>
				<p>${answer.content}</p>
				<div>
					<span class="right">
						<a href="${base}/${project.uniqueId}/knowledge/answer/form?answerId=${answer.id}">编辑</a>						
					</span>
				
					<a href="${base}/${answer.enteredBy.project.uniqueId}/profile">${answer.enteredBy.nickname}</a>
					发表于
					<@prettyTime date=answer.entered />
					(${answer.entered?string('yyyy/M/d HH:mm')})
					<a class="ajaxAction" href="${base}/commons/vote/vote-action?code=useful&linkedId=${answer.id}&entity=Answer"> 有帮助 (${answer.useful!'0'})</a>
					|
					<a class="ajaxAction" href="${base}/commons/vote/vote-action?code=useless&linkedId=${answer.id}&entity=Answer"> 没帮助 (${answer.useless!'0'})</a>
				</div>
			</li>
		</#list>
		</ul>
		</#if>
	</div>
</div>