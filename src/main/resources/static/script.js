const seccion1 = document.getElementById("seccion1");
const seccion2 = document.getElementById("seccion2");

const pacientes = document.getElementById("h1pac");
const descPac = document.getElementById("ppac");

const profesionales = document.getElementById("h1prof");
const descProf = document.getElementById("pprof");

let estado = false;

seccion2.addEventListener("click", function() {

    if(!estado){

        seccion1.style.cssText = "padding 0.3s ease, width 0.3s ease; padding: 1%; margin: 0; background-color:#1b5453; width: fit-content; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: flex-start; flex: 1; cursor: pointer;";
        pacientes.style.cssText = "font-size 0.3s ease; color: white; font-size: 1rem;";
        descPac.style.cssText = "opacity: 0; font-size 0.3s ease, opacity 0.3s ease; display: none; align-self: center;";

        seccion2.style.cssText = "padding 0.3s ease, width 0.3s ease; background-color: transparent; cursor: default; padding: 3%; width: 100%; display: flex; flex-direction: column; justify-content: center; align-items: flex-start; height: 100%;";
        profesionales.style.cssText = "font-size 0.3s ease; color: white; width: fit-content; padding: 0; margin: 0; text-align: start; margin-bottom: 0.5rem; font-size: 3em;";
        descProf.style.cssText = "opacity: 1; font-size 0.3s ease, opacity 0.3s ease; display: block; width: 100%; padding: 1px; margin: 0; font-size: 1.1rem; color: white;";
        
        estado = true;
    }
    
});

seccion1.addEventListener("click", function(){
    
    if(estado){
        seccion2.style.cssText = "padding 0.3s ease, width 0.3s ease; padding: 1%; margin: 0; background-color:#1b5453; width: fit-content; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: flex-start; flex: 1; cursor: pointer;";
        profesionales.style.cssText = "color: white; font-size: 1rem;";
        descProf.style.cssText = "opacity: 0; font-size 0.3s ease, opacity 0.3s ease; display: none; align-self: center;";
    
        seccion1.style.cssText = "padding 0.3s ease, width 0.3s ease; background-color: transparent; cursor: default; padding: 3%; width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: flex-start;";
        pacientes.style.cssText = "color: white; width: fit-content; padding: 0; margin: 0; text-align: start; margin-bottom: 0.5rem; font-size: 3em;";
        descPac.style.cssText = "opacity: 1; font-size 0.3s ease, opacity 0.3s ease; display: block; width: 100%; padding: 1px; margin: 0; font-size: 1.1rem; color: white;";

        estado = false;
    }

});
