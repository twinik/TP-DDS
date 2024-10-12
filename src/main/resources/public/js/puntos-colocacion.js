document.getElementById('confirmar').addEventListener('click', async function (event) {
    event.preventDefault();

    const latitud = document.getElementById('latitud').value;
    const longitud = document.getElementById('longitud').value;
    const radio = document.getElementById('radio').value;
    const mensajeError = document.getElementById('mensaje-error');

    // Mostrar error si no se seleccionaron todos los campos
    if (latitud === '' || longitud === '' || radio === '') {
        mensajeError.style.display = 'block';

        // Ocultar el mensaje después de 3 segundos (opcional)
        setTimeout(() => {
            mensajeError.style.display = 'none';
        }, 3000);
    } else {
        mensajeError.style.display = 'none';

        try {

            // Obtener los nuevos puntos de donación
            var markers = await fetchDonacionesCercanas(latitud, longitud, radio);

            // Añadir nuevos marcadores al mapa
            markers.forEach(function (markerData) {
                var marker = L.marker(markerData.coords);

                const markerTemplate = `
                    <div class="popup-content">
                        <div class="popup-title">${markerData.nombre}</div>
                        <div class="popup-subtitle">${markerData.calle} ${markerData.altura}</div>
                        <button class="popup-button" data-lat="${markerData.latitud}" data-lng="${markerData.longitud}">Seleccionar</button>
                    </div>
                `;
                marker.bindPopup(markerTemplate);

                document.querySelectorAll('.select-point').forEach(button => {
                    button.addEventListener('click', function () {
                        const latitudElegida = this.getAttribute('data-lat');
                        const longitudElegida = this.getAttribute('data-lng');
                        document.getElementById('latitudElegida').value = latitudElegida;
                        document.getElementById('longitudElegida').value = longitudElegida;
                        document.getElementById('calle').value = markerData.calle;
                        document.getElementById('altura').value = markerData.altura;
                    });
                });
            });
        } catch (error) {
            console.error('Error al obtener los puntos de colocacion:', error);
        }
    }
});

//cuando le al boton id="elegir-nuevo-punto" quiero que se me limpie el mapa y me deje elegir un nuevo punto, que tambien se borre lo que se escribio en los inputs
document.getElementById('elegir-nuevo-punto').addEventListener('click', function (event) {
    event.preventDefault();
    document.getElementById('latitud').value = '';
    document.getElementById('longitud').value = '';
    document.getElementById('radio').value = '';
    if (marker) map.removeLayer(marker);
});
