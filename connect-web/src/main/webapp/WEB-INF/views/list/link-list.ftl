<div id="${widgetConfig.id}" class="widget">
	<div class="head">
		<h3>${widgetConfig.title}</h3>
		<div class="tabs">
			<a <#if !Session.linkEntity?? || Session.linkEntity=='Question'>class="current"</#if> href="${base}/${project.uniqueId}/list?linkEntity=Question">问题</a>
			<a <#if Session.linkEntity?? && Session.linkEntity=='Post'>class="current"</#if> href="${base}/${project.uniqueId}/list?linkEntity=Post">博文</a>
			<a <#if Session.linkEntity?? && Session.linkEntity=='Topic'>class="current"</#if> href="${base}/${project.uniqueId}/list?linkEntity=Topic">话题</a>
		</div>
	</div>
	<div class="body">
		<#if page.result?size==0>
		<div class="notice">当前我收藏内容可显示！</div>
		<#else>
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
		</#if>
	</div>
</div>