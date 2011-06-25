<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if forums?size == 0>
		<div class="notice">当前无版块可显示！</div>
		<#else>
		<ul class="forums-list">
			<#list forums as forum>
			<li class="forum">
				<div class="forum-head">
					<span class="top right">
						<@security code="topic-add" userRequired="true">
						<a href="${base}/${project.uniqueId}/discussion/topic/form?forumId=${forum.id}">发表话题</a>
						</@security>
						<@security code="forum-edit" userRequired="true">
						<a href="${base}/${project.uniqueId}/discussion/forum/form?forumId=${forum.id}">编辑</a>
						</@security>
					</span>
					<h3><a href="${base}/${project.uniqueId}/discussion/topic/list?forumId=${forum.id}">${forum.name}</a></h3>
				</div>
				<#if forum.topics??>
				<div class="forum-body">
					<ul class="topics-list">
					<#list forum.topics as topic>
						<li>
							<span class="top right">${topic.views} 阅</span>
							<a href="${base}/${forum.project.uniqueId}/discussion/reply/list?topicId=${topic.id}">
								${topic.subject}
							</a>
							作者 
							<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
								${topic.enteredBy.nickname}
							</a>
							发表于 
							${topic.entered?string('yyyy-MM-dd HH:mm')}
						</li>
					</#list>
					</ul>
				</div>
				</#if>
			</li>
			</#list>	
		</ul>
		</#if>
	</div>
</div>