// Coordenadas de CABA, Buenos Aires, Argentina
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa (puedes usar otras capas como OpenStreetMap, Mapbox, etc.)
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);

var markers = [];

(async function main() {
    markers = await fetchHeladeras()

    // Función para crear marcadores
    markers.forEach(function (markerData) {
        var marker = L.marker(markerData.coords).addTo(map);

        const markerTemplate = `<div class="flex flex-col popup-content">
                        <div class="popup-title">${markerData.title}</div>
                        <div
                                class="popup-button"
                                id="selectBtn/${markerData.id}"
                                onclick="clickSelectOrigen(this)"
                        >
                            Seleccionar Como Origen
                        </div>
                        <div
                                class="popup-button popup-destino"
                                id="selectBtnDestino/${markerData.id}"
                                onclick="clickSelectDestino(this)"
                        >
                            Seleccionar Como Destino
                        </div>
                    </div>`

        marker.bindPopup(markerTemplate);
    });
})();

function clickSelectOrigen(element) {
    const heladeraSelectedText =
        document.getElementById("heladeraOrigen");
    const heladeraSelectedHidden =
        document.getElementById("idHeladeraOrigen");
    const id = element.id.split("/")[1];
    heladeraSelectedHidden.value = id;
    heladeraSelectedText.innerHTML = markers.find(
        e => e.id == id
    ).title;
}

function clickSelectDestino(element) {
    const heladeraSelectedText =
        document.getElementById("heladeraDestino");
    const heladeraSelectedHidden =
        document.getElementById("idHeladeraDestino");
    const id = element.id.split("/")[1];
    heladeraSelectedHidden.value = id;
    heladeraSelectedText.innerHTML = markers.find(
        e => e.id == id
    ).title;
}