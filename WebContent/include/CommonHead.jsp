<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" title="Normal" type="text/css" media="screen"
	href="./screen.css" />

<script type="text/javascript"
	src="./jscripts/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="./jscripts/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="./jscripts/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.min.css" />

<link href="styles/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script src="./jscripts/jquery-ui-timepicker-addon.js"></script>

<script type="text/javascript"
	src="./jscripts/jquery.timeout.interval.idle.js"></script>

<style>
/* @IMPORT url("Fonts/HanaMinB.ttf");
 */
.ui-menu {
	overflow: hidden;
	/* margin-bottom: 1em;  下面留一點空間。 */
	padding-right: 5em; /* 第一層左右都內縮，看起來不比較好看 */
	padding-left: 5em;
	z-index: 100; /* 提升 menu 的圖層，否則會被 button 擋到。 */
	background: transparent;
	border-width: 0px;
}

.ui-menu .ui-menu {
	padding: 1px 1px 1px 1px; /* 第二層以下的 menu 就不內縮了。 */
	overflow: visible !important;
}

.ui-menu>li {
	float: right;
	display: block;
	width: auto !important;
}

/* 設定 menu 第二層 的樣式。*/
.ui-menu ul {
	background: white;
	border-width: 1px;
}

.ui-menu ul li {
	display: block;
	float: none;
}

.ui-menu ul li ul {
	left: 120px !important;
	width: 100%;
}

.ui-menu ul li ul li {
	margin: 1px 1px !important;
	padding: 0 0 !important;
	width: auto;
}

.ui-menu ul li ul li a {
	float: left;
}

.ui-menu>li {
	margin: 5px 5px !important;
	padding: 0 0 !important;
}

.ui-menu>li>a {
	float: left;
	display: block;
	clear: both;
	overflow: hidden;
}

.ui-menu .ui-menu-icon {
	margin-top: 0.2em !important;
}

.ui-menu .ui-menu .ui-menu li {
	float: left;
	display: block;
}

#tabs {
	border-width: 0px;
	background: transparent;
}

#tabs ul#tabs-ul {
	height: 2.35em;
	text-align: center;
	background: transparent;
	border-width: 0px 0px 1px 0px;
}

#tabs ul#tabs-ul li {
	display: inline-block;
	float: none;
	top: 0px;
	margin: 0em;
}
</style>
<meta charset="UTF-8">
<title>${applicationScope.appConfig.title }</title>

<script type="text/javascript">
	// initialise plugins	
	/*
	 jQuery().ready(function(){
	 jQuery(function(){
	 jQuery('ul.sf-menu').superfish();
	 });	
	 });		
	 */
	/* For superfish MENU ， IE 6 會當掉。 */
	jQuery(document).ready(function() {
		$("input:first").focus();
		$("input[type=submit], [type='button']").button();
		$("button").button().click(function(event) {
			event.preventDefault(); // 讓預設的動作失效！
		});
		$(".confirm").addClass("FakeLink");
		$(".closethick").button({
			icons : {
				primary : "ui-icon-closethick"
			},
			text : false
		});

		$("#menu").menu({
			position : {
				at : "left bottom"
			}
		});
		$("#menu").show();
		jQuery("#tabs").tabs({
			collapsible : false
		});

		/* 		jQuery("ul.sf-menu").supersubs({
		 minWidth : 2, // minimum width of sub-menus in em units 
		 maxWidth : 27, // maximum width of sub-menus in em units 
		 extraWidth : 0
		 // extra width can ensure lines don't sometimes turn over 
		 // due to slight rounding differences and font-family 
		 }).superfish(); // call supersubs first, then superfish, so that subs are 
		 // not display:none when measuring. Call before initialising 
		 // containing tabs for same reason. 
		 */});
</script>

<fmt:setLocale value="${sessionScope.session_locale}" />
<fmt:setBundle basename="resource" />


<!--[if lt IE 7]><script type="text/javascript" src="./jscripts/IE_pngfix.js"></script><![endif]-->
<!--
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-13232278-2");
pageTracker._trackPageview();
} catch(err) {}</script>
-->
