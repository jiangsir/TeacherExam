jQuery(document).ready(function() {
	$("button[id='button']").click(function() {
		$("#form").submit();
	});

	// $("#birthday_datepicker").datepicker("option", "dateFormat",
	// "yyyy-mm-dd");
	$("#birthday_datepicker").datepicker({
		dateFormat : 'yy-mm-dd',
		changeYear : true
	});

	$("input[type='submit']").click(function(event) { // 事件發生
		event.preventDefault();
		jQuery.ajax({
			type : "POST",
			url : "CheckApplication.json",
			data: $('#form').serialize()+"&picture="+$('input[type="file"]').val(),
//			data : $('#form').serialize(),
			dataType : "json",
			async : false,
			timeout : 5000,
			error : function(xhr, ajaxOptions, thrownError) {
				alert("text="+thrownError);
				//alert(JSON.parse(xhr.responseText));
				//console.log(xhr.responseText);
			},

			success : function(result) {
				if (result.length == 0) {
					$("#form").submit();
				} else {
					$("div[id='check_dialog']").dialog({
						position : [ 'top', 50 ],
						width : 350,
						resizable : true,
						modal : true,
						buttons : {
							"確定" : function() {
								$(this).dialog("close");
							}
						}
					});
					var dialog = $("#check_dialog");
					dialog.text("");
					$.each(result, function(index) {
						// alert(result[index]);
						dialog.append(result[index] + "<br>");
					});
				}
			}
		});
	});

	jQuery("input[name='gender']").each(function() {
		if ($(this).parent().attr("gender") == $(this).val()) {
			$(this).attr("checked", true);
		}
	});
	jQuery("input[name='position']").each(function() {
		if ($(this).parent().attr("position") == $(this).val()) {
			$(this).attr("checked", true);
		}
	});
	jQuery("select[name='subjectid'] > option").each(function() {
		if ($(this).parent().attr("subject") == $(this).val()) {
			$(this).attr("selected", true);
		}
	});

});