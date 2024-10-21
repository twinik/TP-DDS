function confirmarEliminacion(heladeraId, heladeraName) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Deseas eliminar " + heladeraName + "?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#cb4335",
        cancelButtonColor: "#6c757d",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "No, cancelar",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('eliminar-' + heladeraId).submit();
        }
    });
}

function editar(heladeraId, heladeraName) {
    Swal.fire({
        title: "Ingresa nuevo nombre",
        text: "Nombre anterior: " + heladeraName,
        input: "text",
        inputLabel: "Nuevo nombre:",
        inputValue: heladeraName,
        showCancelButton: true,
        confirmButtonColor: "#3a4b6b",
        cancelButtonColor: "#6c757d",
        confirmButtonText: "Editar",
        cancelButtonText: "Cancelar",
        reverseButtons: true,
        inputValidator: (value) => {
            if (!value) {
                return "¡Debes ingresar un nombre!";
            }
        }
    }).then((result) => {
        if (result.isConfirmed) {
            let form = document.getElementById('editar-' + heladeraId);
            let inputNombre = form.querySelector('input[name="nombre"]');
            inputNombre.value = result.value;
            form.submit();
        }
    });
}
