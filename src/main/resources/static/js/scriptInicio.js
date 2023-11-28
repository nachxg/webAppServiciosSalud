const contPerfil = document.getElementById("contenedor-perfil");
const icono = document.getElementById("icono");
const desplegable = document.getElementById("desplegable");

let estadoFlecha = false;

contPerfil.addEventListener("click", function(){

    if(!estadoFlecha){
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
