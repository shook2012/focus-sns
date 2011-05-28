<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if features?? && features?size gt 0>
		<ul>
			<#list features as feature>
			<li <#if feature.code == featureCode>class="actived"</#if>>
				<a href="${base}/${project.uniqueId}/${feature.code}" title="${feature.label}">
					<span>${feature.label}</span>
				</a>
			</li>
			</#list>
		</ul>
		</#if>
	</div>
</div>