<#import "/META-INF/spring.ftl" as spring />

<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "question" />
		<form id="question-form${id}" class="question-form"
			action="${base}/knowledge/question/form-action" method="post">
			<div>
				<label for="title" class="title">标题 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="question.title" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if> 
			</div>
			<div>
				<label for="content" class="title">内容 <span class="required">*</span></label>
				<br/>
				<@spring.formTextarea path="question.content" attributes='class="xheditor-mini"'/>
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label>Tags</label>
				<br/>
				<#if question.tags??>
				<ul class="tags-list">
				<#list question.tags as tag>
					<li>
						<a> ${tag.name} </a>
						<a class="deleteAction" href="${base}/commons/tag/delete-action?tagId=${tag.id}"> x </a></span>
					</li>
				</#list>
				</ul>
				<br />
				</#if>
				<#list 1..9 as x>
				<input name="tags" class="tag-input" />
				<#if x%3==0>
				<br />
				</#if>
				</#list> 
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="question.id" />
				<@spring.formHiddenInput path="question.enteredId" />
				<@spring.formHiddenInput path="question.entered" />
				<@spring.formHiddenInput path="question.modifiedId" />
				<@spring.formHiddenInput path="question.projectId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('.tag-input').each(function(){
		$(this).autocomplete({
			serviceUrl:'${base}/commons/tag/auto-action',
		    minChars:2,
		    delimiter: /(,|;)\s*/, // regex or character
		    maxHeight:400,
		    width:170,
		    zIndex: 9999,
		    deferRequestBy: 0, //miliseconds
		    params: { uniqueId:"${project.uniqueId}" }, //aditional parameters
		    noCache: false, //default is false, set to true to disable caching
		    // callback function:
		    onSelect: function(value, data){
		    }
		});	
	});
});
</script>