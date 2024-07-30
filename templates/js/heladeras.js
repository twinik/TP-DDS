// Coordenadas de CABA, Buenos Aires, Argentina
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa (puedes usar otras capas como OpenStreetMap, Mapbox, etc.)
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
	maxZoom: 19,
	minZoom: 5,
}).addTo(map);

const inhabilitadaContent =
	'<div style="display: flex; flexDirection: row; gap: 5px; alignItems: center"> <svg xmlns = "http://www.w3.org/2000/svg" viewBox = "0 0 16 16" fill = "currentColor" width="16" height="16" > <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14ZM8 4a.75.75 0 0 1 .75.75v3a.75.75 0 0 1-1.5 0v-3A.75.75 0 0 1 8 4Zm0 8a1 1 0 1 0 0-2 1 1 0 0 0 0 2Z" clip-rule="evenodd" /></svg > <span style="color: white;">INHABILITADA</span> </div >';
const reportarFallaBtn =
	'<a href="reportar_falla.html" class="popup-button">Reportar Falla</a>';

// Datos de los marcadores
var markers = [
	{
		coords: [-34.59853190440259, -58.42009848935327],
		title: "Heladera UTN Medrano"
	},
	{
		coords: [-34.6067, -58.4299],
		title: "Heladera Parque Centenario"
	},
	{
		coords: [-34.6037, -58.4105],
		title: "Heladera Abasto"
	},
	{
		coords: [-34.6189, -58.4244],
		title: "Heladera Parque Rivadavia",
		extraContent: inhabilitadaContent,
		disabled: true
	}
];

// Función para crear marcadores
markers.forEach(function (markerData) {
	var marker = L.marker(markerData.coords).addTo(map);

	// Crear el contenido del popup usando el template HTML
	var popupContent = document.getElementById("marker-template").innerHTML;
	var div = document.createElement("div");
	div.innerHTML = popupContent;
	div.querySelector(".popup-title").innerText = markerData.title;

	if (markerData.extraContent) {
		var popupContentDiv = div.querySelector(".popup-content");
		var extraContentDiv = document.createElement("div");
		extraContentDiv.innerHTML = markerData.extraContent;
		popupContentDiv.insertBefore(
			extraContentDiv,
			popupContentDiv.querySelector(".popup-button")
		);
	}

	if (markerData.disabled) {
		div.querySelector(".popup-content").classList.add("disabled-heladera");
	} else {
		popupContentDiv = div.querySelector(".popup-content");
		popupContentDiv.innerHTML += reportarFallaBtn;
	}

	marker.bindPopup(div.innerHTML);
});
