<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if page.result?size == 0>
		<div class="notice">当前无内容可显示！</div>
		<#else>
		<ul class="profiles-list">
			<#list page.result as profile>
			<li>
				<a href="${base}/${profile.project.uniqueId}/profile">
					<#if profile.logo??>
					<img class="top left thumbnail" src="${base}/logo/download/${profile.logo.id}/40x40"/>
					<#else>
					<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${profile.project.category.code}.png" with="40" height="40"/>
					</#if>
				</a>
				<div class="profile-body">
					<div class="profile-content">
						<#if user?? && user.project.profile.id != profile.id>
						<ul class="top right">
							<@entity entity="Profile" exist="no" project=profile.project user=user>
							<li>
								<a class="ajaxAction" href="${base}/list/link/focus?fromId=${user.project.id}&toId=${profile.id}&entity=Profile">添加关注</a>
							</li>
							</@entity>
							<li>
								<a  class="popupAction" href="${base}/app/message/form?fromId=${user.project.id}&toId=${profile.project.id}&uniqueId=${profile.project.uniqueId}">发送消息</a>
							</li>
						</ul>
						</#if>
						<a href="${base}/${profile.project.uniqueId}/profile">${profile.title}</a>
						<p>${profile.shortDescription!''}</p>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			</#list>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
		<br/>
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