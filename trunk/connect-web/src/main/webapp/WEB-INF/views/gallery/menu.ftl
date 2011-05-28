<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<li><a href="${base}/${project.uniqueId}/gallery/album/form">添加相册</a></li>
			<li class="last"><a href="${base}/${project.uniqueId}/gallery/photo/form">上传相片</a></li>
		</ul>
	</div>
</div>