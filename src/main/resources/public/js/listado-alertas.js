function filtrarAlertas() {
    const filtroAlerta = document.getElementById('filtroAlerta').value.toLowerCase();
    const filtroHeladera = document.getElementById('filtroHeladera').value.toLowerCase();
    const filas = document.querySelectorAll('.alerta-row');

    filas.forEach(fila => {
        const tipoAlerta = fila.getAttribute('data-tipo').toLowerCase();
        const nombreHeladera = fila.getAttribute('data-heladera').toLowerCase();

        const mostrarPorAlerta = filtroAlerta === '' || tipoAlerta === filtroAlerta;
        const mostrarPorHeladera = filtroHeladera === '' || nombreHeladera === filtroHeladera;

        if (mostrarPorAlerta && mostrarPorHeladera) {
            fila.style.display = '';
        } else {
            fila.style.display = 'none';
        }
    });
}

document.getElementById('filtroAlerta').addEventListener('change', filtrarAlertas);
document.getElementById('filtroHeladera').addEventListener('change', filtrarAlertas);