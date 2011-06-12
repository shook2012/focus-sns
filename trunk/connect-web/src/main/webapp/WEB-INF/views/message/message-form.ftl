<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "message" />
		<form id="message-form${id}" class="message-form"
		action="${base}/message/message/form-action" method="post">
			<div>
				<label for="recipient">收件人</label>
				<br/>
				<@spring.formInput path="message.to.title" />
			</div>
			<div>
				<label for="subject">主题<span class="required"> * </span></label>
				<br/>
				<#if message.reply>
					<input name="subject" value="RE:${message.subject!''}" />
				<#else>
					<@spring.formInput path="message.subject" />
				</#if>
			</div>
			<div>
				<label for="content">内容<span class="required"> * </span></label>
				<br/>
				<@spring.formTextarea path="message.content" />
			</div>
			<div>
				<button class="button" type="submit">发送</button>
				<@spring.formHiddenInput path="message.id" />
				<@spring.formHiddenInput path="message.fromId" />
				<@spring.formHiddenInput path="message.toId" />
				<@spring.formHiddenInput path="message.enteredId" />
				<@spring.formHiddenInput path="message.entered" />
				<#if Request.popup?? && Request.popup>
				<input type="hidden" name="ajax" value="true"/>
				</#if>
			</div>
		</form>
	</div>
</div>

<#if Request.popup?? && Request.popup>
<script type="text/javascript">
$(document).ready(function(){
	$('#message-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var subject = $.trim(formData[1].value);
			var content = $.trim(formData[2].value);
			if(subject=='' || content=='') {
				return false;
			}
		},
		success: function(message){
			setTimeout(function(){
				window.location.href='?messageId=' + message.id;
			}, 500);
		}
	});
});
</script>
</#if>