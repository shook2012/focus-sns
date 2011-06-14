<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="replies-list">
		<#list page.result as reply>
			<li>
				<div class="reply">
					<div class="span-3 author">
						<a href="${base}/${reply.enteredBy.project.uniqueId}/profile">
							<#if reply.enteredBy.project.profile.logo??>
							<img class="thumbnail" src="${base}/logo/download/${reply.enteredBy.project.profile.logo.id}/75x75"/>
							<#else>
							<img class="thumbnail" src="${base}/themes/${theme}/stock/${reply.enteredBy.project.category.code}.png"/>
							</#if>
						</a>
						<div>
							<a href="#">${reply.enteredBy.nickname}</a>
						</div>
						<div>
							<span>注册日期:${reply.enteredBy.entered?string('yyyy/M/d')}</span>
						</div>
					</div>
					<div class="reply-head">
						<span class="top right">回复于 ${reply.entered?string('yyyy-MM-dd HH:mm')}</span>
						<h4>${reply.subject}</h4>
					</div>
					<div class="reply-body">
						<#if reply.quote??>
						<div class="quote">
						<div class="quote-head">${reply.quote.subject}</div>
						<div class="quote-body">${reply.quote.content}</div>
						</div>
						</#if>
						${reply.content}
					</div>
					<div class="reply-foot">
						<span class="top right">
							<a id="${reply.id}" class="quoteAction" href="#reply">引用</a>
						</span>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</#list>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
	</div>
</div>