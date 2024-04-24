function habilitarRespuesta() {
    var chkResp = document.getElementById("respuesta");
    var selComent = document.getElementById("numero");
    selComent.disabled = !chkResp.checked;
}