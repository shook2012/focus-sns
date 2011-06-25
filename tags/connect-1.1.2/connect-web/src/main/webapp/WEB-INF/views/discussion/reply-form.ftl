<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<a name="reply"></a>
		<div class="span-3 author">
				<a href="${base}/${user.project.uniqueId}/profile">
					<#if user.project.profile.logo??>
					<img class="thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/75x75"/>
					<#else>
					<img class="thumbnail" src="${base}/themes/${theme}/stock/${user.project.category.code}.png"/>
					</#if>
				</c:choose>
				</a>
				<div>
					<a href="${base}/${user.project.uniqueId}/profile">${user.project.title}</a>
				</div>
				<div>
					<span>注册日期:${user.entered?string('yyyy/M/d')}</span>
				</div>
		</div>
		<@spring.bind "reply" />
		<form id="reply-form${id}" action="${base}/discussion/reply/form-action#reply" 
			method="post" class="reply-form">
				<div>
					<label for="subject" class="title">主题 <span class="required"> * </span></label>
					<br/>
					<input name="subject" value="RE: ${topic.subject}" />
				</div>
				<div>
					<label>内容 <span class="required"> * </span></label>
					<br/>
					<@spring.formTextarea path="reply.content" attributes='class="xheditor-mini"' />
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<@spring.formHiddenInput path="reply.id" />
					<@spring.formHiddenInput path="reply.topicId" />
					<@spring.formHiddenInput path="reply.enteredId" />
					<@spring.formHiddenInput path="reply.modifiedId" />
					<@spring.formHiddenInput path="reply.quoteId" />
					<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
				</div>
		</form>
	</div>
</div>