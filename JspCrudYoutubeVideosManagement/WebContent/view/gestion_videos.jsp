<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <h2 align="center">CRUD Gesti&oacute;n de Videos Youtube</h2>
    </div>
    <div class="row" id="pos01">
        <div class="col-md-12">
            <div class="pull-right">
                <input type="button" class="btn btn-success" id="btnModalVideo" name="btnModalVideo" value="Nuevo Video" onclick="nuevoVideoModal();" >
            </div>
        </div>
    </div>
    <div class="row" id="pos02">
        <div class="col-md-12">
            <div class="pull-right">
                <form id="formSearch" name="formSearch" method="GET" action="">
                    <label for="buscarPorId">Id:</label>
                    <input type="text" id="buscarPorId" name="buscarPorId" value="" maxlength="9" onkeypress="ValidaSoloNumeros()">
                    <input type="button" class="btn btn-info" id="btnSearch" name="btnSearch" value="Buscar" onclick="buscarVideosPorId();" >
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div id="mostrarResultadoData"></div>
    </div>
    <div class="row" id="divPiePagina">
        <%@include file="includes/pie_pagina.jsp"%>
    </div>
    
    <!------------------Modal Formulario Registro Video-------------------->
    <div id="modalFormularioNuevoRegistro" class="modal modal-sh fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <%@include file="formulario_reg_video.jsp"%>
            </div>
        </div>
    </div>
    <!----------------Fín Modal Formulario Registro Video------------------>
    
    <!-------------------Modal Formulario Editar Video--------------------->
    <div id="modalFormularioEdicionVideo" class="modal modal-sh fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <%@include file="formulario_updt_video.jsp"%>
            </div>
        </div>
    </div>
    <!-----------------Fín Modal Formulario Editar Video------------------->
    
    <!-------------Modal Opción Confirm Alert Eliminación Video------------>
    <div id="deleteOptionModalConfirm" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                    <h4 class="modal-title">Eliminar</h4>
                </div>
                <form id="formModalConfirmDelVideoId" name="formModalConfirmDelVideoId" method="GET" action="">
                    <div class="modal-body">
                        <p>¿Est&aacute;s seguro de Borrar el registro?</p>
                        <input type="hidden" id="idVideoConfirmDel" name="idVideoConfirmDel" value="">
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" id="btnDelCanc" name="btnDelCanc" data-dismiss="modal" value="No" onclick="cancelarModalEliminar();" >
                        <input type="button" class="btn btn-danger" id="btnDelConf" name="btnDelConf" value="Si" onclick="eliminarVideo();" >
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-----------Fín Modal Opción Confirm Alert Eliminación Video---------->
    
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#mostrarResultadoData').html(mostrarVideos());
        
        $.ajax({
            url: 'VideoServletReadCategoriasAll',
            type: 'GET',
            success: function(datos) {
                $('#categoria').html(datos);
                $('#categoriaEdit').html(datos);
            },
            error: function() {
                console.error("No es posible completar la operación");
            }
        });
    });
    
    function mostrarVideos() {
        $.ajax({
            url: 'VideoServletReadAll',
            type: 'GET',
            success: function(datos) {
                $('#mostrarResultadoData').html(datos);
            }
        });
    }
    
    function nuevoVideoModal() {
        limpiarFormularioRegVideo();
        
        $('#modalFormularioNuevoRegistro').modal('show');
    }
    
    function registrarVideo() {
        var tituloVideo = document.formRegVideo.tituloVideo.value;
        var urlVideo = document.formRegVideo.urlVideo.value;
        var categoria = document.formRegVideo.categoria.value;
        var error = false;
        
        if(tituloVideo == "") {
            error = true;
            toastr.error("El campo Título es obligatorio", "Error en el formulario");
            document.formRegVideo.tituloVideo.focus();
        }
        
        if(urlVideo == "") {
            error = true;
            toastr.error("El campo Video es obligatorio", "Error en el formulario");
            document.formRegVideo.urlVideo.focus();
        }
        
        if(categoria == "") {
            error = true;
            toastr.error("Debe seleccionar una Categoria", "Error en el formulario");
            document.formRegVideo.categoria.focus();
        }
        
        if(!error) {
        	loadingOpen();
        	
            $.ajax({
                url: 'VideoServletInsert',
                type: 'POST',
                dataType: 'json',
                data: 'urlVideo=' + urlVideo + '&tituloVideo=' + tituloVideo + '&categoria=' + categoria,
                success: function(respuesta) {
					if(respuesta.hasError) {
						loadingClose();
						toastr.error(respuesta.descripcion, "Operación erronea");
					} else {
						toastr.success(respuesta.descripcion, "Operación exitosa");
						$('#modalFormularioNuevoRegistro').modal('hide');
						limpiarFormularioRegVideo();
						
						$('#mostrarResultadoData').html(mostrarVideos());
						loadingClose();
					}
				}
			});
        }
    }
    
    function cancelarModalRegVideo() {
        $('#modalFormularioNuevoRegistro').modal('hide');
    }
    
    function limpiarFormularioRegVideo() {
        document.formRegVideo.tituloVideo.value = "";
        document.formRegVideo.urlVideo.value = "";
        document.formRegVideo.categoria.value = "";
    }
    
    function modificarVideo() {
        var tituloVideo = document.formUpdtVideo.tituloVideoEdit.value;
        var urlVideo = document.formUpdtVideo.urlVideoEdit.value;
        var categoria = document.formUpdtVideo.categoriaEdit.value;
        var error = false;
        
        if(tituloVideo == "") {
            error = true;
            toastr.error("El campo Título es obligatorio", "Error en el formulario");
            document.formUpdtVideo.tituloVideoEdit.focus();
        }
        
        if(categoria == "") {
            error = true;
            toastr.error("Debe seleccionar una Categoria", "Error en el formulario");
            document.formUpdtVideo.categoriaEdit.focus();
        }
        
        if(urlVideo != "") {
            var datos1 = 'tituloVideoEdit=' + tituloVideo + '&urlVideoEdit=' + urlVideo + '&categoriaEdit=' + categoria + '&idVideoEdit=' + document.formUpdtVideo.idVideoEdit.value;
        } else {
            var datos1 = 'tituloVideoEdit=' + tituloVideo + '&categoriaEdit=' + categoria + '&idVideoEdit=' + document.formUpdtVideo.idVideoEdit.value;
        }
        
        if(!error) {
        	loadingOpen();
        	
            $.ajax({
                url: 'VideoServletUpdate',
                type: 'POST',
                dataType: 'json',
                data: datos1,
                success: function(respuesta) {
					if(respuesta.hasError) {
						loadingClose();
						toastr.error(respuesta.descripcion, "Operación erronea");
					} else {
						toastr.success(respuesta.descripcion, "Operación exitosa");
						$('#modalFormularioEdicionVideo').modal('hide');
						limpiarFormularioModifVideo();
						
						$('#mostrarResultadoData').html(mostrarVideos());
						loadingClose();
					}
                }
            });
        }
    }
    
    function cancelarModalUpdtVideo() {
        $('#modalFormularioEdicionVideo').modal('hide');
        
        limpiarFormularioModifVideo();
    }
    
    function limpiarFormularioModifVideo() {
        document.formUpdtVideo.tituloVideoEdit.value = "";
        document.formUpdtVideo.urlVideoEdit.value = "";
        document.formUpdtVideo.categoriaEdit.value = "";
        document.formUpdtVideo.idVideoEdit.value = "";
        
        $('#mostrarVideoSubido').val("");
    }
    
    function editarVideo(idVideo) {
        $.ajax({
            url: 'VideoServletEditId',
            type: 'GET',
            dataType: 'json',
            data: 'accion=edit&idVideoEdit=' + idVideo,
            success: function(respuesta) {
                $('#idVideoEdit').val(respuesta.idVideo1);
                $('#tituloVideoEdit').val(respuesta.videoTitulo);
                
                document.getElementById("mostrarVideoSubido").innerHTML = respuesta.urlVideo;
                $('#categoriaEdit').val(respuesta.categoria);
                
                $('#modalFormularioEdicionVideo').modal('show');
            }
        });
    }
    
    function mostrarModalConfirmEliminarVideo(idVideo) {
        $.ajax({
            url: 'VideoServletReadPreviousConfirmDelId',
            type: 'GET',
            dataType: 'json',
            data: 'accion=readVideoIdPreviusConfirmDel&idVideoConfirmDel=' + idVideo,
            success: function(respuesta) {
                $('#idVideoConfirmDel').val(respuesta.idVideo1);
                
                $('#deleteOptionModalConfirm').modal('show');
            }
        });
    }
    
    function eliminarVideo() {
    	loadingOpen();
    	
        $.ajax({
            url: 'VideoServletDelete',
            type: 'GET',
            dataType: 'json',
            data: 'idVideoConfirmDel=' + document.formModalConfirmDelVideoId.idVideoConfirmDel.value,
            success: function(respuesta) {
				if(respuesta.hasError) {
					loadingClose();
					toastr.error(respuesta.descripcion, "Operación erronea");
				} else {
					toastr.success(respuesta.descripcion, "Operación exitosa");
					$('#deleteOptionModalConfirm').modal('hide');
					document.formModalConfirmDelVideoId.idVideoConfirmDel.value = "";
					
					$('#mostrarResultadoData').html(mostrarVideos());
					loadingClose();
				}
            }
        });
    }
    
    function cancelarModalEliminar() {
        document.formModalConfirmDelVideoId.idVideoConfirmDel.value = "";
        
        $('#deleteOptionModalConfirm').modal('hide');
    }
    
    function buscarVideosPorId() {
        var buscarPorId = $('#buscarPorId').val();
        
        if(buscarPorId == "") {
            toastr.error("El campo ID es obligatorio", "Error en el formulario");
            document.formSearch.buscarPorId.focus();
            return false;
        }
        
        $.ajax({
            url: 'VideoServletReadById',
            type: 'GET',
            data: 'buscarPorId=' + buscarPorId,
            success: function(datos) {
                $('#mostrarResultadoData').html(datos);
                document.formSearch.buscarPorId.value = "";
            }
        });
    }
    
    function loadingOpen() {
        document.getElementById("load1").style.display = "block";
        document.getElementById("load2").style.display = "block";
    }
    
    function loadingClose() {
    	document.getElementById("load1").style.display = "none";
        document.getElementById("load2").style.display = "none";
    }
</script>
<!-------------------------------Load para la carga de datos------------------------------------->
<div id="load1" class="modal fade loading-icon-page in" style="z-index: 1060; display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" style="min-width: 150px;">
                <div class="circle"></div>
                <div class="text">
                    <span class="load">Cargando </span>...
                </div>
            </div>
        </div>
    </div>
</div>

<div id="load2" class="modal-backdrop fade in" style="z-index: 1051; display: none;"></div>
<!-----------------------------Fin Load para la carga de datos----------------------------------->