<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<fieldset>
			<legend>Application</legend>
			<div>
				<label>Product Name</label>
				<br/>
				${application.productName}
			</div>
			<div>
				<label>Code Name</label>
				<br/>
				${application.codeName}
			</div>
			<div>
				<label>License</label>
				<br/>
				${application.license}
			</div>
			<div>
				<label>Version</label>
				<br/>
				${application.version}
			</div>
			<div>
				<label>Runtime</label>
				<br/>
				${application.runtime}
			</div>
		</fieldset>
		<fieldset>
			<legend>System Environment</legend>
			<div>
				<label>OS Name</label>
				<br/>
				${application.osName}
			</div>
			<div>
				<label>OS Arch</label>
				<br/>
				${application.osArch}
			</div>
			<div>
				<label>OS Version</label>
				<br/>
				${application.osVersion}
			</div>
		</fieldset>
		<fieldset>
			<legend>Java Environment</legend>
			<div>
				<label>Java Home</label>
				<br/>
				${application.javaHome}
			</div>
			<div>
				<label>Java Vendor</label>
				<br/>
				${application.javaVendor}
			</div>
			<div>
				<label>Java Temp Dir</label>
				<br/>
				${application.javaTmpdir}
			</div>
			<div>
				<label>Java Version</label>
				<br/>
				${application.javaVersion}
			</div>
		</fieldset>
	</div>
</div>