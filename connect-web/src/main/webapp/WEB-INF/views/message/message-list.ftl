<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0 >
		<div class="notice">
			当前无消息可显示！
		</div>
		<#else>
		<ul class="messages-list">
			<#list page.result as message>
			<li>
				<#if message.enteredBy.project.profile.logo??>
				<img class="left thumbnail" src="${base}/logo/download/${message.enteredBy.project.profile.logo.id}/40x40"/>
				<#else>
				<img class="left thumbnail" src="${base}/themes/${theme}/stock/${message.enteredBy.project.category.code}.png" with="40" height="40"/>
				</#if>
				<div class="message-body">
					<div class="subject">
						<a href="${base}/${project.uniqueId}/message/${box}/detail?messageId=${message.id}">${message.subject}</a>
						<#if box == 'inbox'>
						<span>接收于 <@prettyTime date=message.entered /> (${message.entered?string('yyyy-MM-dd HH:mm')})</span>
						<a href="${base}/${project.uniqueId}/message/${box}/form?messageId=${message.id}&toId=${message.from.id}">回复</a>
						<#else>
						<span>发送于 <@prettyTime date=message.entered /> (${message.entered?string('yyyy-MM-dd HH:mm')})</span>
						</#if>
						<!--<a href="#">删除</a>-->
					</div>
					<div class="content">
						${message.shortContent}
					</div>
				</div>
			</li>
			</#list>
		</ul>
		<div id="pagination${id}" class="right"></div>
		</#if>
	</div>
</div>