<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>

<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />

<title>${SystemConfig.TITLE}</title>
<link rel="stylesheet" title="Normal" type="text/css" media="screen"
	href="./screen.css" />
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript" src="jscripts/js_date.js">
	
</script>
<script type="text/javascript"
	src="jscripts/jquery.timeout.interval.idle.js">
	
</script>

<link href="styles/smoothness/jquery-ui-1.10.3.custom.css"
	rel="stylesheet" />
<script src="jscripts/jquery-1.9.1.js"></script>
<script src="jscripts/jquery-ui-1.10.3.custom.js"></script>
<script language="javascript">
	jQuery(document).ready(
			function() {
				$("input:first").focus();
				$("input[type=submit], [type='button']").button();
				$("button").button().click(function(event) {
					event.preventDefault(); // 讓預設的動作失效！
				});

				$("ul#uimenu").menu({
					minWidth : 120,
					arrowSrc : 'arrow_right.gif',
					onClick : function(e, menuItem) {
						alert('you clicked item "' + $(this).text() + '"');
					}
				});

				//mytime(parseInt(${now.time}) );
				//$("#account_menu").menu();
				var servletPath = jQuery("span#servletPath").attr("page");
				jQuery("div#menu > ul > li").each(
						function() {
							if ($(this).attr("uri") == servletPath
									|| $(this).attr("uri2") == servletPath) {
								$(this).addClass("selected");
							}
						});

			});

	function mytime(nowtime) {
		var nowdate = new Date();
		nowdate.setTime(nowtime);
		jQuery("#now").text(formatDate(nowdate, "y-MM-dd HH:mm:ss"));
		jQuery.interval(function() {
			var nowdate = new Date();
			nowtime = nowtime + 1000;
			//  alert("nowtime="+nowtime);
			nowdate.setTime(nowtime);
			jQuery("#now").text(formatDate(nowdate, "y-MM-dd HH:mm:ss"));
		}, 1000);
	}
</script>

