<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Opinión</title>
    <link rel="stylesheet" href="style/estilo.css"/>
    <link rel="icon" type="image/x-icon" href="imgs/favicon.ico"/>
</head>
<body>
    <div class="container">
        <h1>Opiniones</h1>
        <ol>

            <#-- Bucle anidado de respuestas a opiniones -->
            <#list opiniones as opinion>
                <#if opinion.numRespuestas == 0>
                    <li>${opinion.texto} <b>(${opinion.autor})</b></li>
                <#else>
                    <li>${opinion.texto} <b>(${opinion.autor})</b>
                        <ul>
                            <#list opinion.respuestas as respuesta>
                                <li>${respuesta.texto} <b>(${respuesta.autor})</b></li>
                            </#list>
                        </ul>
                    </li>
                </#if>
            </#list>

        </ol>
        <form action="opinar.html" method="post">
            <#-- Identificación como parámetro oculto -->
            <input type="hidden" name="nombre" value="${(nombre)!}" />
            <input type="hidden" name="codigo" value="${(codigo)!}" />

            <input id="respuesta" type="checkbox"
            name="esrespuesta" value="true"
            onclick="habilitarRespuesta();" />
            <label for="respuesta">es respuesta a</label>
            <select id="numero" name="numero" disabled="disabled">
                <option value="0">ninguno</option>

                <#-- Para responder a una opinión envíando el número real de la opinión a la que se responde -->
                <#assign i=1 />
                <#list opiniones as opinion>
                    <option value="${opinion.numero}">comentario ${i}</option>
                    <#assign i++ />
                </#list>

            </select>
            <br/><br/>
            <textarea id="opinion" name="opinion"></textarea><br/>
            <div class="button-container opinar">
                <input type="submit" value="Opinar"/>
            </div>
        </form>
        <br/>
        <form action="votar.html" method="post">
            <#-- Identificación como parámetro oculto -->
            <input type="hidden" name="nombre" value="${(nombre)!}" />
            <input type="hidden" name="codigo" value="${(codigo)!}" />
            <div class="button-container volver">
                <input type="submit" value="Volver"/>
            </div>
        </form>
        <br/>
        <div class="button-container salir">
            <a href="index.html">Salir</a>
        </div>

        <script src="code/script.js"></script>
    </div>
</body>
</html>