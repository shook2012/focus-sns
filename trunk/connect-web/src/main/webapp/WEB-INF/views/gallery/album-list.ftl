<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无相册可显示！</div>
		<#else>
		<div class="albums-list">
			<#list page.result as album>
			<div class="slideshow">
			<#if album.photos?? && album.photos?size gt 0>
				<#list album.photos as photo>
					<a href="${base}/${project.uniqueId}/gallery/photos?albumId=${album.id}" title="${photo.name}">
						<img src="${base}/photo/download/${photo.realFile.id}/150x150"/>
					</a>	
				</#list>
			<#else>
				<a href="${base}/${project.uniqueId}/gallery/photo/form?albumId=${album.id}" title="${album.name}">
					<img src="${base}/static/images/nophoto.jpg" width="150" height="150"/>	
				</a>
			</#if>
			</div>
			</#list>
		</div>
		</#if>
		<br class="clear" />
	</div>
</div>