<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div id="folder-tree${id}" class="folder-tree"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#folder-tree${id}').AdubyTree({
		url:'${base}/document/folder/tree-action?uniqueId=${project.uniqueId}',
		dataType:'json',
		onSelected: function(node){
			if(node.id > 0) {
				window.location.href = '${base}/${project.uniqueId}/document/file/list?folderId=' + node.id;
			}
		}
	});
});
</script>