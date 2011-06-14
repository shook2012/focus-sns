<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if message.enteredBy.project.profile.logo??>
		<img class="left thumbnail" src="${base}/logo/download/${message.enteredBy.project.profile.logo.id}/50x50"/>
		<#else>
		<img class="left thumbnail" src="${base}/themes/${theme}/stock/${message.enteredBy.project.category.code}.png" with="50" height="50"/>
		</#if>
		<div class="message-body">
			<div class="content">
				${message.content}
			</div>
			<br class="clear"/>
		</div>
	</div>
</div>