<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "forum" />
		<form id="forum-form${id}" action="${base}/discussion/forum/form-action" 
			method="post" class="forum-form">
				<div>
					<label for="name" class="title">版块名</label>
					<br/>
					<@spring.formInput path="forum.name" />
					<#if showErrors!false>
					<@spring.showErrors separator="<br/>" classOrStyle="error" />
					</#if>
				</div>
				<div>
					<label for="discription" class="title">描述</label>
					<br/>
					<@spring.formTextarea path="forum.description" />
				</div>
				<div>
					<label for="level" class="title">排序值</label>
					<br/>
					<@spring.formInput path="forum.level" />
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<@spring.formHiddenInput path="forum.id" />
					<@spring.formHiddenInput path="forum.enteredId" />
					<@spring.formHiddenInput path="forum.modifiedId" />
					<@spring.formHiddenInput path="forum.projectId" />
					<@spring.formHiddenInput path="forum.entered" />
					<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
				</div>
		</form>
	</div>
</div>