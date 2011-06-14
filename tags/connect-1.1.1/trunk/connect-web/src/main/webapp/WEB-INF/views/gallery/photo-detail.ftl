<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<div class="photo">
			<a href="${base}/commons/attachment/download/${photo.realFile.id}">
			<img class="thumbnail" src="${base}/commons/attachment/download/${photo.realFile.id}/400x400" id="${photo.id}"/>
			</a>
		</div>
	</div>
</div>