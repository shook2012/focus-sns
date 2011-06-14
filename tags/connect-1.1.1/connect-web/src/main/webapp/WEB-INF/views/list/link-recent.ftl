<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="link-list">
			<#list page.result as link>
			<li>
				<#if link.entity=='Question'>
				<a href="${base}/${link.linkedEntity.project.uniqueId}/knowledge/question/detail?questionId=${link.toId}">${link.linkedEntity.title}</a>
				<#elseif link.entity=='Post'>
				<a href="${base}/${link.linkedEntity.project.uniqueId}/blog/post/detail?postId=${link.toId}">${link.linkedEntity.title}</a>
				<#elseif link.entity=='Topic'>
				<a href="${base}/${link.linkedEntity.forum.project.uniqueId}/discussion/reply/list?topicId=${link.toId}">${link.linkedEntity.subject}</a>
				</#if>
			</li>
			</#list>
		</ul>
	</div>
</div>