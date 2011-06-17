<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul>
			<li>
				<a href="${base}">
					<span>主页<span>
				</a>
			</li>
			<#if categories?? && categories?size gt 0>
			<#list categories as category>
			<li <#if currentCategory?? && currentCategory.id=category.id>class="active"</#if>>
				<a href="${base}/${category.code}">
					<span>${category.label}</span>
				</a>
			</li>
			</#list>
			</#if>
		</ul>
	</div>
</div>