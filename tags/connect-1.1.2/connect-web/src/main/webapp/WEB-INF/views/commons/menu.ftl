<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<#list menuitems as item>
			<li>
				<a href="<@freemarkerRender template=item[0] />">${item[1]}</a>
			</li>
			</#list>
		</ul>
	</div>
</div>