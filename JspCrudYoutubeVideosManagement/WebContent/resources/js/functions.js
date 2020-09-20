/**
 * 
 */
$(document).ready(function() {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-bottom-left",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
});

function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}

/*
 * Link para la busqueda de expresiones regulares como validadores de Letras, N�meros y Caracteres: 
 * https://informaticapc.com/tutorial-javascript/cadenas-expresiones-regulares.php
 */
//Este validador es solo para el input "Titulo" que se encuentra en los formularios de modificaci�n e ingreso//
function validarSoloNumerosLetrasGuionComillasComaPuntoyEspacioEnBlanco(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8) return true;
    patron = /^[0-9a-zA-ZáéíóúÁÉÍÓÚäëïöüÄËÏÖÜàèìòùÀÈÌÒÙñÑ\s\-\+\"\'\:\.\(\)\,]+$/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

/*
 * Ejemplo Validador datos de entrada mediante FrontEnd: 
 * https://coudlain.wordpress.com/2011/07/11/javascript-controlar-entrada-de-datos-en-caja-de-texto-este-si-que-funciona-en-chrome-firefox-y-explorer/
 */

//Este validador es solo para el input "Video" que se encuentra en los formularios de modificaci�n e ingreso//
function validarUrlIdVideoYoutube(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8) return true;
    patron = /^[0-9a-zA-Z\_\-\=\&\.]+$/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}
/*
 * Pasos claves para saber como se llev� a cabo la realizaci�n de validador de la ID vinculada a la url de un video youtube:
 * https://www.iteramos.com/pregunta/19611/como-encontrar-todos-los-identificadores-de-video-de-youtube-de-una-cadena-mediante-una-expresion-regular
 */