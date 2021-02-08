$(document).ready(function () {
  $('#dataTable_paginate').DataTable();
  $('.dataTables_length').addClass('bs-select');
});


//Enlaces entoda la fila de la tabla
jQuery(document).ready(function($) {
	$(".clickable-row").click(function() {
		alert("clicable");
		var $th = $(this);
		// window.open($th.attr('data-href'), $th.attr('data-target'));
		goTo($th.attr('data-href'));
	});
});

/** Abre el cuadro de ayuda **/
function abrirAyuda(){
	$("#soporte").addClass("on");
    $("#cajaSoporte").addClass("on");
}

/** Cierra el cuadro de ayuda **/
function cerrarAyuda(){
	$("#soporte").removeClass("on");
    $("#cajaSoporte").removeClass("on");
}

/** Salir de la Carpeta **/
function sortirCarpeta(){
	document.getElementById('logout').submit();
}

/** Acceder a una url **/
function goTo(url) {
	document.location.href=url;
}

/** Acceder a una url **/
function goToWindow(url) {
	window.open(url);
}

/**  DATEPICKER **/
$(function() {
	$('#fechaInicio').datetimepicker({
		locale: 'ca',
		showClose: false,
		format: 'D/MM/YYYY',
		icons: {
			time: 'oi oi-clock',
			date: 'oi oi-calendar',
			previous: 'oi oi-chevron-left',
			next: 'oi oi-chevron-right',
			close: 'oi oi-circle-x'
		},
		tooltips: {
			selectMonth: 'Selecciona mes',
			prevMonth: 'Mes anterior',
			nextMonth: 'Mes següent'
		}
	});
});

$(function() {
	$('#fechaFin').datetimepicker({
		locale: 'ca',
		showClose: false,
		format: 'D/MM/YYYY',
		icons: {
			time: 'oi oi-clock',
			date: 'oi oi-calendar',
			previous: 'oi oi-chevron-left',
			next: 'oi oi-chevron-right',
			close: 'oi oi-circle-x'
		},
		tooltips: {
			selectMonth: 'Selecciona mes',
			prevMonth: 'Mes anterior',
			nextMonth: 'Mes següent'
		}
	});
});