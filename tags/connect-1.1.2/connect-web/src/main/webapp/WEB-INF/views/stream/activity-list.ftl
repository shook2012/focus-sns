<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />
<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
	<a name="activities-list"></a>
	<#if page.result?size == 0>
		<div class="info">当前无最新动态可显示!</div>
	<#else>
		<ul id="activities-list${id}" class="activities-list">
			<#list page.result as activity>
			<li class='activity<#if !activity_has_next> last</#if>'>
				<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">
				<#if activity.enteredBy.project.profile.logo??>
					<img class="top left thumbnail" src="${base}/logo/download/${activity.enteredBy.project.profile.logo.id}/40x40"/>
				<#else>
					<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${activity.enteredBy.project.category.code}.png" width="40" height="40"/>
				</#if>
				</a>
				<div class="activity-wrapper">
					<div class="activity-body">
						<div class="right">
							<span class="pretty-tim "><@prettyTime date=activity.entered/></span>
							<a class="comment-action" id="${activity.id}" href="#">评论(${activity.children?size})</a>
						</div>
						<@activityRender activity=activity imageBase="${base}/static/images/faces" />
					</div>
					<#if activity?size gt 0>
					<ul id="comments-list${activity.id}" class="comments-list" style="display:none">
						<#list activity.children as comment>
						<li>
							<a href="${base}/${comment.enteredBy.project.uniqueId}/profile">
								<#if comment.enteredBy.project.profile.logo??>
								<img class="top left thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/35x35"/>
								<#else>
								<img class="top left thumbnail" src="${base}/themes/${theme}/stock/${comment.enteredBy.project.category.code}.png" width="35" height="35"/>
								</#if>
							</a>
							<p class="comment-content"><@activityRender activity=comment imageBase="${base}/static/images/faces" /></p>
							<span class="top right"><@prettyTime date=comment.entered /> </span>
							<br class="clear"/>
						</li>
						</#list>
					</ul>
					</#if>
					<form id="activity-comment-form${activity.id}" class="activity-comment-form"
						action="${base}/stream/activity/form-action" method="post" style="display:none">
					<#if !user??>
						<div class="notice">添加评论，请先 <a href="${base}/app/user/login" class="popupAction">登录</a></div>					
					<#else>
						<div>
							<textarea name="description"></textarea>
						</div>
						<div class="last">
							<!--
							<span class="float-right">最多140个字符</span>
							-->
							<button class="button" type="submit">评论</button>
							<button id="${activity.id}" class="button cancel-action" type="reset">取消</button>
							<input type="hidden" name="parentId" value="${activity.id}" />
							<input type="hidden" name="projectId" value="${project.id}"/>
							<input type="hidden" name="enteredId" value="${user.id}"/>
							<input type="hidden" name="modifiedId" value="${user.id}"/>
							<input type="hidden" name="type" value="comment" />
							<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
							<br class="clear"/>
						</div>					
					</#if>
					</form>
				</div>
			</li>
			</#list>
		</ul>
		<#if page.totalPages gt 1>
		<div id="pagination${id}" class="right"></div>
		</#if>
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
				document.location.href='?pageNo=' + (pageNo+1) + '#activities-list';
            }
            return false;
        }
	});
});
</script>
</#if>

<script type="text/javascript">
$(document).ready(function(){
	$('#${id} .comment-action').click(function(){
		var id = $(this).attr('id');
		$('#activity-comment-form'+id).toggle();
		$('#comments-list'+id).slideToggle('slow');
		return false;
	});
});
</script>