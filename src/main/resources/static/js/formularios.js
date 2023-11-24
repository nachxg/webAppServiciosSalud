
const dni = document.getElementById('dni-registro');
const mensajeDni = document.getElementById('warnings');

const maxLength = 8;

dni.addEventListener('input', function () {

    const dniValor = dni.value;

    if (dniValor.length > maxLength) {

        mensajeDni.textContent = "Se superó el límite máximo, volve a intentarlo";

    } else {
       
        mensajeDni.textContent = "";
    }
});


const matricula = document.getElementById('matricula-registro');
const mensajeMatricula = document.getElementById('warnings');
const arrayMatricula = [];

matricula.addEventListener('input', function () {

    const matriculaValor = matricula.value;


    if (arrayMatricula.includes(matriculaValor)) {

        mensajeMatricula.textContent = "La matricula ya fue ingresada";
        matriculaValor.value = "";

    } else {
        mensajeMatricula.textContent = "";
        arrayMatricula.push(matriculaValor);
    }
});


const fecha = document.getElementById('fecha-registro');
const mensajeFecha = document.getElementById('warnings');


fecha.addEventListener('input', function () {

    const fechaValor = new Date(fecha.value);
    const fechaActual = new Date();

    if (fechaValor > fechaActual) {

        mensajeFecha.textContent = "La fecha de nacimiento es incorrecta, vuelva a intentarlo";
        fechaInput.value = "";
        
    } else {
        mensajeFecha.textContent = "";
    }
});
