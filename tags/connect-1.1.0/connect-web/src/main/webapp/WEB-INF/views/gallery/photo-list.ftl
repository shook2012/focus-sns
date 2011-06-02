<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="photos-list">
		<#list page.result as photo>
			<li>
			<a href="${base}/${project.uniqueId}/gallery/photo/detail?albumId=${photo.albumId}&photoId=${photo.id}">
				<img class="thumbnail <#if (RequestParameters.photoId!0)?number == photo.id>current</#if>" 
					src="${base}/commons/attachment/download/${photo.realFile.id}/75x75"/>
			</a>
			</li>
		</#list>
		</ul>
		<br class="clear"/>
	</div>
</div>