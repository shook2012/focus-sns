<#import "/META-INF/spring.ftl" as spring />

<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if comments?size gt 0>
		<ul class="comments-list">
		<#list comments as comment>
			<li>
				<span class="span-2">
					<a href="${base}/profile/${comment.enteredBy.project.uniqueId}" title="${comment.enteredBy.project.title}">
					<#if comment.enteredBy.project.profile.logo??>
						<img class="thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/40x40"/>
					<#else>
						<img class="thumbnail" src="${base}/themes/${theme}/stock/${comment.enteredBy.project.category.code}.png" width="40px" height="40px"/>
					</#if> 
				</span>
				<span class="span6">
					<div>
						<span><a href="${base}/profile/${comment.enteredBy.project.uniqueId}" title="${comment.enteredBy.project.title}">
						${comment.enteredBy.project.title}
						</a></span>
						<span>
							评论于:${comment.entered}
						</span>
					</div>
					${comment.content}
				</span>
				<br class="clear"/>
			</li>
		</#list>
		</ul>
		</#if>
		<@spring.bind "comment" />
		<form id="comment-form${id}" class="comment-form"
			action="${base}/commons/comment/form-action" method="post">
			<#if !user??>
				<p>添加评论，请先<a href="${base}/app/login/form" class="loginAction">登录</a></p>
			<#else>
				<div>
					<label>评论内容:</label>
					<br/>
					<form:textarea path="content" cssClass="text"/>
					<@spring.formTextarea path="comment.content" />
				</div>
				<div>
					<button type="submit" class="button">评论</button>
					<@spring.formHiddenInput path="comment.enteredId" />
					<@spring.formHiddenInput path="comment.modifiedId" />
					<@spring.formHiddenInput path="comment.linkedId" />
					<@spring.formHiddenInput path="comment.entity" />
					<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
				</div>
			</#if>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#comment-form${id}').ajaxForm({
		dataType: 'json',
		success:function(obj){
			if(obj.id!=null) {
				window.location.reload();
			}
		}	
	});
});
</script>