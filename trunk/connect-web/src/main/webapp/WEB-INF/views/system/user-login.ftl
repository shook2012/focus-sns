<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "loginBean"/>
		<form class="login-form" action="${base}/system/user/login-action" method="POST">
			<div>
           		<label for="username" class="title">用户名 <span class="required">*</span></label>
           		<br/>
           		<@spring.formInput path="loginBean.username" />
           		<#if showErrors!false>
           		<@spring.showErrors separator="<br/>" classOrStyle="error" />
           		</#if>
            </div>
			<div>
           		<label for="password" class="title">密码 <span class="required">*</span></label>
           		<br/>
             	<@spring.formPasswordInput path="loginBean.password" />
             	<#if showErrors!false>
             	<@spring.showErrors separator="<br/>" classOrStyle="error" />
             	</#if>
			</div>
			<div>
            	<label for="rememberMe" class="title">记住我</label>
            	<br/>
             	<@spring.formCheckbox path="loginBean.rememberMe" />
            </div>
            <div>
       			<button type="submit" class="button positive">
   					<img src="${base}/static/images/icons/button/tick.png"/>
   					登录
 				</button>
 				<button type="reset" class="button negative">
				   <img src="${base}/static/images/icons/button/cross.png"/>
				 	重置
				</button>
				<#if Request.popup?? && Request.popup>
				<input type="hidden" name="ajax" value="true" />
				</#if>
				<br class="clear"/>
       		</div>
       		<div>
				忘记密码？<a href="#">帮助</a>
				尚未注册？<a href="${base}/register">注册</a>
			</div>
		</form>
	</div>
</div>

<#if Request.popup?? && Request.popup>
<script type="text/javascript">
$(document).ready(function(){
	$('.login-form').ajaxForm({
		dataType: 'json',
		beforeSubmit: function(formData, $form){
			var username = $.trim(formData[0]);
			var password = $.trim(formData[1]);
			if(username=='' || password=='') {
				return false;
			}
		},
		success: function(user){
			if(user.id!=null) {
				window.location.reload();
			}
		}
	});
});
</script>
</#if>