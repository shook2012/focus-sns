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
			<#if menuitems?? && menuitems?size gt 0>
			<#list menuitems as menuitem>
			<li>
				<a href="${base}/${menuitem.code}">
					<span>${menuitem.label}</span>
				</a>
			</li>
			</#list>
			</#if>
		</ul>
	</div>
</div>