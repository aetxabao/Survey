l<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Votar</title>
    <link rel="stylesheet" href="style/estilo.css"/>
    <link rel="icon" type="image/x-icon" href="imgs/favicon.ico"/>
</head>
<body>
    <div class="container">
        <h1>Mejor equipo</h1>

        <#-- Obtener cuántos votos hay (cnt) y la opción que tiene más votos (max) -->
        <#assign cnt=0 />
        <#assign max=0 />
        <#list votos as clave, valor >
            <#assign cnt++ />
            <#if valor gt max >
                <#assign max=valor />
            </#if>
        </#list>

        <#-- Paleta de colores para cada valor -->
        <#assign palette = ["#7eb0d5", "#b2e061", "#bd7ebe", "#ffb55a", "#ffee65", "#beb9db", "#fdcce5", "#8bd3c7"]>

        <div class="svg-container">
        <#-- Gráfico SVG en un lienzo de 480x250 en un área gráfica de 420x200 -->
        <svg xmlns="http://www.w3.org/2000/svg" width="420" height="250">
            <rect width="360" height="200" x="40" y="20" style="fill:white;stroke-width:1;stroke:black;"/>
            <#assign i=-1/>
            <#list votos as clave, valor>
                <#assign i=i+1 />
                <#assign w=360/cnt />
                <#assign h=200/max*valor />
                <#assign x=40+i*w />
                <#assign y=20+200-h />
                <#assign c=palette[i] />
                <rect width="${w}" height="${h}" x="${x}" y="${y}" style="stroke-width:1;fill:${c};stroke:black;"/>
                <#assign xc=20+x />
                <text x="${xc}" y="240" fill="${c}">${clave}</text>
                <#assign yv=y+5 />
                <text x="15" y="${yv}" fill="${c}">${valor}</text>
            </#list>
        </svg>
        </div>

        <#-- Mostrar un mensaje con el voto que habías hecho si es el caso -->
        <#if voto??>
            <p><b>${voto.usuario}</b> el ${voto.fecha} habías votado <i>${voto.opcion}</i> a las ${voto.hora}</p>
            <h2>Si quieres puedes cambiar tu voto</h2>
        <#else>
            <h2>No has votado todavía, elige</h2>
        </#if>

        <form action="votar.html" method="post">

            <#-- Identificación como parámetro oculto -->
            <input type="hidden" name="nombre" value="${(nombre)!}" />
            <input type="hidden" name="codigo" value="${(codigo)!}" />

            <div class="radios-container">
            <#-- Opciones que se pueden votar -->
            <#list recuentos as recuento>
                <#-- Mostrar el voto que habías hecho si es el caso -->
                <#if voto?? && voto.opcion?lower_case == recuento.valor?lower_case>
                    <input id="${recuento.valor}" type="radio" name="voto" value="${recuento.valor}" checked="checked"/>
                <#else>
                    <input id="${recuento.valor}" type="radio" name="voto" value="${recuento.valor}"/>
                </#if>
                <label for="${recuento.valor}">${recuento.nombre} (${recuento.cuantos})</label>
            </#list>
            </div>

            <br/><br/>
            <div class="button-container votar">
                <input type="submit" value="Votar"/>
            </div>
        </form>
        <br/>
        <form action="opinar.html" method="post">
            <#-- Identificación como parámetro oculto -->
            <input type="hidden" name="nombre" value="${(nombre)!}" />
            <input type="hidden" name="codigo" value="${(codigo)!}" />
            <div class="button-container opinar">
                <input type="submit" value="Opinar"/>
            </div>
        </form>
        <div class="button-container salir">
            <a href="index.html">Salir</a>
        </div>
    </div>
</body>
</html>