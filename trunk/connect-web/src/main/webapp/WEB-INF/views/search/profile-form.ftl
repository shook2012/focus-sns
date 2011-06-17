<#import "/META-INF/spring.ftl" as spring />

<#assign id=widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<form class="search-profile-form" action="${base}/search/profile">
			<div>
				<input name="title" value="${RequestParameters.title!''}"/>
			</div>
			<div>
				<select name="categoryId">
					<#list categories as category>
					<option value="${category.id}" <#if currentCategory?? && currentCategory.id==category.id>selected="selected"</#if> >${category.label}</option>
					</#list>
				</select>		
				<input type="submit" value="搜索" />
			</div>
		</form>
	</div>
</div>