<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal-header">
    <button type="button" class="close sclick" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h3 align="center">Formulario Modificaci&oacute;n Video</h3>
</div>
<form id="formUpdtVideo" name="formUpdtVideo" method="POST" action="">
    <div class="modal-body">
        <div class="form-group">
            <label for="tituloVideoEdit">T&iacute;tulo</label>
            <input type="text" class="form-control" id="tituloVideoEdit" name="tituloVideoEdit" maxlength="199" value="" onkeypress="return validarSoloNumerosLetrasGuionComillasComaPuntoyEspacioEnBlanco(event)">
            <input type="hidden" id="idVideoEdit" name="idVideoEdit" value="">
        </div>
        <div class="form-group">
            <label for="urlVideoEdit">Video</label>
            <input type="text" class="form-control" id="urlVideoEdit" name="urlVideoEdit" maxlength="999" value="" onkeypress="return validarUrlIdVideoYoutube(event)">
            <div id="mostrarVideoSubido"></div>
        </div>
        <div class="form-group">
            <label for="categoriaEdit">Categoria</label>
            <select id="categoriaEdit" name="categoriaEdit" class="form-control">
            </select>
        </div>
    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-warning" id="btnCanc" name="btnCanc" value="Cancelar" onclick="cancelarModalUpdtVideo();" />
        <input type="button" class="btn primary-btn" id="btnUpdt" name="btnUpdt" value="Modificar" onclick="modificarVideo();" />
    </div>
</form>