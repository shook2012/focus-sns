<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="topic">
			<div class="span-3 author">
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
					<#if topic.enteredBy.project.profile.logo??>
					<img class="thumbnail" src="${base}/logo/download/${topic.enteredBy.project.profile.logo.id}/75x75"/>
					<#else>
					<img class="thumbnail" src="${base}/themes/${theme}/stock/${topic.enteredBy.project.category.code}.png"/>
					</#if>
				</a>
				<div>
					<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">${topic.enteredBy.nickname}</a>
				</div>
				<div>
					<span>注册时间:${topic.enteredBy.entered?string('yyyy/M/d')}</span>
				</div>
			</div>
			<div class="topic-head">
				<span class="top right">
					发表于 ${topic.entered?string('yyyy-MM-dd HH:mm')}
					<#if user??>
					<a class="ajaxAction" href="${base}/list/link/favorite?fromId=${user.project.id}&toId=${topic.id}&entity=Topic">收藏 (${topic.favorite})</a>
					<#else>
					<a class="popupAction" href="${base}/app/user/login">收藏 (${topic.favorite})</a>
					</#if>
				</span>
				<h4>${topic.subject}</h4>
			</div>
			<div class="topic-body">
				${topic.content}
			</div>
			<br class="clear"/>
		</div>
	</div>
</div>