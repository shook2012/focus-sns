<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<form action="${base}/admin/features/form-action" method="post">
		<table>
			<thead>
				<tr>
					<th>显示名</th>
					<th>编码</th>
					<th>排序值</th>
					<th>状态</th>
					<th>权限</th>
				</tr>
			</thead>
			<tbody>
				<#list features as feature>
				<tr>
					<td>
						<input name="labels" value="${feature.label!''}" />
					</td>
					<td>
						<input name="code" value="${feature.code!''}" readonly="readonly"/>
					</td>
					<td>
						<input name="levels" value="${feature.level!''}" size="2" />
					</td>
					<td>
						<input id="${feature.id}" type="checkbox" <#if feature.show>checked="checked"</#if> />
						<input id="showFeature${feature.id}" type="hidden" name="shows" value="${feature.show?string}" />
						<input type="hidden" name="ids" value="${feature.id}"/>
					</td>
					<td>
						<select name="roleIds">
							<#list roles as role>
							<option value="${role.id}" <#if role.id == feature.roleId>selected="selected"</#if> >${role.name}</option>
							</#list>
						</select>
					</td>
				</tr>
				</#list>
			</tbody>		
			<tfoot>
				<tr>
					<td>
						<button id="submit" class="button">提交</button>
						<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
					</td>
				</tr>
			</tfoot>
		</table>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#${id} input[type=checkbox]').click(function(){
		var id = $(this).attr('id');
		var checked = $(this).attr('checked');
		$('#showFeature'+id).val(checked);
	});
});
</script>