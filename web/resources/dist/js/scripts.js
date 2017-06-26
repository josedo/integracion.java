/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    
    $(document).ajaxStart(function() { Pace.restart(); });
    $(document).ajaxComplete(function(){ $('table-ajax').dataTable(); });
    if ($('.checkbox').length) {
        $('.checkbox').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });
    }

    $('#login').on('click', function (e) {
        if (!sigecoApp.validForm())
            return;
        else {
            var data = sigecoApp.dataFormMantenedor();
            $.ajax({
                url: 'valida.htm',
                data: 'action=login' + data,
                type: 'POST',
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Logeado!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    window.location.replace('index.htm');
                                });
                    } else {
                        swal({
                            title: "Error al intentar logear!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al intentar logear!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
        e.preventDefault();
    });

    $('#registra').on('click', function (e) {
        if (!sigecoApp.validForm())
            return;
        else {
            var data = sigecoApp.dataFormMantenedor();
            $.ajax({
                url: 'registrarse.htm',
                data: 'action=register' + data,
                type: 'POST',
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Usuario registrado!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al registrar el usuario!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al registrar el usuario!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
        e.preventDefault();
    });

    if ($('#mantenedor').length) {
        $('#mantenedor').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    }

    if ($('select').length) {
        $('select').select2({
            placeholder: "Seleccione...",
            allowClear: true
        });
    }
    
    $('#region_id').select2().on('change', function() {
        if (this.value > 0) {
            $.ajax({
                url: './provincias',
                data: {region_id: this.value},
                type: 'POST',
                success: function (data) {
                    if (typeof(data.provinces) !== undefined) {
                        $('#province_id').html('').select2({
                            data: data.provinces
                        });
                    } else {
                        swal({
                            title: "Error al buscar provincias!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al buscar provincias!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
    });
    
    $('#province_id').select2().on('change', function() {
        if (this.value > 0) {
            $.ajax({
                url: './comunas',
                data: {province_id: this.value},
                type: 'POST',
                success: function (data) {
                    if (typeof(data.districts) !== undefined) {
                        $('#district_id').html('').select2({
                            data: data.districts
                        });
                    } else {
                        swal({
                            title: "Error al buscar comunas!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al buscar comunas!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
    });
    
    $('#newItem').on('click', function () {
        sigecoApp.clearFormMantanedor();
        $('#new').modal('show');
    });

    $('#addNew').on('click', function () {
        var data = sigecoApp.dataFormMantenedor();
        if (!sigecoApp.validForm())
            return;
        $('#new').modal('hide');
        var controller = $(this).attr('data-controller');
        var url = $(this).attr('data-url');
        swal({
            title: 'Envío de datos',
            text: 'Está seguro de la información ingresada?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: 'controller=' + controller + data,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Datos guardados!",
                            text: "",
                            type: "success"
                        },
                        function () {
                            location.reload();
                        });
                    } else {
                        swal({
                            title: "Error al guardar los datos!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al guardar los datos!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });

    $('#new').on('hidden.bs.modal', function () {
        sigecoApp.clearFormMantanedor();
    });

    $('.btnEditar').on('click', function () {
        sigecoApp.fillInputMantenedor($(this).attr('data-id'), $(this).attr('data-url'));
        $('#new').modal('show');
    });

    $('.btnEliminar').on('click', function () {
        var id = $(this).attr('data-id');
        var url = $(this).attr('data-url');
        swal({
            title: 'Eliminar Registro',
            text: 'Está seguro que desea eliminar este registro?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Registro eliminado!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al eliminar el registro!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al eliminar el registro!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });

    $('.btnRechazar').on('click', function () {
        var id = $(this).attr('data-id');
        var url = $(this).attr('data-url');
        swal({
            title: 'Rechazar Solicitud',
            text: 'Está seguro que desea rechazar esta solicitud?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Solicitud rechazada!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al rechazar la solicitud!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al rechazar la solicitud!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });

    $('.btnAprobar').on('click', function () {
        var id = $(this).attr('data-id');
        var url = $(this).attr('data-url');
        swal({
            title: 'Aprobar Solicitud',
            text: 'Está seguro que desea aprobar esta solicitud?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Solicitud aprobada!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al aprobar la solicitud!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al aprobar la solicitud!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });
    
    $('#report-selector').select2().on('change', function() {
        if (this.value > 0) {
            $.ajax({
                url: $(this).data('url'),
                data: {id: this.value},
                type: 'POST',
                success: function (data) {
                    if(data!=null){
                        $('#report-table').html(data);
                    } else {
                        swal({
                            title: "Error al bla!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al bla!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
    });

    var sigecoApp = {
        dataFormMantenedor: function () {
            var data = '';
            $('.form').find('.form-control').each(function () {
                if ($(this).parent('[class*="icheckbox"]').length>0)
                    data += '&' + $(this).attr('name') + '=' + ($(this).parent('[class*="icheckbox"]').hasClass("checked")?1:0);
                else
                    data += '&' + $(this).attr('name') + '=' + $(this).val();
            });
            return data;
        },
        clearFormMantanedor: function () {
            $('#addForm').find('.form-control').each(function () {
                if ($(this).is('select'))
                    $(this).val(null).trigger('change');
                else if ($(this).attr('type') === 'hidden')
                    $(this).val('0');
                else
                    $(this).val('');
            });
        },
        fillInputMantenedor: function (id, url) {
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "GET",
                success: function (data) {
                    $('#addForm').find('.form-control').each(function () {
                        if ($(this).is('select'))
                            $(this).select2('val', data.data[$(this).attr('name')]);
                        else
                            $(this).val(data.data[$(this).attr('name')]);
                    });
                }
            });
        },
        validForm: function () {
            var valid = true;
            $('.form').find('.form-control').each(function () {
                if ($(this).attr('required') === 'required' || $(this).attr('required') === 'true') {
                    if ($(this).val() === '' || $(this).val() === null) {
                        $(this).parents('.form-group').addClass('has-error');
                        valid = false;
                    } else
                        $(this).parents('.form-group').removeClass('has-error');
                }
            });
            return valid;
        }
    };
    
    function formatMoney ( number, places, symbol, thousand, decimal ) {
        places = !isNaN(places = Math.abs(places)) ? places : 0;
        symbol = symbol !== undefined ? symbol : '$';
        thousand = thousand || '.';
        decimal = decimal || ',';
        var number = number,
                negative = number < 0 ? '-' : '',
                i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + '',
                j = (j = i.length) > 3 ? j % 3 : 0;
        return symbol + ' ' + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, '$1' + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : '');
    };
    
    if (document.URL.indexOf('historialGastosComunes') > -1) {
        //******* BAR CHART
        var data = [[0, 11065161], [1, 15632865], [2, 15045372], [3, 14109583], [4, 13634087], [5, 17182678]];
        var dataset = [{label: ' Gastos Comunes', data: data, color: '#5482FF'}];
        var ticks = [[0, 'Enero'], [1, 'Febrero'], [2, 'Marzo'], [3, 'Abril'], [4, 'Mayo'], [5, 'Junio']];

        var options = {
            series: {
                bars: {
                    show: true,
                    barWidth: 0.5,
                    align: 'center'
                }
            },
            bars: {
                align: 'center',
                barWidth: 0.5
            },
            xaxis: {
                axisLabel: 'Meses',
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 10,
                tickLength: 0,
                ticks: ticks
            },
            yaxis: {
                axisLabel: 'Valor',
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 3,
                tickFormatter: function (v, axis) {
                    return formatMoney(v);
                }
            },
            legend: {
                noColumns: 0,
                labelBoxBorderColor: '#000000',
                position: 'nw'
            },
            grid: {
                hoverable: true,
                clickable: true,
                borderWidth: 1,
                borderColor: '#f3f3f3',
                tickColor: '#f3f3f3'
            }
        };

        $(document).ready(function () {
            $.plot($('#flot-placeholder'), dataset, options);
            $('#flot-placeholder').useTooltip();
            $("#flot-placeholder").bind("plotclick", function (event, pos, item) {
                if (item) {
                    var x = item.datapoint[0];
                    var y = item.datapoint[1];
                    alert(item.series.xaxis.ticks[x].label);
                }
            });
        });

        function gd(year, month, day) {
            return new Date(year, month, day).getTime();
        }

        var previousPoint = null, previousLabel = null;

        $.fn.useTooltip = function () {
            $(this).bind("plothover", function (event, pos, item) {
                if (item) {
                    $("#flot-placeholder").css('cursor','pointer');
                    if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) {
                        previousPoint = item.dataIndex;
                        previousLabel = item.series.label;
                        $("#tooltip").remove();

                        var x = item.datapoint[0];
                        var y = item.datapoint[1];

                        var color = item.series.color;

                        showTooltip(item.pageX,
                            item.pageY,
                            color,
                            item.series.xaxis.ticks[x].label + ' : <strong>' + formatMoney(y) + '</strong>');
                    }
                } else {
                    $('#tooltip').remove();
                    $("#flot-placeholder").css('cursor','auto');
                    previousPoint = null;
                }
            });
        };

        function showTooltip(x, y, color, contents) {
            $('<div class="tooltip-inner" id="tooltip">' + contents + '</div>').css({
                position: 'absolute',
                display: 'none',
                top: y - 40,
                left: x - 90,
                padding: '5px 8px',
                'font-size': '11px',
                'border-radius': '5px',
                opacity: 0.82
            }).appendTo('body').fadeIn(200);
        }
    }
    
    if (document.URL.indexOf('estacionamientos') > -1 || document.URL.indexOf('espaciosComunes') > -1) {
        $('#fechas').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'DD/MM/YYYY h:mm A'});
    }
    
    if (document.URL.indexOf('morosidad') > -1) {
        var donutData = [
            {label: "Debe", data: 30, color: "#3c8dbc"},
            {label: "Morosos", data: 20, color: "#dd4b39"},
            {label: "Pagos", data: 50, color: "#00c0ef"}
        ];
        $.plot("#donut-chart", donutData, {
            series: {
                pie: {
                    show: true,
                    radius: 1,
                    innerRadius: 0.5,
                    label: {
                        show: true,
                        radius: 2 / 3,
                        formatter: labelFormatter,
                        threshold: 0.1
                    }

                }
            },
            legend: {
                show: false
            }
        });

        /*
         * Custom Label formatter
         * ----------------------
         */
        function labelFormatter(label, series) {
            return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
                    + label
                    + "<br>"
                    + Math.round(series.percent) + "%</div>";
        }
    }
}(jQuery));
/*
var obj = {
  "users": {
    "admin": {
      "clave": "admin",
      "condominio": 1
    },
    "conserje": {
      "clave": "asdasd",
      "condominio": 2
    },
    "usuario1": {
      "clave": "usuario",
      "condominio": 2
    }
  },
  "gastosComunes": {
    "usuario1": 51928
  },
  "morosidad": {
    "usuario1": 0
  },
  "estacionamientos": {
    "condominio": {
      "2": {
        "1": 0,
        "2": 1,
        "3": 1
      }
    }
  },
  "espaciosComunes": {
    "condominio": {
      "quincho": 0,
      "salaMultiuso": 1
    }
  }
};
var data = JSON.stringify(obj);

$("#clickMe").click(function () {
       // do update
      $.ajax({
        url: "https://api.myjson.com/bins/2t2db",
        type: "PUT",
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
          var json = JSON.stringify(data);
          $("#data").val(json);
        }
      });
});
*/