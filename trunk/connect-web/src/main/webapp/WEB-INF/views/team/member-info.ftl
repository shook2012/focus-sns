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
				<span class="span-2">
				<a href="${base}/${member.user.project.uniqueId}/profile">
					<#if member.user.project.profile.logo??>
					<img class="thumbnail" src="${base}/logo/download/${member.user.project.profile.logo.id}/35x35"/>
					<#else>
					<img class="thumbnail"src="${base}/themes/${theme}/stock/${member.user.project.category.code}.png" with="35" height="35"/>
					</#if>
				</a>
				</span>
				<span class="span-4">
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
				</span>
				<div class="clear"></div>
			</li>
			</#list>
			<#list needAccept as member>
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
					<#if member.project.profile.logo??>
					<img class="thumbnail" src="${base}/logo/download/${member.project.profile.logo.id}/35x35"/>
					<#else>
					<img class="thumbnail"src="${base}/themes/${theme}/stock/${member.project.category.code}.png" with="35" height="35"/>
					</#if>
				</a>
				<div class="desc">
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
					<div class="clear"></div>
				</div>
			</li>
			</#list>
		</ul>
		<div class="clear"></div>
	</div>
</div>