<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<div class="head">
		<h3>${widgetConfig.title}</h3>
		<div class="tabs">
			<a <#if !Session.questionOrder?? || Session.questionOrder=='date'>class="current"</#if> href="${base}/${project.uniqueId}/knowledge/question/list?questionOrder=date"> 最近</a>
			<a <#if Session.questionOrder?? && Session.questionOrder=='answer'>class="current"</#if> href="${base}/${project.uniqueId}/knowledge/question/list?questionOrder=answer"> 回答</a>
			<a <#if Session.questionOrder?? && Session.questionOrder=='view'>class="current"</#if> href="${base}/${project.uniqueId}/knowledge/question/list?questionOrder=view"> 浏览</a>
		</div>
	</div>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无问题可显示！</div>
		<#else>
		<ul class="questions-list">
		<#list page.result as question>
			<li>
				<span class="span-1 answers">
					<div class="counts">
						${question.answers?size}
					</div>
					<div>
						回答
					</div>
				</span>
				<span class="span-1 views">
					<div class="counts">
						${question.views}
					</div>
					<div>
						浏览
					</div>
				</span>
				<span>
					<div class="title">
						<a href="${base}/${project.uniqueId}/knowledge/question/detail?questionId=${question.id}">${question.title}</a>
					</div>
					<div>
						<span class="right">
							<@prettyTime date=question.entered />
							<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
						</span>
						<#if question.tags??>
						<ul class="tags-list">
							<#list question.tags as tag>
							<li>
							<a>${tag.name}</a>
							</li>
							</#list>
						</ul>
						</#if>
					</div>
				<span>
				<br class="clear"/>
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