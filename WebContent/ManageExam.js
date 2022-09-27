jQuery(document).ready(function() {
	$("#stupidTable").stupidtable();

	$("#makeSeatid").click(function (event) {
		var examid = $(this).data("examid");
		console.log("examid="+examid)
		$("div[id='makeSeatid_dialog']").dialog({
			resizable: true,
			modal: true,
			buttons: {
				"確定": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=makeSeatid&examid=" + examid,
						async: false,
						timeout: 5000,
						success: function (result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function () {
					$(this).dialog("close");
				}
			}
		});
	});

	$("#setExamformprintable").click(function (event) {
		var examid = $(this).data("examid");
		$("div[id='setExamformprintable_dialog']").dialog({
			resizable: true,
			modal: true,
			buttons: {
				"開放": function () {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=setExamformprintable&examid=" + examid,
						async: false,
						timeout: 5000,
						success: function (result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function () {
					$(this).dialog("close");
				}
			}
		});
	});


	$("button[id='bankdata']").click(function() {
		var parent = $(this).parent();
		parent.find("div[id='bankdata-dialog']").dialog({
			width: '50%',
			resizable: true,
			modal: true,
			buttons: {
				"確定": function() {
					$(this).dialog("destroy");
				}
			}
		});
	});



	// $("td > #setPaid").button();

	$("button[id='setPaid']").click(function() {
		var bankaccount = $(this).data("bankaccount");
		console.log(bankaccount);
		$("div#ispaid-dialog-"+bankaccount).dialog({
			resizable: true,
			modal: true,
			buttons: {
				"確定": function() {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=setPaid&bankaccount=" + bankaccount,
						async: false,
						timeout: 5000,
						success: function(result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function() {
					$(this).dialog("destroy");
				}
			}
		});
	});

	$("button[id='unPaid']").button({
		icons: {
			primary: "ui-icon-closethick"
		},
		text: false
	});
	$("button[id='unPaid']").click(function() {
		var bankaccount = $(this).data("bankaccount");
		console.log(bankaccount);
		//var index = $(this).parentsUntil("tr").parent().index() + 1;
		//$("tr:eq(" + index + ") div#unpaid-dialog").dialog({
		$("div#unpaid-dialog-"+bankaccount).dialog({
			resizable: true,
			modal: true,
			buttons: {
				"取消繳費": function() {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=unPaid&bankaccount=" + bankaccount,
						async: false,
						timeout: 5000,
						success: function(result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function() {
					$(this).dialog("destroy");
				}
			}
		});
	});

	$("button[id='deleteApplication']").button({
		icons: {
			primary: "ui-icon-closethick"
		},
		text: false
	});
	$("button[id='deleteApplication']").click(function() {
		var applicationid = $(this).attr("applicationid");
		var index = $(this).parentsUntil("tr").parent().index() + 1;
		$("tr:eq(" + index + ") #deleteApplication-dialog").dialog({
			width: 400,
			resizable: true,
			modal: true,
			buttons: {
				"確定刪除報名表及報名者": function() {
					jQuery.ajax({
						type: "GET",
						url: "./Admin.api",
						data: "action=deleteApplication&applicationid=" + applicationid,
						async: false,
						timeout: 5000,
						success: function(result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function() {
					$(this).dialog("destroy");
				}
			}
		});
	});

	$("button[id='exportPaid']").click(function(event) {
		var examid = $(this).attr("examid");
		$("div[id='exportPaid_dialog']").dialog({
			resizable: true,
			modal: true,
			buttons: {
				"確定": function() {
					jQuery.ajax({
						type: "GET",
						url: "./ExportCSV.api",
						data: "target=Paid&examid=" + examid,
						async: false,
						timeout: 5000,
						success: function(result) {
							location.reload();
						}
					});
					$(this).dialog("close");
				},
				"取消": function() {
					$(this).dialog("close");
				}
			}
		});
	});


});
