<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal-header">
    <button type="button" class="close sclick" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h3 align="center">Formulario Registro Video</h3>
</div>
<form id="formRegVideo" name="formRegVideo" method="POST" action="">
    <div class="modal-body">
        <div class="form-group">
            <label for="tituloVideo">T&iacute;tulo</label>
            <input type="text" class="form-control" id="tituloVideo" name="tituloVideo" maxlength="199" value="" onkeypress="return validarSoloNumerosLetrasGuionComillasComaPuntoyEspacioEnBlanco(event)">
        </div>
        <div class="form-group">
            <label for="urlVideo">Video</label>
            <input type="text" class="form-control" id="urlVideo" name="urlVideo" maxlength="999" value="" onkeypress="return validarUrlIdVideoYoutube(event)">
        </div>
        <div class="form-group">
            <label for="categoria">Categoria</label>
            <select id="categoria" name="categoria" class="form-control">
            </select>
        </div>
    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-warning" id="btnCanc" name="btnCanc" value="Cancelar" onclick="cancelarModalRegVideo();" />
        <input type="button" class="btn primary-btn" id="btnReg" name="btnReg" value="Ingresar" onclick="registrarVideo();" />
    </div>
</form>