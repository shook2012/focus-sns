<#if project.enteredBy.id != user.id>
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<#if !member??>
			<li>
				<a class="ajaxAction" href="${base}/team/member/request?projectId=${project.id}">
				<#if project.category.code == 'people'>加为好友<#else>申请加入</#if>
				</a>
			</li>
			</#if>
			<@entity entity="Profile" exist="no" project=project user=user>
			<li>
				<a class="ajaxAction" href="${base}/commons/link/focus?fromId=${user.project.id}&toId=${project.profile.id}&entity=Profile">
					添加关注
				</a>
			</li>
			</@entity>
			<#if user??>
			<li class="last"><a class="popupAction" href="${base}/app/message/form?popup=true&fromId=${user.project.id}&toId=${project.id}">
			<#if project.category.code == 'people'>给我留言<#else>给我们留言</#if>
			</li>
			</#if>
		</ul>
	</div>
</div>
</#if>