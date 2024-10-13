function confirmarCanje(productId, productName, productPoints) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Deseas canjear " + productName + " por " + productPoints + " puntos?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#28b463",
        cancelButtonColor: "#cb4335",
        confirmButtonText: "Sí, canjear",
        cancelButtonText: "No, cancelar",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('form-canje-' + productId).submit();
        }
    });
}

function filtrarCategoria() {
    const categoriaSeleccionada = document.getElementById('categoria').value;
    const productos = document.querySelectorAll('.producto');

    productos.forEach(producto => {
        const categoriaProducto = producto.getAttribute('data-categoria');

        if (categoriaSeleccionada === "" || categoriaProducto === categoriaSeleccionada) {
            producto.style.display = 'block'; // Mostrar producto
        } else {
            producto.style.display = 'none'; // Ocultar producto
        }
    });
}