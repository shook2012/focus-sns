<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "folder" />
		<form id="folder-form${id}" action="${base}/document/folder/form-action" 
			method="post" class="folder-form">
			<div>
				<label for="name" class="title">目录名 <span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="folder.name" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
				</#if>
			</div>
			<div>
				<label>请选择上一级目录</label>
				<br/>
				<div id="folderChooser${id}"></div>
			</div>
			<br class="clear"/>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="folder.id" />
				<@spring.formHiddenInput path="folder.enteredId" />
				<@spring.formHiddenInput path="folder.modifiedId" />
				<@spring.formHiddenInput path="folder.projectId" />
				<@spring.formHiddenInput path="folder.entered" />
				<@spring.formHiddenInput path="folder.parentId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#folderChooser${id}').AdubyTree({
		url:'${base}/document/folder/tree-action?uniqueId=${project.uniqueId}',
		dataType:'json',
		onSelected:function(node){
			$('#folder-form${id} #parentId').val(node.id!=0?node.id:'');
        }
	});
});
</script>