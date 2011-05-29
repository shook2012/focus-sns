<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<form id="logo-form${id}" class="logo-form" method="post"
			action="${base}/profile/logo/form-action">
			<div>
			<#if profile.logo??>
				<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>			
			<#else>
				<img class="thumbnail" src="${base}/themes/${theme}/stock/${project.category.code}.png"/>
			</#if>
			</div>
			<div>
				<input type="file" id="select-file${id}" name="file">
				<input type="hidden" name="profileId" value="${profile.id}"/>
			</div>
		</form>
	</div>
</div>

<#-- FIXME -->
<script type="text/javascript">
$(document).ready(function(){
	$('#select-file${id}').change(function(){
		$('#logo-form${id}').ajaxSubmit({
			dataType: 'json',
			success: function(profile){
				window.location.reload();
			}
		});
	});
});
</script>