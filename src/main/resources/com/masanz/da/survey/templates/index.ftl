<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Encuesta</title>
    <link rel="stylesheet" href="style/estilo.css"/>
    <link rel="icon" type="image/x-icon" href="imgs/favicon.ico"/>
</head>
<body>
    <div class="container">
        <h1>Encuesta</h1>
        <form class="login" action="votar.html" method="post">
            <em>Datos facilitados para la encuesta</em><br/><br/>
            <label for="name">Nombre:</label>

            <#-- Se puede inyectar el nombre -->
            <input id="name" type="text" name="nombre" value="${(nombre)!}"/><br/><br/>

            <label for="code">Código*:</label>

            <#-- Se puede inyectar el código -->
            <input  id="code" type="password" value="${(codigo)!}"
                    name="codigo" required="required" minlength="6" maxlength="6"/><br/><br/>

            <em>* tiene 6 caracteres alfanuméricos.</em><br/><br/>
            <div class="podium-container">
                <img src="imgs/podium.png"/>
            </div>
            <div class="button-container">
                <input type="submit" value="Entrar"/>
            </div>
        </form>
    </div>
</body>
</html>