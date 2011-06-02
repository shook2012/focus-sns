<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if forums?size == 0 >
		<div class="notice">当前无话题版块，因此无法发表话题.</div>
		<#else>
		<@spring.bind "topic" />
		<form id="topic-form${id}" class="topic-form" method="post"
			 action="${base}/discussion/topic/form-action" >
			<div>
				<label for="forumId" class="title">版块</label>
				<br/>
				<@spring.formSingleSelect path="topic.forumId" options=forumOptions />
			</div>
			<div>
				<label for="subject" class="title">话题名</label>
				<br/>
				<@spring.formInput path="topic.subject" />		
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="content" class="title">内容</label>
				<br/>
				<@spring.formTextarea path="topic.content" attributes='class="xheditor-simple"' />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="topic.id" />
				<@spring.formHiddenInput path="topic.forumId" />
				<@spring.formHiddenInput path="topic.answerId" />
				<@spring.formHiddenInput path="topic.enteredId" />
				<@spring.formHiddenInput path="topic.modifiedId" />
				<@spring.formHiddenInput path="topic.entered" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form>
		</#if>
	</div>
</div>