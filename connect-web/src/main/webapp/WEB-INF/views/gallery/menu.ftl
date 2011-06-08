<@security code="album-add|photo-add">
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<@security code="album-add">
			<li><a href="${base}/${project.uniqueId}/gallery/album/form">添加相册</a></li>
			</@security>
			<@security code="photo-add">
			<li><a href="${base}/${project.uniqueId}/gallery/photo/form">上传相片</a></li>
			</@security>
		</ul>
	</div>
</div>
</@security>