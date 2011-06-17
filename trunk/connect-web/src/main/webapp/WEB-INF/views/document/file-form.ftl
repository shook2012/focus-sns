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
		<form id="file-form${id}" action="${base}/document/file/form-action" 
			class="file-form" method="post" enctype="multipart/form-data">
			<div>
				<label>请选择上传目录</label>
				<br/>
				<div id="folderChooser${id}"></div>
				<br class="clear"/>
			</div>
			<div>
				<label>显示名</label>
				<br/>
				<@spring.formInput path="file.name" />
			</div>
			<div>
				<label>上传目录</label>
				<br/>
				<input id="folder" value="<#if file.folder??>${file.folder.name!''}</#if>" readonly="readonly"/>
			</div>
			<div>
				<input type="file" name="file" />
			</div>
			<#if Session.files?? && Session.files?size gt 0>
			<div>
				<label>最近上传</label>
				<br />
				<table>
					<thead>
						<th>ID</th>
						<th>文件名</th>
						<th>操作</th>
					</thead>
					<tbody>
						<#list Session.files as file>
						<tr>
							<td>${file.id}</td>
							<td>
								<#if file.name?? && file.name!=''>${file.name}
								<#else>${file.realFile.name}</#if>
							</td>
							<td>
								<@security code="file-edit" userRequired="true">
								<a href="${base}/${project.uniqueId}/document/file/form?fileId=${file.id}">编辑</a>
								</@security>
								<@security code="file-edit" userRequired="true">  
								|
								<a href="${base}/document/file/delete-action?fileId=${file.id}">删除</a>
								</@security>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
			</#if>
			<div>
				<#if file.id?? && file.id gt 0>
					<button type="submit">提交</button>
				<#else>
					<button type="submit">上传</button>
				</#if>
				<@spring.formHiddenInput path="file.id" />
				<@spring.formHiddenInput path="file.enteredId" />
				<@spring.formHiddenInput path="file.entered" />
				<@spring.formHiddenInput path="file.modifiedId" />
				<@spring.formHiddenInput path="file.folderId" />
				<@spring.formHiddenInput path="file.realFileId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form>
		</#if>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#file-form${id}').submit(function(){
		var folderId = $.trim($('#file-form${id} #folderId').val());
		var folder = $.trim($('#file-form${id} #folder').val());
		if(folder=='' || folderId=='') {
			alert('请选择上传目录！');
			return false;
		}
	});
	$('#folderChooser${id}').AdubyTree({
		url:'${base}/document/folder/tree-action?uniqueId=${project.uniqueId}',
		dataType:'json',
		onSelected:function(node){
			$('#file-form${id} #folderId').val(node.id!=0?node.id:'');
			$('#file-form${id} #folder').val(node.id!=0?node.data:'');
        }
	});
});
</script>