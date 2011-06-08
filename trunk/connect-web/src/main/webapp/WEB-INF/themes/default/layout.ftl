<#assign w=JspTaglibs["/META-INF/widget.tld"]>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#--
	<title>${site.title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="Keywords" content="${site.title}"/>
    <meta name="Description" content="${site.description}"/>
    -->
    
    <link rel="shortcut icon" type="image/x-icon" href="${base}/static/images/fav.ico" />

	<link rel="stylesheet" href="${base}/themes/default/css/screen.css" type="text/css" media="screen, projection"/>
    <link rel="stylesheet" href="${base}/themes/default/css/print.css" type="text/css" media="print"/>
    <!--[if lt IE 8]>
    <link rel="stylesheet" href="${base}/themes/default/css/ie.css" type="text/css" media="screen, projection"/>
    <![endif]-->

	<!-- plugins -->
    <link rel="stylesheet" href="${base}/themes/default/css/plugins/buttons/screen.css" type="text/css" media="screen, projection"/>


    <script type="text/javascript" src="${base}/static/components/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/components/global.js"></script>

    <script type="text/javascript" src="${base}/static/components/jquery-xheditor/xheditor-1.1.6-en.min.js"></script>

    <script type="text/javascript" src="${base}/static/components/jquery-form/jquery.form.min.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-busy/jquery.busy.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-datePicker/jquery.datePicker.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-datePicker/jquery.datePicker.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-datePicker/date.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-simplemodal/jquery.simplemodal.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-simplemodal/jquery.simplemodal.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-fancybox/jquery.fancybox-1.3.4.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-fancybox/jquery.mousewheel-3.0.4.pack.js"></script>

	<link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-aqLayer/jquery.aqLayer.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-aqLayer/jquery.aqLayer.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-adubyTree/themes/adubytree.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-adubyTree/jquery.adubytree.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-adubyTree/jquery.easing.1.3.js"></script>

    <script type="text/javascript" src="${base}/static/components/jquery-cycle/jquery.cycle.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-autocomplete/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-autocomplete/jquery.autocomplete.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-pagination/pagination.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-pagination/jquery.pagination.js"></script>
    
    <link rel="stylesheet" href="${base}/themes/default/css/style.css" type="text/css" media="screen, projection"/>
    <link rel="stylesheet" href="${base}/themes/default/css/widget.css" type="text/css" media="screen, projection"/>
    <script type="text/javascript">
    $(document).ready(function(){
		var hasLeftColumn = $.trim($('#body .left-column').html())!='';
		var hasRightColumn = $.trim($('#body .right-column').html())!='';
		if(!hasLeftColumn) {
			$('#body .left-column').remove();
			$('#body .main-column').addClass('span-16');
		}
		if(!hasRightColumn) {
			$('#body .right-column').remove();
			$('#body .main-column').addClass('span-19');
			$('#body .main-column').addClass('last');
		}
		if(!hasLeftColumn && !hasRightColumn) {
			$('#body .left-column').remove();
			$('#body .right-column').remove();
			$('#body .main-column').addClass('span-24');
			$('#body .main-column').addClass('last');
		}
    });
	</script>
</head>
<body>
	<div class="container">
		<div id="head">
			<a href="${base}">
			<img id="logo" src="${base}/themes/default/logo.png"/>
			</a>
			<div id="wrapper">
				<div id="user-menu">
					<@w.placeholder name="userMenu"/>
				</div>
				<div id="site-search">
					<@w.placeholder name="siteSearch"/>
				</div>
			</div>
			<br class="clear"/>
			<div id="main-menu">
				<@w.placeholder name="mainMenu"/>
			</div>
			<div id="sub-menu1">
				<@w.placeholder name="subMenu1"/>
			</div>
			<div id="sub-menu">
				<@w.placeholder name="subMenu"/>
			</div>
		</div>
		<div id="body">
			<div class="span-5 left-column">
				<@w.placeholder name="leftColumn"/>
			</div>
			<div class='span-11 main-column'>
				<@w.placeholder name="mainColumn"/>
			</div>
			<div class="span-8 last right-column">
				<@w.placeholder name="rightColumn"/>
			</div>
			<br class="clear"/>
		</div>
		<div id="foot">
			<@w.placeholder name="foot"/>
		</div>
	</div>
</body>
</html>