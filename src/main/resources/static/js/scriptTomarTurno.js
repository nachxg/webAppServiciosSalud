
    // Función para abrir el modal y actualizar el formulario con el ID del turno
    function openModal(turnoId) {
        // Abre el modal
        $('#agregarFamiliarModal').modal('show');

        // Actualiza el formulario con el ID del turno
        $('#turnoIdInput').val(turnoId);
    }

    // Asocia el evento clic a todos los botones "Tomar Turno"
    $(document).ready(function () {
        ('button[id="openModalBtn"]').on('click', function () {
            // Obtiene el ID del turno desde el atributo data-turno-id
            var turnoId = $(this).data('turno-id');
            // Llama a la función para abrir el modal con el ID del turno
            openModal(turnoId);
        });

        // Cierra el modal cuando se hace clic en el botón "Cerrar"
        $('#closeModalBtn').on('click', function () {
            $('#agregarFamiliarModal').modal('hide');
        });
    });
