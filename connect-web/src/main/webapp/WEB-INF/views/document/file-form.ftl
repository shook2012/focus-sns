<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if folders?size == 0 >
		<div class="notice">
			当前无上传目录，请先添加目录!
		</div>
		<#else>
		<@spring.bind "file" />
		<form id="file-form${id}" action="${base}/Document/file/form-action" 
			class="file-form" method="post">
			<div>
				<label>请选择上传目录</label>
				<br/>
				<div id="folderChooser${id}"></div>
				<br class="clear"/>
			</div>
			<div>
				<input type="file" id="select-file${id}" name="file" />
			</div>
			<div>
				<button type="submit">上传</button>
				<@spring.formHiddenInput path="file.id" />
				<@spring.formHiddenInput path="file.enteredId" />
				<@spring.formHiddenInput path="file.modifiedId" />
				<@spring.formHiddenInput path="file.folderId" />
			</div>
		</form>
		</#if>
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