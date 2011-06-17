<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="members-info">
			<#list needApprove as member>
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
					<#if member.user.project.profile.logo??>
					<img class="left thumbnail" src="${base}/logo/download/${member.user.project.profile.logo.id}/30x30"/>
					<#else>
					<img class="left thumbnail" src="${base}/themes/${theme}/stock/${member.user.project.category.code}.png" with="30" height="30"/>
					</#if>
				</a>
				<div class="member-detail">
					<div>
						<a target="_blank" href="${base}/${member.user.project.uniqueId}/profile">
							${member.user.project.title}
						</a>
					</div>
					<div>
						<a class="ajaxAction" href="${base}/team/member/approve?memberId=${member.id}">同意</a>
						|
						<a class="ajaxAction" href="#">拒绝</a>
					</div>
				</div>
			</li>
			</#list>
			<#list needAccept as member>
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
					<#if member.project.profile.logo??>
					<img class="left thumbnail" src="${base}/logo/download/${member.project.profile.logo.id}/30x30"/>
					<#else>
					<img class="left thumbnail"src="${base}/themes/${theme}/stock/${member.project.category.code}.png" with="30" height="30"/>
					</#if>
				</a>
				<div class="member-detail">
					<div>
						<a target="_blank" href="${base}/${member.project.uniqueId}/profile">
							${member.project.title}
						</a>
					</div>
					<div>
						<a class="ajaxAction" href="${base}/team/member/approve?memberId=${member.id}">同意</a>
						|
						<a class="ajaxAction" href="#">拒绝</a>
					</div>
				</div>
			</li>
			</#list>
		</ul>
		<div class="clear"></div>
	</div>
</div>