<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if !user??>
			<div class="notice">回答问题，请先 <a href="#" class="loginAction">登录</a></div>
		<#else>
		<@spring.bind "answer" />
		<form id="answer-form${id}" class="answer-form"
			action="${base}/knowledge/answer/form-action" method="post">
			<div>
				<@spring.formTextarea path="answer.content" attributes='class="xheditor-mini"' />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="answer.id" />
				<@spring.formHiddenInput path="answer.enteredId" />
				<@spring.formHiddenInput path="answer.entered" />
				<@spring.formHiddenInput path="answer.modifiedId" />
				<@spring.formHiddenInput path="answer.questionId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
				<br class="clear"/>
			</div>
		</form>
		</#if>
	</div>
</div>