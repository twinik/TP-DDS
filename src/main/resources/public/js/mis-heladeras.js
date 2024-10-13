function confirmarEliminacion(heladeraId, heladeraName) {
    swal({
        title: "¿Estás seguro?",
        text: "¿Deseas eliminar " + heladeraName + "?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#cb4335",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "No, cancelar",
        closeOnConfirm: false
    }, function (isConfirm) {
        if (isConfirm) {
            document.getElementById('eliminar-' + heladeraId).submit();
        }
    });
}

// function editar(heladeraId, heladeraName) {
//     swal({
//         title: "Ingresa nuevo nombre",
//         text: "Nombre anterior: " + heladeraName,
//         input: "text",
//         inputLabel: "Nuevo nombre",
//         inputValue: heladeraName,
//         inputAttributes: {
//             name: "nombre"
//         },
//         showCancelButton: true,
//         inputValidator: (value) => {
//             if (!value) {
//                 return "You need to write something!";
//             }
//         },
//         confirmButtonColor: "#28b463",
//         confirmButtonText: "Editar",
//         cancelButtonText: "Cancelar",
//         closeOnConfirm: false
//     }, function (isConfirm) {
//         if (isConfirm) {
//             document.getElementById('editar-' + heladeraId).submit();
//         }
//     });
// }

// function editar(heladeraId, heladeraName) {
//     Swal.fire({
//         title: "Ingresa nuevo nombre",
//         text: "Nombre anterior: " + heladeraName,
//         input: "text",
//         inputLabel: "Nuevo nombre",
//         inputValue: heladeraName,
//         showCancelButton: true,
//         confirmButtonColor: "#28b463",
//         confirmButtonText: "Editar",
//         cancelButtonText: "Cancelar",
//         inputValidator: (value) => {
//             if (!value) {
//                 return "¡Debes ingresar un nombre!";
//             }
//         }
//     }).then((result) => {
//         if (result.isConfirmed) {
//             // Asigna el nuevo valor al campo input
//             let form = document.getElementById('editar-' + heladeraId);
//             let inputNombre = form.querySelector('input[name="nombre"]');
//             inputNombre.value = result.value;
//
//             // Envía el formulario
//             form.submit();
//         }
//     });
// }

function editar(heladeraId, heladeraName) {
    swal({
        title: "Ingresa nuevo nombre",
        text: "Nombre anterior: " + heladeraName,
        type: "input",  // Usamos "input" en SweetAlert 1
        inputType: "text", // Asegura que sea un campo de texto
        showCancelButton: true,
        inputValue: heladeraName, // Valor predeterminado
        closeOnConfirm: false, // Mantener la alerta abierta para validación
        confirmButtonColor: "#28b463",
        confirmButtonText: "Editar",
        cancelButtonText: "Cancelar",
        inputValidator: function(value) { // Validación personalizada
            if (value === "") {
                return swal.showInputError("¡Debes escribir algo!");
            }
        }
    }, function (inputValue) {
        if (inputValue === false) return; // Si se cancela
        if (inputValue === "") {
            swal.showInputError("¡Debes escribir algo!");
            return false;
        }
        // Enviar el formulario si se confirmó
        document.getElementById('editar-' + heladeraId).submit();
    });

    // Asignar manualmente el atributo 'name' al input
    setTimeout(function() {
        document.querySelector('.sa-input').setAttribute('name', 'nombre');
    }, 0);
}


