<#import "/META-INF/spring.ftl" as spring />

<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if user.project.profile.logo??>
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/60x60"/>
		<#else>
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/themes/${theme}/stock/${user.project.category.code}.png"/>		
		</#if>
		<@spring.bind "activity" />
		<form id="activity-form${id}" class="activity-form"
			action="${base}/stream/activity/form-action" method="post">
			<div>
				<@spring.formTextarea path="activity.description"/>
			</div>
			<div>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="sina"/>
					<img src="${base}/static/images/sina_16x16.png">
				</span>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="tencent"/>
					<img src="${base}/static/images/tencent_16x16.png">
				</span>
				<button id="${id}submitButton" type="submit" class="button right">分享</button>
				<br class="clear"/>
			</div>
			<@spring.formHiddenInput path="activity.type" />
			<@spring.formHiddenInput path="activity.projectId" />
			<@spring.formHiddenInput path="activity.enteredId" />
			<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#activity-form${id} .syncTarget').click(function(){
		if($(this).attr('checked')) {
			var target = $(this).val();
			var url = '${base}/oauth/authorized?target=' + target;
			$.get(url, function(o){
				if(!o.authorized) {
					var u = '${base}/oauth/authorizationUrl?target=' + target;
					$.get(u, function(o){
						showModalWindow(o.authUrl, {width: '800px', height: '600px'});
					})
				}
			});
		}
	});
});
</script>