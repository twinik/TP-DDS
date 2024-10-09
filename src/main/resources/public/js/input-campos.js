let camposIndex = 1;
let indicesDisponibles = [];

const opcionesIndex = {};
const indicesOpcionesDisponibles = {};

const getNewCampoHtml = index => {
    return `<div id="campo-${index}">
        <strong class="mt-5 mb-5">Campo ${index}</strong>
        <label for="tipo-campo-${index}"
            class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Tipo de campo</label
        >
        <select
            id="tipo-campo-${index}"
            name="tipo-campo-${index}"
            onchange="mostrarOpciones(${index})"
            class="mb-5 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        >
            <option value="texto-libre">Texto libre</option>
            <option value="multiple-choice">Opción múltiple</option>
            <option value="single-choice">Opción única</option>
            <option value="numerico">Númerico</option>
            <option value="fecha">Fecha</option>
        </select>

        <label for="pregunta-${index}"
            class="text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Enunciado</label
        >
        <input
            type="text"
            id="pregunta-${index}"
            name="pregunta-${index}"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Pregunta"
            required
        />
        
        <input type="hidden" id="cantidad-opciones-campo-${index}" name="cantidad-opciones-campo-${index}" value="1"/>

        <div id="opciones-container-${index}" style="display: none;">
            <div id="opciones-campo-${index}">
                <div id="opcion-1-campo-${index}">
                    <label for="opcion-1-campo-${index}-input"
                        class="mt-5 text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white">Opción 1
                    </label>
                    <div class="flex flex-row">
                        <input type="text" id="opcion-1-campo-${index}-input" name="opcion-1-campo-${index}-input"
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            placeholder="Valor opción" />
                    </div>
                </div>
            </div>
            <button type="button" onclick="agregarOpcion(${index})" class="mt-5">Agregar otra opción +</button>
        </div>

        <div class="flex flex-row justify-between">
            <div class="flex flex-1 items-center mt-5 mb-5">
                <input id="obligatorio-campo-${index}" name="obligatorio-campo-${index}" type="checkbox" value="obligatorio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                <label for="obligatorio-campo-${index}"
                    class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Obligatorio</label>
            </div>

            <button type="button" onclick="eliminarCampo(${index})" class="flex-1 pr-0 text-red-500">Eliminar
                campo</button>
        </div>
    </div>`;
};

const nuevoCampo = () => {
    let nuevoIndex;

    if (indicesDisponibles.length > 0) {
        nuevoIndex = indicesDisponibles.shift();
    } else {
        nuevoIndex = ++camposIndex;
    }

    opcionesIndex[nuevoIndex] = 1;
    indicesOpcionesDisponibles[nuevoIndex] = [];

    const newElement = document.createElement('div');
    newElement.innerHTML = getNewCampoHtml(nuevoIndex);

    const cont = document.getElementById("camposContainer");
    cont.appendChild(newElement);

    const cantidadCampos = document.getElementById("cantidad-campos");
    cantidadCampos.value = parseInt(cantidadCampos.value) + 1;
};

const eliminarCampo = (index) => {
    const campoElement = document.getElementById(`campo-${index}`);
    if (campoElement) {
        campoElement.remove();
        indicesDisponibles.push(index);
        indicesDisponibles.sort((a, b) => a - b);

        delete opcionesIndex[index];
        delete indicesOpcionesDisponibles[index];

        const cantidadCampos = document.getElementById("cantidad-campos");
        cantidadCampos.value = parseInt(cantidadCampos.value) - 1;
    }
}

const mostrarOpciones = (campoIndex) => {
    const tipoCampo = document.getElementById(`tipo-campo-${campoIndex}`).value;
    const opcionesContainer = document.getElementById(`opciones-container-${campoIndex}`);
    if (tipoCampo === 'multiple-choice' || tipoCampo === 'single-choice') {
        opcionesContainer.style.display = 'block';
    } else {
        opcionesContainer.style.display = 'none';
    }
    manejarCheckboxChange(campoIndex);
}

const agregarOpcion = (campoIndex) => {
    if (!opcionesIndex[campoIndex]) {
        opcionesIndex[campoIndex] = 1;
        indicesOpcionesDisponibles[campoIndex] = [];
    }

    let nuevoOpcionIndex;
    if (indicesOpcionesDisponibles[campoIndex].length > 0) {
        nuevoOpcionIndex = indicesOpcionesDisponibles[campoIndex].shift();
    } else {
        nuevoOpcionIndex = ++opcionesIndex[campoIndex];
    }

    const opcionesDiv = document.getElementById(`opciones-campo-${campoIndex}`);
    const nuevaOpcionDiv = document.createElement('div');
    nuevaOpcionDiv.id = `opcion-${nuevoOpcionIndex}-campo-${campoIndex}`;
    nuevaOpcionDiv.name = `opcion-${nuevoOpcionIndex}-campo-${campoIndex}`;

    const label = document.createElement('label');
    label.setAttribute('for', `opcion-${nuevoOpcionIndex}-campo-${campoIndex}-input`);
    label.className = "mt-5 text-left block mb-2 text-sm font-medium text-gray-900 dark:text-white";
    label.textContent = `Opción ${nuevoOpcionIndex}`;

    const contenedorFlex = document.createElement('div');
    contenedorFlex.className = "flex items-center";

    const nuevaOpcionInput = document.createElement('input');
    nuevaOpcionInput.type = 'text';
    nuevaOpcionInput.id = `opcion-${nuevoOpcionIndex}-campo-${campoIndex}-input`;
    nuevaOpcionInput.name = `opcion-${nuevoOpcionIndex}-campo-${campoIndex}-input`;
    nuevaOpcionInput.className = "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500";
    nuevaOpcionInput.placeholder = `Valor opción`;

    const botonEliminar = document.createElement('button');
    botonEliminar.type = 'button';
    botonEliminar.className = "ms-2 text-red-500";
    botonEliminar.textContent = "Eliminar";
    botonEliminar.onclick = () => eliminarOpcion(campoIndex, nuevoOpcionIndex);

    contenedorFlex.appendChild(nuevaOpcionInput);
    contenedorFlex.appendChild(botonEliminar);

    nuevaOpcionDiv.appendChild(label);
    nuevaOpcionDiv.appendChild(contenedorFlex);

    opcionesDiv.appendChild(nuevaOpcionDiv);

    const cantidadOpciones = document.getElementById(`cantidad-opciones-campo-${campoIndex}`);
    cantidadOpciones.value = parseInt(cantidadOpciones.value) + 1;
}

const eliminarOpcion = (campoIndex, opcionIndex) => {
    const opcionDiv = document.getElementById(`opcion-${opcionIndex}-campo-${campoIndex}`);
    if (opcionDiv) {
        opcionDiv.remove();
        indicesOpcionesDisponibles[campoIndex].push(opcionIndex);
        indicesOpcionesDisponibles[campoIndex].sort((a, b) => a - b);

        const cantidadOpciones = document.getElementById(`cantidad-opciones-campo-${campoIndex}`);
        cantidadOpciones.value = parseInt(cantidadOpciones.value) - 1;
    }
}