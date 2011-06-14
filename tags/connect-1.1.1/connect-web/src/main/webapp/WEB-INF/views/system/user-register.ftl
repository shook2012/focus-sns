<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "registerBean" />
		<form id="register-form${id}"  class="register-form"
			action="${base}/system/user/register-action" method="post">
			<div>
				<label for="username" class="title">用户名<span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="registerBean.username" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="password" class="title">密码<span class="required"> * </span></label>
				<br/>
				<@spring.formPasswordInput path="registerBean.password" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="rePassword" class="title">确认密码<span class="required"> * </span></label>
				<br/>
				<@spring.formPasswordInput path="registerBean.rePassword" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<label for="nickname" class="title">昵称<span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="registerBean.nickname" />
				<#if showErrors!false>
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
				</#if>
			</div>
			<div>
				<button class="button" type="submit">加入 ${site.title}</button>
				<button class="button" type="reset">重置</button>
				<br class="clear"/>
			</div>
			<div>
				忘记密码？<a href="#">帮助</a>
				已经注册？<a href="${base}/login">登录</a>
			</div>
		</form>
	</div>
</div>