<#if page.result?size!=0>

<#assign id = widgetConfig.id />
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="activities-recent">
			<#list page.result as activity>
			<li>
				<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">
				<#if activity.enteredBy.project.profile.logo??>
					<img class="top left thumbnail" src="${base}/logo/download/${activity.enteredBy.project.profile.logo.id}/30x30"/>
				<#else>
					<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${activity.enteredBy.project.category.code}.png" width="30" height="30"/>
				</#if>
				</a>
				<div class="activity-detail">
					<#if activity.type=='user-input'>
						<#if activity.project.id==activity.enteredBy.project.id>
						<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">${activity.enteredBy.nickname}</a>
						<#else>
						<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">${activity.enteredBy.nickname}</a>
						@
						<a href="${base}/${activity.project.uniqueId}/profile">${activity.project.title}</a>
						</#if>	
					<#else>
					<a href="${base}/${activity.project.uniqueId}/profile">${activity.project.title}</a>
					</#if>
					<@activityRender activity=activity imageBase="${base}/static/images/faces" />
					<div>
						<span class="right"><@prettyTime date=activity.entered/></span>
					</div>
					<br class="clear" />
				</div>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>