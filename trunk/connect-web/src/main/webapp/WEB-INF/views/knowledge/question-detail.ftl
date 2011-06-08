<div id="${widgetConfig.id}" class="widget">
	<div class="question-head">
		<#if question.enteredBy.project.profile.logo??>
				<img class="top left thumbnail" src="${base}/logo/download/${question.enteredBy.project.profile.logo.id}/40x40"/>
			<#else>
				<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${question.enteredBy.project.category.code}.png" width="40px" height="40px"/>
			</#if>
			<div>
				<h3>${question.title}</h3>
				<div>
					<span class="right">
						<@security code="question-edit">
						<a href="${base}/${project.uniqueId}/knowledge/question/form?questionId=${question.id}">编辑</a>
						</@security>
					</span>
				
					<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
					发表于
					<@prettyTime date=question.entered />
					(${question.entered?string('yyyy/M/d HH:mm')})
					<#if user??>
					<a class="ajaxAction" href="${base}/commons/link/favorite?fromId=${user.project.id}&toId=${question.id}&entity=Question">收藏 (${question.favorite})</a>
					<#else>
					<a class="popupAction" href="${base}/app/user/login">收藏 (${question.favorite})</a>
					</#if>
				</div>
			</div>
			<br class="clear" />
	</div>
	<div class="body question-body">
		<p>${question.content}</p>
	</div>
</div>