<#if page.result?size gt 0>
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body" style="height:180px;">
		<div class="slideshow">
			<#list page.result as photo>
			<a href="${base}/${project.uniqueId}/gallery/photo/detail?albumId=${photo.album.id}&photoId=${photo.id}" title="${photo.name!''}">
				<img src="${base}/commons/attachment/download/${photo.realFile.id}/150x150"/>
			</a>	
			</#list>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
    $('.slideshow').cycle();
});
</script>
</#if>