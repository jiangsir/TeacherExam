jQuery(document).ready(function () {


	$("button[id='removeUpfile']").button({
		icons: {
			primary: "ui-icon-closethick"
		},
		text: false
	});
	$("span[id='deleteUpfile']").button({
		icons: {
			primary: "ui-icon-closethick"
		},
		text: false
	});


	$("input[name='applybegin']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[name='applyend']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[id='startline']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[id='deadline']").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("input[id='firsttest']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[id='secondtest']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[id='scoreboardbegin']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});
	$("input[id='step2scoreboardbegin']").datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm:ss'
	});




	$("span[id='deleteUpfile']").click(function () {
		var upfileid = $(this).attr("upfileid");
		//var index = $(this).parent().index();
		var index = $("span[id='deleteUpfile']").index(this);
		//alert("$(this).parent()="+$(this).parent().attr('id')+", upfileid="+upfileid+", index="+index);
		$("ul#upfiles li:eq(" + index + ") div[id='deleteUpfile_dialog']").dialog({
			width: 350,
			resizable: true,
			modal: true,
			buttons: {
				"確定刪除": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=deleteUpfile&upfileid=" + upfileid,
						async: false,
						timeout: 5000,
						success: function (result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function () {
					$(this).dialog("destroy");
					// 由於 dialog 關閉，會一並把 XXXX_dialog 也關閉，強迫 reload 才會正常。
					// 不能用 dialog("close"); 改用 "destroy" 就可已重複再出現。
				}
			},
			close: function (ev, ui) {
				$(this).dialog("destroy");
				// 由於 dialog 關閉，會一並把 XXXX_dialog 也關閉，強迫 reload 才會正常。
				// 不能用 dialog("close"); 改用 "destroy" 就可已重複再出現。
			}
		});
	});


	$("#addUpfile").click(function () {
		var lastNewUpfile = $("div[id='newUpfile']").last();
		$(lastNewUpfile).clone().insertAfter(lastNewUpfile).find("#removeUpfile").on('click', function (event) {
			if ($("div[id='newUpfile']").size() > 1) {
				$(this).parent().remove();
			}
			event.preventDefault(); // 讓預設的動作失效！
		});
		//lastNewUpfile = $("div[id='newUpfile']").last();
		//$("button[id='removeUpfile']").hover();
	});
	$("button[id='removeUpfile']").click(function () {
		if ($("div[id='newUpfile']").size() > 1) {
			$(this).parent().remove();
		}
	});

	jQuery("input[name='isapplicationalwayseditable']").each(function () {
		if ($(this).parent().attr("isapplicationalwayseditable") == $(this).val()) {
			$(this).attr("checked", true);
		}
	});
	jQuery("select[name='nianduan'] > option").each(function () {
		if ($(this).parent().attr("nianduan") == $(this).val()) {
			$(this).attr("selected", true);
		}
	});
	jQuery("select[name='zhengshidaili'] > option").each(function () {
		if ($(this).parent().attr("zhengshidaili") == $(this).val()) {
			$(this).attr("selected", true);
		}
	});


	$("span[id='removeSubjectSeat']").click(function () {
		if ($("div[id='SubjectSeat']").size() > 1) {
			var subjectid = $(this).parentsUntil("#SubjectSeat").parent().find("input[name='subjectid']").val();
			alert(subjectid);
			jQuery.ajax({
				type: "GET",
				url: "./Subject.api",
				data: "action=deleteSubject&subjectid=" + subjectid,
				async: false,
				timeout: 5000,
				success: function (result) {
				}
			});
			$(this).parentsUntil("#SubjectSeat").parent().remove();
		}
	});

	$("#icons li").hover(function () {
		$(this).addClass("ui-state-hover");
	}, function () {
		$(this).removeClass("ui-state-hover");
	});

});