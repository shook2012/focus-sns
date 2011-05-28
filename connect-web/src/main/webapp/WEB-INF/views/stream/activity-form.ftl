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
				<#if showToolbar!false>
				<ul class="toolbar left">
					<li><a href="#" id="insertFace${id}">表情</a></li>
					<li><a href="#" id="insertLink${id}">链接</a></li>
					<li><a href="#" id="insertImage${id}">图片</a></li>
				</ul>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="sina"/>
					<img src="${base}/static/images/sina_16x16.png">
				</span>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="tencent"/>
					<img src="${base}/static/images/tencent_16x16.png">
				</span>
				</#if>
				<button id="${id}submitButton" type="submit" class="button right">提交</button>
				<br class="clear"/>
			</div>
			<@spring.formHiddenInput path="activity.type" />
			<@spring.formHiddenInput path="activity.projectId" />
			<@spring.formHiddenInput path="activity.enteredId" />
			<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
		</form>
		<#if showToolbar!false>
		<div id="faceLayer${id}" style="display:none">
			<ul class="faces-list">
				<#list ['angel','angry','cool','crying','devilish','embarrassed','glasses','kiss','laugh','monkey','plain','raspberry','sad','sick','smile','smile-big','smirk','surprise','tired','uncertain','wink','worried'] as face>
				<li><a id="${face}"><img src="${base}/static/images/faces/face-${face}.png"/></a></li>
				</#list>
			</ul>
		</div>
		<div id="linkLayer${id}" style="display:none">
			<a>插入链接</a>
			<input type="text" size="40"/>
		</div>
		<div id="imageLayer${id}" style="display:none">
			<a>插入图片</a>
			<input type="text" size="40"/>
		</div>
		</#if>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#insertFace${id}').aqLayer({
        margin: '0',
        object: '#faceLayer${id}',
        onLoad: function(){
        	$('#linkLayer${id}').data('aqLayer').trigger('close');
        	$('#imageLayer${id}').data('aqLayer').trigger('close');
        }
    });
    $('.faces-list a').click(function(){
		var faceId = $(this).attr('id');
		$('#description${id}').val($('#description${id}').val()+'[face:'+faceId+']');
		$('#faceLayer${id}').data('aqLayer').trigger('close');
		return false;
    });
    $('#insertLink${id}').aqLayer({
        margin: '0',
        object: '#linkLayer${id}',
        onLoad: function(){
	    	$('#faceLayer${id}').data('aqLayer').trigger('close');
	    	$('#imageLayer${id}').data('aqLayer').trigger('close');
	    }
    });
    $('#linkLayer${id} a').click(function(){
        var link = $('#linkLayer${id} input').val();
        if($.trim(link)=='') {
			link = '替换成链接地址';
        }
        $('#description${id}').val($('#description${id}').val()+'[link:'+link+']');
        $('#linkLayer${id}').data('aqLayer').trigger('close');
    });
    $('#insertImage${id}').aqLayer({
        margin: '0',
        object: '#imageLayer${id}',
        onLoad: function(){
	    	$('#faceLayer${id}').data('aqLayer').trigger('close');
	    	$('#linkLayer${id}').data('aqLayer').trigger('close');
	    }
    });
    $('#imageLayer${id} a').click(function(){
        var image = $('#imageLayer${id} input').val();
        if($.trim(image)=='') {
			image = '替换成图片地址';
        }
        $('#description${id}').val($('#description${id}').val()+'[img:'+image+']');
        $('#imageLayer${id}').data('aqLayer').trigger('close');
    });
});
</script>