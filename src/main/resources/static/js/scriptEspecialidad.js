function mostrarEspecialidades(letra) {
    // Obtén la sección donde mostrarás las especialidades
    var especialidadesContainer = document.getElementById('especialidades-container');

    // Limpia el contenido existente
    especialidadesContainer.innerHTML = '';

    // Crea una lista desordenada para las especialidades
    var listaEspecialidades = document.createElement('ul');
    listaEspecialidades.id = 'lista-especialidades';

    // Aquí podrías tener un array o datos de especialidades que empiezan con la letra seleccionada
    // En este ejemplo, utilizo un array simple como ejemplo
    var especialidades = obtenerEspecialidadesPorLetra(letra);

    // Añade elementos de lista para cada especialidad a la lista
    especialidades.forEach(function (especialidad) {
        var listItem = document.createElement('li');
        var link = document.createElement('a');
        link.textContent = especialidad;
        link.href = 'informacion.html?especialidad=' + encodeURIComponent(especialidad);  // Agrega un enlace con un parámetro de especialidad
        listItem.appendChild(link);
        listaEspecialidades.appendChild(listItem);
    });

    // Añade la lista de especialidades al contenedor
    especialidadesContainer.appendChild(listaEspecialidades);
    // Agrega la clase 'centrado' al contenedor
    especialidadesContainer.classList.add('centrado');
}

function obtenerEspecialidadesPorLetra(letra) {
    // Aquí deberías tener tu lógica para obtener las especialidades según la letra seleccionada
    // Este es solo un ejemplo, podrías tener un objeto, una base de datos, etc.
    switch (letra) {
        case 'A-B':
            return ['Alergia e Inmunología', 'Anatomía Patológica', 'Anestesiología'];
        case 'C':
            return ['Cardiología', 'Cirugía Cardíaca', 'Cirugía General', 'Clínica Médica', 'Cirugía Pediátrica', 'Cirugía de Tórax'];
        // Agrega más casos según sea necesario
        case 'D-E':
            return ['Dermatología', 'Diabetes', 'Endocrinología', 'Diagnóstico por Imágenes'];
        case 'F-G':
            return ['Fonoaudiología', 'Geriatría y Gerontología', 'Ginecología y Obstetricia', 'Genética Médica Clínica'];
        case 'H-I-K':
            return ['Hematología', 'Infectología', 'Kinesioterapia Respiratoria'];
        case 'L-M':
            return ['Laboratorio Bioquímico Central', 'Medicina Laboral', 'Medicina Transfusional', 'Medicina Interna Hospitalaria'];
        case 'N':
            return ['Nefrología', 'Neurocirugía', 'Neonatología', 'Neurología Adultos', 'Neumonología', 'Nutrición', 'Neurología Infantil'];
        case 'O-P':
            return ['Obesología y Trastornos Alimentarios', 'Otorrinolaringología', 'Oftalmología', 'Pediatría', 'Oncología Clínica'];
        case 'R-S':
            return ['Radioterapia', 'Reumatología', 'Salud Mental'];
        case 'T-U':
            return ['Terapia Intensiva Pediátrica', 'Trasplantes', 'Toxicología', 'Traumatología y Ortopedia', 'Trabajo Social', 'Urología']
        default:
            return [];
    }
}
