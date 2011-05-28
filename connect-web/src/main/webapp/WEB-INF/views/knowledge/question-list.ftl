<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无问题可显示！</div>
		<#else>
		<ul class="questions-list">
		<#list page.result as question>
			<li>
				<span class="top right">${question.answers?size}答/${question.views}阅</span>
				<a href="${base}/${project.uniqueId}/knowledge/question/detail?questionId=${question.id}">${question.title}</a>
			</li>
		</#list>
		</ul>
		<br/>
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