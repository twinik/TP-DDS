
var cabaCoords = [-34.6118, -58.4173];

var map = L.map("map").setView(cabaCoords, 13);


L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);


var markerLayerGroup = L.layerGroup().addTo(map);


const localidadesPorProvincia = {
    caba: ['Almagro', 'Palermo', 'Recoleta', 'Bajo Flores'],
    cordoba: ['Nueva Córdoba', 'Alberdi', 'General Paz', 'Villa Cabrera', 'Alta Córdoba'],
    santaFe: ['Rosario', 'Santa Fe', 'Rafaela', 'Venado Tuerto', 'Reconquista'],
    mendoza: ['Godoy Cruz', 'Guaymallén', 'Luján de Cuyo', 'San Rafael', 'Maipú', 'Las Heras'],
    buenosAires: ['La Plata', 'Quilmes', 'Lanús', 'Morón', 'Tigre']
};


function actualizarLocalidades() {
    const provinciaSeleccionada = document.getElementById('provincia').value;
    const localidadSelect = document.getElementById('localidad');


    localidadSelect.innerHTML = '<option value="">Seleccionar</option>';


    if (provinciaSeleccionada && localidadesPorProvincia[provinciaSeleccionada]) {
        localidadesPorProvincia[provinciaSeleccionada].forEach(function(localidad) {
            const option = document.createElement('option');
            option.value = localidad.toLowerCase().replace(/\s+/g, ''); // Formatear valor para que coincida con el select
            option.text = localidad;
            localidadSelect.appendChild(option);
        });
    }
}

document.getElementById('provincia').addEventListener('change', actualizarLocalidades);

// Validación de los inputs
document.getElementById('confirmar').addEventListener('click', async function () {
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;
    const mensajeError = document.getElementById('mensaje-error');

    // Mostrar error si no se seleccionaron todos los campos
    if (localidad === '' || provincia === '') {
        mensajeError.style.display = 'block';

        // Ocultar el mensaje después de 3 segundos (opcional)
        setTimeout(() => {
            mensajeError.style.display = 'none';
        }, 3000);
    } else {
        mensajeError.style.display = 'none';

        try {
            // Eliminar los marcadores anteriores
            markerLayerGroup.clearLayers();

            // Obtener los nuevos puntos de donación
            var markers = await fetchDonaciones(provincia, localidad);

            // Añadir nuevos marcadores al mapa
            markers.forEach(function (markerData) {
                var marker = L.marker(markerData.coords).addTo(markerLayerGroup);

                const markerTemplate = `
                    <div class="popup-content">
                        <div class="popup-title">${markerData.nombre}</div>
                        <div class="popup-subtitle">${markerData.calle} ${markerData.altura}</div>
                    </div>
                `;
                marker.bindPopup(markerTemplate);
            });
        } catch (error) {
            console.error('Error al obtener donaciones:', error);
        }
    }
});
