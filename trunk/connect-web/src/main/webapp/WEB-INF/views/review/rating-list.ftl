<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size==0>
		<div class="notice">当前无内容可显示!</div>
		<#else>
		<ul class="rating-list">
			<#list page.result as rating>
			<li>
				<a href="${base}/${rating.enteredBy.project.uniqueId}/profile">
				<#if rating.enteredBy.project.profile.logo??>
					<img class="left thumbnail" src="${base}/logo/download/${rating.enteredBy.project.profile.logo.id}/35x35"/>
				<#else>
					<img class="left thumbnail" src="${base}/themes/${theme}/stock/${rating.project.category.code}.png" width="35" height="35"/>
				</#if>
				</a>
				<div class="rating-detail">
					<div id="${rating.rating}" class="rating"></div>
					<p>
					${rating.comment}
					</p>
				<div>
			</li>
			</#list>
		</ul>
		<div id="pagination${id}" class="right"></div>
		</#if>
	</div>
</div>

<#if page.totalPages gt 1>
<script type="text/javascript">
$(document).ready(function(){
	$("#pagination${id}").pagination(${page.totalCount}, {
        items_per_page: ${page.pageSize},
        current_page: ${page.pageNo}-1,
        callback: function(pageNo, container){
            if((pageNo+1)!=${page.pageNo}) {
				document.location.href='?pageNo=' + (pageNo+1);
            }
            return false;
        }
	});
});
</script>
</#if>

<script type="text/javascript">
$(document).ready(function(){
	$('.rating').each(function(){
		var score = $(this).attr('id');
		$(this).raty({
			path: '${base}/static/components/jquery-raty/img',
			readOnly: true,
			start: score,
			half:  true
		});
	});
});
</script>