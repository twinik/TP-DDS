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
    markers = await fetchHeladerasDonaciones()

    // Función para crear marcadores
    markers.forEach(function (markerData) {
        var marker = L.marker(markerData.coords).addTo(map);

        const markerTemplate = `<div class="flex flex-col popup-content">
                    <div class="popup-title">${markerData.title}</div>
                    <div
                            class="popup-button"
                            id="selectBtn/${markerData.id}"
                            onclick="clickOnPopup(this)"
                    >
                        Seleccionar
                    </div>
                </div>`

        marker.bindPopup(markerTemplate);
    });
})();

function clickOnPopup(element) {
    const btns = document.querySelectorAll(".popup-button");
    const heladeraSelectedText = document.getElementById(
        "heladeraSeleccionada"
    );
    const heladeraSelectedHidden =
        document.getElementById("idHeladera");
    const id = element.id.split("/")[1];
    heladeraSelectedHidden.val;
    heladeraSelectedHidden.value = id;
    console.log(id)
    console.log(markers);
    heladeraSelectedText.innerHTML = markers.find(
        e => e.id == id
    ).title;
}

document.getElementById('form-vianda').addEventListener('submit', function (event) {
    const heladeraSelectedHidden = document.getElementById("idHeladera");
    const heladeraError = document.getElementById("heladera-error");

    if (!heladeraSelectedHidden.value) {
        event.preventDefault();
        heladeraError.classList.remove("hidden");
    } else {
        heladeraError.classList.add("hidden");
    }
});