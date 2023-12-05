package com.egg.webApp.controladores;

import com.egg.webApp.servicios.HistorialClinicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/historial_clinico")
public class HistorialClinicoControlador {
    
    @Autowired
    private HistorialClinicoServicio historialClinicoServicio;
    
    @PostMapping("/buscar_historial_clinico/{dni}")
    public String buscarHistoriales(ModelMap modelo, @RequestParam String dni){
        
        try {
            
        } catch (Exception e) {
        }
        return "";
    }

}
