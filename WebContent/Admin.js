jQuery(document).ready(function () {

	$("button[id='deleteExam']").click(function () {
		var examid = $(this).data("examid");
		//var index = $(this).parentsUntil("tr").parent().index() + 1;
		var tr = $(this).closest("tr");
		var deleteExam_dialog = tr.find("#deleteExam_dialog")
		deleteExam_dialog.dialog({
			width: 400,
			resizable: true,
			modal: true,
			buttons: {
				"確定刪除本次甄選?": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=deleteExam&examid=" + examid,
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
				}
			}
		});
	});


	$("button[id='enactiveExam']").click(function () {
		var examid = $(this).attr("examid");
		var index = $("button[id='enactiveExam']").index(this);
		$("div[id='enactiveExam_dialog']").eq(index).dialog({
			resizable: true,
			modal: true,
			buttons: {
				"確定": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=setActiveExam&examid=" + examid,
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
				}
			}
		});
	});

	$("button[id='disactiveExam']").click(function () {
		var examid = $(this).attr("examid");
		var index = $("button[id='disactiveExam']").index(this);
		$("div[id='disactiveExam_dialog']").eq(index).dialog({
			resizable: true,
			modal: true,
			buttons: {
				"確定": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=disactiveExam&examid=" + examid,
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
				}
			}
		});
	});

	$("button[id='setAllBankaccount']").click(function () {
		jQuery.ajax({
			type: "GET",
			url: "./Admin.api",
			data: "action=setAllBankaccount",
			async: false,
			timeout: 5000,
			success: function (result) {
				location.reload();
			}
		});

	});

});