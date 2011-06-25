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
			<#if link.entity=='Question'>
			<li class="question">
				<#assign question = link.linkedEntity />
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
			<#elseif link.entity=='Post'>
			<li class="post">
				<#assign post = link.linkedEntity/>
				<span class="top right">${post.views} 阅</span>
				<a href="${base}/${link.linkedEntity.project.uniqueId}/blog/post/detail?postId=${link.toId}">${link.linkedEntity.title}</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${base}/${post.enteredBy.project.uniqueId}/profile">${post.enteredBy.nickname}</a>
				发表于
				${post.entered?string('yyyy-MM-dd HH:mm')}
			</li>
			<#elseif link.entity=='Topic'>
			<li class="topic">
				<#assign topic = link.linkedEntity/>
				<span class="top right">${topic.views} 阅</span>
				<a href="${base}/${topic.forum.project.uniqueId}/discussion/reply/list?topicId=${topic.id}">${topic.subject}</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">${topic.enteredBy.nickname}</a>
				发表于
				${topic.entered?string('yyyy-MM-dd HH:mm')}
			</li>
			</#if>
			</#list>
		</ul>
		</#if>
	</div>
</div>