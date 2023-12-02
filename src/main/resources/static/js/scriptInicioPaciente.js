const contPerfil = document.getElementById("contenedor-perfil");
const icono = document.getElementById("icono");
const desplegable = document.getElementById("desplegable");

let estadoFlecha = false;

contPerfil.addEventListener("click", function () {

    if (!estadoFlecha) {
        contPerfil.classList.add("link-click");
        icono.classList.add("rotar");
        desplegable.style.maxHeight = "20%";
        desplegable.style.visibility = "visible";
        desplegable.style.opacity = "1";
        estadoFlecha = true;
    } else {
        contPerfil.classList.remove("link-click");
        icono.classList.remove("rotar");
        desplegable.style.maxHeight = "0";
        desplegable.style.visibility = "hidden";
        desplegable.style.opacity = "0";
        estadoFlecha = false;
    }

});

function obtenerSeleccion(value) {
    let especialidadElegida = value.toString();
    console.log(especialidadElegida);

    var contenedorListas = document.getElementById('contenedor-listas');
    var listasUL = contenedorListas.getElementsByTagName('ul');
    var arrayListas = Array.from(listasUL);

    var listaDeTurnos = arrayListas.map(function (ulElemento) {
        var elementosLi = ulElemento.getElementsByTagName('li');
        var arrayLi = Array.from(elementosLi);

        return {
            id: arrayLi[0].textContent,
            especialidad: arrayLi[1].textContent,
            fechaTurno: arrayLi[2].textContent,
            profesionalACargo: arrayLi[3].textContent,
            cancelado: arrayLi[4].textContent,
            atendido: arrayLi[5].textContent
        };
    });

    // Busca el elemento select existente
    let selectTurnosDisponibles = document.querySelector('.select-turnos-disponibles');

    // Si existe, elimina sus opciones para agregar las nuevas
    if (selectTurnosDisponibles) {
        selectTurnosDisponibles.innerHTML = '';
    } else {
        selectTurnosDisponibles = document.createElement('select');
        selectTurnosDisponibles.setAttribute('name', 'idTurno');
        selectTurnosDisponibles.setAttribute('onchange', 'desplegarTextarea()');
        selectTurnosDisponibles.classList.add('select-turnos-disponibles');
    }

    listaDeTurnos.forEach(turno => {
        if (turno.especialidad === especialidadElegida && turno.atendido === 'false' && turno.cancelado === 'false') {
            var opcion = document.createElement('option');
            opcion.value = parseInt(turno.id);

            let [mitadFecha, mitadHora] = turno.fechaTurno.split('T');
            let [anio, mes, dia] = mitadFecha.split('-');
            let fechaFormat = `${dia}/${mes}/${anio}, ${mitadHora}`;

            opcion.text = `Dr. ${turno.profesionalACargo.toUpperCase()}. Día ${fechaFormat} hs.`;
            selectTurnosDisponibles.add(opcion);
        }
    });

    console.log(selectTurnosDisponibles.length);

    // Agrega el select al DOM si tiene opciones, o elimínalo si ya existe
    if (selectTurnosDisponibles.length > 0) {

        if (!document.querySelector('.select-turnos-disponibles')) {
            let containerSelectTurnos = document.querySelector('.select-turnos-container');
            containerSelectTurnos.appendChild(selectTurnosDisponibles);
        }

    } else {
        // Elimina el select si no hay opciones
        if (document.querySelector('.select-turnos-disponibles')) {
            document.querySelector('.select-turnos-disponibles').remove();
        }
    }
}

function desplegarTextarea() {

}